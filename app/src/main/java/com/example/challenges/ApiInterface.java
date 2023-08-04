package com.example.challenges;

import com.example.challenges.model.DangKyHocLai;
import com.example.challenges.model.LichHoc;
import com.example.challenges.model.LichThi;
import com.example.challenges.model.Notification;
import com.example.challenges.model.NotificationDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //Phương thức để lấy danh sách thông báo
    @GET("get_notifications.php")
    Call<List<Notification>> getNotifications();

    //Phương thức để lấy nội dung chi tiết của một thông báo
    @GET("get_notification_detail.php")
    Call<NotificationDetail> getNotificationDetail(@Query("notification_id") int notificationId);

    @GET("get_lich_hoc.php")
    Call<List<LichHoc>> getLichHoc();

    @GET("get_lich_thi.php")
    Call<List<LichThi>> getLichThi();

    @GET("get_thilai.php")
    Call<List<DangKyHocLai>> getThilai();

    Call<Void> updateThilaiApi(int i, int i1);
}
