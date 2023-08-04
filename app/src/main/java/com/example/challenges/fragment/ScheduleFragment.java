package com.example.challenges.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.challenges.ApiInterface;
import com.example.challenges.R;
import com.example.challenges.SearchableFragment;
import com.example.challenges.adapter.LichHocAdapter;
import com.example.challenges.model.LichHoc;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleFragment extends Fragment implements SearchableFragment {

    private RecyclerView rcvLichHoc;
    private LichHocAdapter lichHocAdapter;
    private List<LichHoc> lichHocList;
    private List<LichHoc> filteredListLichHoc; //ds lưu kết quả search

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Khởi tạo danh sách thông báo và danh sách kết quả search
        lichHocList = new ArrayList<>();
        filteredListLichHoc = new ArrayList<>();
        //Khởi tạo lichHocAdapter và đưa danh sách ban đầu vào RecyclerView
        lichHocAdapter = new LichHocAdapter(lichHocList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        rcvLichHoc = rootView.findViewById(R.id.rcvLichHoc);
        rcvLichHoc.setLayoutManager(new LinearLayoutManager(getActivity()));
        lichHocAdapter = new LichHocAdapter(lichHocList);
        rcvLichHoc.setAdapter(lichHocAdapter);

        //
        //.baseUrl("https://demondev.games/api/")
        //Khởi tạo retrofit
        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://172.16.66.147/challenges/lich_hoc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Gọi API và cập nhật dữ liệu vào adapter khi nhận được kết quả
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<LichHoc>> call = apiInterface.getLichHoc();
        call.enqueue(new Callback<List<LichHoc>>() {
            @Override
            public void onResponse(Call<List<LichHoc>> call, Response<List<LichHoc>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<LichHoc> lichHocs = response.body();
                    lichHocList.clear();
                    lichHocList.addAll(lichHocs);
                    lichHocAdapter.notifyDataSetChanged();
                }else {

                }
            }

            @Override
            public void onFailure(Call<List<LichHoc>> call, Throwable t) {

            }
        });
        return rootView;
    }

    @Override
    public void performSearch(String query) {
        if (TextUtils.isEmpty(query)){
            filteredListLichHoc.addAll(lichHocList);
        }else {
            //duyệt ds lịch học và thêm vào filteredListLichHoc các lịch học phù hợp với từ khoá
            for (LichHoc lichHoc : lichHocList){
                if (lichHoc.getPhong().toLowerCase().contains(query.toLowerCase())
                        || lichHoc.getCa_hoc().toLowerCase().contains(query.toLowerCase())
                        || lichHoc.getNgay().toLowerCase().contains(query.toLowerCase())
                        || lichHoc.getMa_mon().toLowerCase().contains(query.toLowerCase())
                        || lichHoc.getTen_mon().toLowerCase().contains(query.toLowerCase())
                        || lichHoc.getTen_giang_vien().toLowerCase().contains(query.toLowerCase())){
                    filteredListLichHoc.add(lichHoc);
                }
            }
        }
        lichHocAdapter.updateDataLichHoc(filteredListLichHoc);
        lichHocAdapter.notifyDataSetChanged();
    }
}