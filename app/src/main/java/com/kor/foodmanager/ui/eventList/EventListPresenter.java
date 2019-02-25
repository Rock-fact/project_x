package com.kor.foodmanager.ui.eventList;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.kor.foodmanager.data.model.EventDto;
import com.kor.foodmanager.data.model.EventListDto;
import com.kor.foodmanager.data.provider.web.Api;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@InjectViewState
public class EventListPresenter extends MvpPresenter<IEventList> {

private EventListAdapter adapter = new EventListAdapter();

    public void loadEventList(){
        new LoadingList().execute();
    }

    public EventListAdapter getAdapter(){
        return adapter;
    }



    private class LoadingList extends AsyncTask<Void, Void, List<EventDto>> {

        private static final String BASE_URL = "https://mishpahug-java221-team-a.herokuapp.com";
        private Api api;
        private List<EventDto> tmp = new ArrayList<>();
//        private EventListAdapter tmpAdapter = adapter;

        @Override
        protected void onPreExecute() {
            getViewState().showProgressFrame();
        }

        @Override
        protected List<EventDto> doInBackground(Void... voids) {
            try {
                Retrofit retrofit = new Retrofit.Builder().client(new OkHttpClient()).baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();
                api = retrofit.create(Api.class);
                Call<EventListDto> call = api.getListOfEventsInProgress(0,10);
                retrofit2.Response<EventListDto> response = call.execute();
                Log.d("MY_TAG", "doInBackground: 4");
                if(response.isSuccessful()){
                    EventListDto eventListDto = response.body();
                    tmp = eventListDto.getContent();
                    Log.d("MY_TAG", "doInBackground: ");
                    return tmp;
                } else {
                    throw new Exception(response.errorBody().string());
                }
            } catch (Exception e) {
                Log.d("MY_TAG", "doInBackground: "+e.getMessage());;
            }
            return tmp;
        }

        @Override
        protected void onPostExecute(List<EventDto> list) {
           getViewState().hideProgressFrame();
            if (list!=null) {
                adapter.removeAll();
                for(int i=0; i<list.size(); i++){
                    adapter.addEvent(list.get(i));
                }

            }
        }
    }
}