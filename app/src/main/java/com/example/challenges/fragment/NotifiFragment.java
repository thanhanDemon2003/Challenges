package com.example.challenges.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.challenges.ApiInterface;
import com.example.challenges.R;
import com.example.challenges.adapter.NotificationAdapter;
import com.example.challenges.model.Notification;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotifiFragment extends Fragment {
    private RecyclerView rcvNotification;
    private NotificationAdapter notificationAdapter;
    private List<Notification> dataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notifi, container, false);

        rcvNotification = rootView.findViewById(R.id.rcvNotification);
        rcvNotification.setLayoutManager(new LinearLayoutManager(getActivity()));

        notificationAdapter = new NotificationAdapter(new ArrayList<>());
        rcvNotification.setAdapter(notificationAdapter);

        //Khởi tạo retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.112.116/challenges/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Gọi API và cập nhật dữ liệu vào adapter khi nhận được kết quả
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Notification>> call = apiInterface.getNotifications();
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<Notification> notifications = response.body();
                    notificationAdapter.updateData(notifications);
                }else {

                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {

            }
        });
        return rootView;
    }
    private void filterData(String query) {
        if (dataList != null){
            List<Notification> filteredList = new ArrayList<>();
            for (Notification item : dataList){
                if (item.getTitle().toLowerCase().contains(query.toLowerCase())){
                    filteredList.add(item);
                }
            }
            notificationAdapter.updateData(filteredList);
            notificationAdapter.notifyDataSetChanged();
        }
    }
}