package com.kor.foodmanager.ui.notificationInfo;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.kor.foodmanager.R;
import com.kor.foodmanager.data.model.NotificationDto;
import com.kor.foodmanager.ui.IToolbar;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NotificationInfoFragment extends MvpAppCompatFragment implements INotificationInfo{
    @InjectPresenter NotificationInfoPresenter presenter;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.message) TextView message;
    @BindView(R.id.date) TextView date;
    private Unbinder unbinder;
    private NotificationDto notification;
    private IToolbar iToolbar;

    public NotificationInfoFragment() {
    }

    public static NotificationInfoFragment newInstance(NotificationDto data){
        NotificationInfoFragment fragment = new NotificationInfoFragment();
        fragment.notification = data;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            notification=(NotificationDto) savedInstanceState.getSerializable("notification");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("notification",notification);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        if(notification!=null){
            title.setText(notification.getTitle());
            message.setText(notification.getMessage());
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            date.setText(formatDate.format(notification.getDate()));
            presenter.startWork(notification.getNotificationId());
            iToolbar = (IToolbar) getActivity();
            iToolbar.setTitleToolbarEnable("Notification Info",false,true,false);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
