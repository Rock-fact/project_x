package com.kor.foodmanager.ui.eventInfo.myEventInfoInProgress;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.kor.foodmanager.R;
import com.kor.foodmanager.data.model.UserDto;
import java.util.ArrayList;
import java.util.List;


public class MyEventInfoInProgressAdapter extends RecyclerView.Adapter<MyEventInfoInProgressAdapter.MyViewHolder>{

    private Long eventId;
    private List<UserDto> listOfParticipants;
    private MyClickListener listener;

    public MyEventInfoInProgressAdapter() {
        listOfParticipants = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subscriber,viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        UserDto subscriber = listOfParticipants.get(i);
        myViewHolder.fullName.setText(subscriber.getFullName());
         //TODO load image
    }

    @Override
    public int getItemCount() {
        return listOfParticipants.size();
    }

    public void setListener(MyClickListener listener) {
        this.listener = listener;
    }

    public void setSubscribers(List<UserDto> listOfParticipants, Long eventId){
        this.listOfParticipants=listOfParticipants;
        this.eventId=eventId;
        notifyDataSetChanged();
    }
    public void setSubscribers(List<UserDto> listOfParticipants){
        this.listOfParticipants=listOfParticipants;
        notifyDataSetChanged();
    }


    public void removeAll(){
        listOfParticipants.clear();
        notifyDataSetChanged();
    }

    public List<UserDto> getListOfParticipants() {
        return listOfParticipants;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView fullName;
        ImageView pictureOfSubscriber;
        Button inviteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.subscriber_full_name);
            pictureOfSubscriber = itemView.findViewById(R.id.subscriber_picture);
            inviteBtn = itemView.findViewById(R.id.invite_btn);
            if (listOfParticipants.get(getAdapterPosition()).getInvited()) {
                inviteBtn.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onItemClick(eventId, listOfParticipants.get(getAdapterPosition()).getUserId());
                    }
                    inviteBtn.setText("^");
                    inviteBtn.setEnabled(false);
                });

                itemView.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.userInfo(listOfParticipants.get(getAdapterPosition()));
                    }
                });

            } else {
                inviteBtn.setText("^");
                inviteBtn.setEnabled(false);
            }
        }
    }

    public interface MyClickListener{
        void onItemClick(Long eventId,Long userId);
        void userInfo(UserDto user);
    }
}