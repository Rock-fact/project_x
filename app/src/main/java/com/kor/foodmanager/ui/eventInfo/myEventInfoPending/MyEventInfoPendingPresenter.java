package com.kor.foodmanager.ui.eventInfo.myEventInfoPending;

import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.kor.foodmanager.App;
import com.kor.foodmanager.buissness.MyEventInProgressInteractor.IMyEventInProgressInteractor;
import com.kor.foodmanager.data.event.ServerException;
import com.kor.foodmanager.data.model.EventDto;
import com.kor.foodmanager.data.model.InvitationStatusDto;
import com.kor.foodmanager.data.model.UserDto;
import com.kor.foodmanager.ui.MainActivity;

import java.io.IOException;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MyEventInfoPendingPresenter extends MvpPresenter<IMyEventInfoPending> {
    @Inject
    Router router;

    private MyEventInfoPendingAdapter adapter;

    public MyEventInfoPendingPresenter() {
        App.get().mainComponent().inject(this);
        adapter = new MyEventInfoPendingAdapter();
    }

    public void userInfo(int adapterPosition) {
        UserDto user=adapter.getListOfParticipants().get(adapterPosition);
        router.navigateTo(MainActivity.USER_INFO_SCREEN_PENDING, user);
    }

    public MyEventInfoPendingAdapter getAdapter() {
        return adapter;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

