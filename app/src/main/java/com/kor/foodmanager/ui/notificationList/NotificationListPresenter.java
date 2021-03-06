package com.kor.foodmanager.ui.notificationList;

import android.os.AsyncTask;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.kor.foodmanager.App;
import com.kor.foodmanager.buissness.notification.INotificationInteractor;
import com.kor.foodmanager.data.event.ServerException;
import com.kor.foodmanager.data.model.NotificationDto;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import ru.terrakok.cicerone.Router;
import static com.kor.foodmanager.ui.MainActivity.NOTIFICATION_INFO_SCREEN;

@InjectViewState
public class NotificationListPresenter extends MvpPresenter<INotificationList>{
    @Inject Router router;
    @Inject INotificationInteractor interactor;
    NotificationListAdapter adapter;

    public NotificationListPresenter(){
        App.get().notificationComponent().inject(this);
        adapter = new NotificationListAdapter();
    }

    public void startWork() {
        new GetNotificationListTask().execute();
    }

    @Override
    public void onDestroy() {
        App.get().clearNotificationComponent();
        super.onDestroy();
    }

    public void showInfo(NotificationDto notificationDto) {
        router.navigateTo(NOTIFICATION_INFO_SCREEN, notificationDto);
    }

    public void onResume() {
        adapter.notifyDataSetChanged();             // TODO: 03.03.2019 for new (isRead) change. Not working now. 
    }

    private class GetNotificationListTask extends AsyncTask<Void,Void, List<NotificationDto>> {
        private Boolean isSuccess;
        String res;

        @Override
        protected void onPreExecute() {
            getViewState().showProgressFrame();
        }

        @Override
        protected List<NotificationDto> doInBackground(Void... voids) {
            try {
                isSuccess = true;
                return interactor.getNotificationList();
            } catch (IOException e) {
                res = "Connection failed!";
                isSuccess = false;
            } catch (ServerException e) {
                res = e.getMessage();
                isSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<NotificationDto> notificationListDto) {
            getViewState().hideProgressFrame();
            if(isSuccess){
                List<NotificationDto> list = notificationListDto;
                adapter.setList(list);
                getViewState().showNotificationList(adapter);
            }else{
                router.showSystemMessage(res);
            }
        }
    }
}
