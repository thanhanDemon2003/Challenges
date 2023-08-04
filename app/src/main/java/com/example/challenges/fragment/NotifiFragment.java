package com.example.challenges.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.challenges.ApiInterface;
import com.example.challenges.R;
import com.example.challenges.SearchableFragment;
import com.example.challenges.adapter.NotificationAdapter;
import com.example.challenges.model.Notification;
import com.example.challenges.model.NotificationDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotifiFragment extends Fragment implements SearchableFragment {
    private RecyclerView rcvNotification;
    private NotificationAdapter notificationAdapter;
    private List<Notification> notificationList;
    private List<Notification> filteredList; //ds lưu kết quả tìm kiếm

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationList = new ArrayList<>();
        filteredList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(notificationList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notifi, container, false);

        rcvNotification = rootView.findViewById(R.id.rcvNotification);
        rcvNotification.setLayoutManager(new LinearLayoutManager(getActivity()));

        notificationAdapter = new NotificationAdapter(notificationList);
        rcvNotification.setAdapter(notificationAdapter);

        // .baseUrl("https://demondev.games/api/")
        //Khởi tạo retrofit
        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://192.168.1.132/challenges/Thong_Bao/")
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
                    //notificationAdapter.updateData(notifications);
                    notificationList.clear();
                    notificationList.addAll(notifications);
                    notificationAdapter.notifyDataSetChanged();

                }else {

                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {

            }
        });
        //Thêm sự kiện click vào RecyclerView item
        notificationAdapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Notification notification) {
                showNotificationDetailDialog(notification);
            }
        });
        return rootView;
    }

    private void showNotificationDetailDialog(Notification notification) {
        //khởi tạo retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demondev.games/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //gọi API để lấy nội dung chi tiết thông báo
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<NotificationDetail> call = apiInterface.getNotificationDetail(notification.getId());//thay đổi phương thức lấy thông báo chi tiết
        call.enqueue(new Callback<NotificationDetail>() {
            @Override
            public void onResponse(Call<NotificationDetail> call, Response<NotificationDetail> response) {
                if (response.isSuccessful() && response.body() != null){
                    NotificationDetail notificationDetail = response.body();
                    showDetailDialog(notificationDetail.getContent());
                }else {
                    //xử lý lỗi khi không lấy được dữ liệu từ API
                }
            }

            @Override
            public void onFailure(Call<NotificationDetail> call, Throwable t) {

            }
        });
    }

    private void showDetailDialog(String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Nội dung chi tiết");
        builder.setMessage(content);
        builder.setPositiveButton("OK",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void performSearch(String query) {
        filteredList.clear();
        //duyệt ds thông báo và thêm vào filteredList các thông báo phù hợp với từ khoá search
        for (Notification notification : notificationList){
            if (notification.getTitle().toLowerCase().contains(query.toLowerCase())
                    || notification.getAuthor().toLowerCase().contains(query.toLowerCase())
                    || notification.getDate().toLowerCase().contains(query.toLowerCase())
                    || notification.getContent().toLowerCase().contains(query.toLowerCase())){
                filteredList.add(notification);
            }
        }
        notificationAdapter.updateData(filteredList);
        notificationAdapter.notifyDataSetChanged();
    }
}