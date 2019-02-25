package com.kor.foodmanager;

import android.app.Application;


import com.kor.foodmanager.di.application.DaggerMainComponent;
import com.kor.foodmanager.di.application.MainComponent;
import com.kor.foodmanager.di.application.MainModule;
import com.kor.foodmanager.di.event.EventComponent;
import com.kor.foodmanager.di.event.EventModule;
import com.kor.foodmanager.di.login.LoginComponent;
import com.kor.foodmanager.di.login.LoginModule;

public class App extends Application {
    private static App app;
    private MainComponent mainComponent;
    private LoginComponent loginComponent;
    private EventComponent eventComponent;

    public App(){
        app = this;
    }

    public static App get(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();

    }

    public MainComponent mainComponent(){
        return mainComponent;
    }

    public LoginComponent loginComponent(){
        if(loginComponent == null){
            loginComponent = mainComponent.plus(new LoginModule());
        }
        return loginComponent;
    }

    public void clearLoginComponent(){
        loginComponent = null;
    }

    public EventComponent eventComponent(){
        if(eventComponent == null){
            eventComponent = mainComponent.plus(new EventModule());
        }
        return eventComponent;
    }

    public void clearEventComponent(){
        eventComponent = null;
    }
}
