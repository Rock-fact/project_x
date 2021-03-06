package com.kor.foodmanager;

import android.app.Application;


import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.kor.foodmanager.di.MyEventInProgress.MyEventInProgressComponent;
import com.kor.foodmanager.di.MyEventInProgress.MyEventInProgressModule;
import com.kor.foodmanager.di.application.DaggerMainComponent;
import com.kor.foodmanager.di.application.MainComponent;
import com.kor.foodmanager.di.application.MainModule;
import com.kor.foodmanager.di.calendar.CalendarComponent;
import com.kor.foodmanager.di.calendar.CalendarModule;
import com.kor.foodmanager.di.event.EventComponent;
import com.kor.foodmanager.di.event.EventModule;
import com.kor.foodmanager.di.eventInfo.GuestEventInfoComponent;
import com.kor.foodmanager.di.eventInfo.GuestEventInfoModule;
import com.kor.foodmanager.di.login.LoginComponent;
import com.kor.foodmanager.di.login.LoginModule;
import com.kor.foodmanager.di.myEventList.MyEventListComponent;
import com.kor.foodmanager.di.myEventList.MyEventListModule;
import com.kor.foodmanager.di.notification.NotificationComponent;
import com.kor.foodmanager.di.notification.NotificationModule;
import com.kor.foodmanager.di.participationList.ParticipationListComponent;
import com.kor.foodmanager.di.participationList.ParticipationListModule;

public class App extends Application {
    private static App app;
    private MainComponent mainComponent;
    private LoginComponent loginComponent;
    private EventComponent eventComponent;
    private NotificationComponent notificationComponent;
    private GuestEventInfoComponent guestEventInfoComponent;
    private ParticipationListComponent participationListComponent;
    private MyEventListComponent myEventListComponent;
    private MyEventInProgressComponent myEventInProgressComponent;
    private CalendarComponent calendarComponent;

    public App() {
        app = this;
    }

    public static App get() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();

    }

    public MainComponent mainComponent() {
        return mainComponent;
    }

    public LoginComponent loginComponent() {
        if (loginComponent == null) {
            loginComponent = mainComponent.plus(new LoginModule());
        }
        return loginComponent;
    }

    public void clearLoginComponent() {
        loginComponent = null;
    }

    public EventComponent eventComponent() {
        if (eventComponent == null) {
            eventComponent = mainComponent.plus(new EventModule());
        }
        return eventComponent;
    }

    public void clearEventComponent() {
        eventComponent = null;
    }

    public NotificationComponent notificationComponent() {
        if (notificationComponent == null) {
            notificationComponent = mainComponent.plus(new NotificationModule());
        }
        return notificationComponent;
    }

    public void clearNotificationComponent() {
        notificationComponent = null;
    }

    public GuestEventInfoComponent guestEventInfoComponent() {
        if (guestEventInfoComponent == null) {
            guestEventInfoComponent = mainComponent.plus(new GuestEventInfoModule());
        }
        return guestEventInfoComponent;
    }

    public void clearGuestEventInfoComponent() {
        guestEventInfoComponent = null;
    }

    public ParticipationListComponent participationListComponent() {
        if (participationListComponent == null) {
            participationListComponent = mainComponent.plus(new ParticipationListModule());
        }
        return participationListComponent;
    }

    public void clearParticipationListComponent() {
        participationListComponent = null;
    }

    public MyEventListComponent myEventListComponent() {
        if (myEventListComponent == null) {
            myEventListComponent = mainComponent.plus(new MyEventListModule());
        }
        return myEventListComponent;
    }

    public void clearMyEventListComponent() {
        myEventListComponent = null;
    }


    public MyEventInProgressComponent myEventInProgressComponent() {
        if (myEventInProgressComponent == null) {
            myEventInProgressComponent = mainComponent.plus(new MyEventInProgressModule());
        }
        return myEventInProgressComponent;
    }

    public void clearMyEventInProgressComponent() {
        myEventInProgressComponent = null;
    }

    public CalendarComponent calendarComponent() {
        if (calendarComponent == null) {
            calendarComponent = mainComponent.plus(new CalendarModule());
        }
        return calendarComponent;
    }

    public void clearCalendarComponent() {
        calendarComponent = null;
    }

}
