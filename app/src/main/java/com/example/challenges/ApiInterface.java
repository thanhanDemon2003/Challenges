package com.example.challenges;

import com.example.challenges.model.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("get_notifications.php")
    Call<List<Notification>> getNotifications();
}
