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
import com.example.challenges.adapter.LichThiAdapter;
import com.example.challenges.model.LichThi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScoreFragment extends Fragment implements SearchableFragment {

    private RecyclerView rcvLichThi;
    private LichThiAdapter lichThiAdapter;
    private List<LichThi> lichThiList;
    private List<LichThi> filteredListLichThi; //ds lưu kết quả search

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Khởi tạo danh sách thông báo và danh sách kết quả search
        lichThiList = new ArrayList<>();
        filteredListLichThi = new ArrayList<>();
        //Khởi tạo lichHocAdapter và đưa danh sách ban đầu vào RecyclerView
        lichThiAdapter = new LichThiAdapter(lichThiList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        rcvLichThi = rootView.findViewById(R.id.rcvLichThi);
        rcvLichThi.setLayoutManager(new LinearLayoutManager(getActivity()));
        lichThiAdapter = new LichThiAdapter(lichThiList);
        rcvLichThi.setAdapter(lichThiAdapter);

        //.baseUrl("https://demondev.games/api/")
        //Khởi tạo retrofit
        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://172.16.66.147/challenges/lich_thi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Gọi API và cập nhật dữ liệu vào adapter khi nhận được kết quả
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<LichThi>> call = apiInterface.getLichThi();
        call.enqueue(new Callback<List<LichThi>>() {
            @Override
            public void onResponse(Call<List<LichThi>> call, Response<List<LichThi>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<LichThi> lichThis = response.body();
                    lichThiList.clear();
                    lichThiList.addAll(lichThis);
                    lichThiAdapter.notifyDataSetChanged();
                }else {

                }
            }

            @Override
            public void onFailure(Call<List<LichThi>> call, Throwable t) {

            }
        });
        return rootView;
    }

    @Override
    public void performSearch(String query) {
        if (TextUtils.isEmpty(query)){
            filteredListLichThi.addAll(lichThiList);
        }else {
            filteredListLichThi.clear();
            //duyệt ds lịch học và thêm vào filteredListLichHoc các lịch học phù hợp với từ khoá
            for (LichThi lichThi : lichThiList){
                if (lichThi.getLop().toLowerCase().contains(query.toLowerCase())
                        || lichThi.getPhong().toLowerCase().contains(query.toLowerCase())
                        || lichThi.getCa_thi().toLowerCase().contains(query.toLowerCase())
                        || lichThi.getNgay().toLowerCase().contains(query.toLowerCase())
                        || lichThi.getMa_mon().toLowerCase().contains(query.toLowerCase())
                        || lichThi.getTen_mon().toLowerCase().contains(query.toLowerCase())
                        || lichThi.getDot_thi().toLowerCase().contains(query.toLowerCase())
                        || lichThi.getGv1().toLowerCase().contains(query.toLowerCase())){
                    filteredListLichThi.add(lichThi);
                }
            }
        }
        lichThiAdapter.updateDataLichThi(filteredListLichThi);
        lichThiAdapter.notifyDataSetChanged();
    }
}