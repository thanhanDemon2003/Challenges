package com.example.challenges.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.challenges.ApiInterface;
import com.example.challenges.R;
import com.example.challenges.adapter.DangKyHocLaiAdapter;
import com.example.challenges.model.DangKyHocLai;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListMonHocFragment extends Fragment {

    // code dang ky học lại trong này
    private RecyclerView rcvDangkyhoclai;

    Button btnSignInHocLai;
    private DangKyHocLaiAdapter dangKyHocLaiAdapter;
    private List<DangKyHocLai> dangKyHocLaiList;
    private List<DangKyHocLai> filteredListHocLai;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Khởi tạo danh sách thông báo và danh sách kết quả tìm kiếm
        dangKyHocLaiList = new ArrayList<>();
        filteredListHocLai = new ArrayList<>();
        //Khởi tạo notificationAdapter và đưa danh sách ban đầu vào RecyclerView
//        dangKyHocLaiAdapter = new DangKyHocLaiAdapter(dangKyHocLaiList);
        dangKyHocLaiAdapter = new DangKyHocLaiAdapter(getContext(), dangKyHocLaiList);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_mon_hoc, container, false);
//        View view = inflater.inflate(R.layout.item_dang_ky_hoc_lai, container, false);
        rcvDangkyhoclai = rootView.findViewById(R.id.rcvDangkyhoclai);

        rcvDangkyhoclai.setLayoutManager(new LinearLayoutManager(getActivity()));
        dangKyHocLaiAdapter = new DangKyHocLaiAdapter(getContext(), dangKyHocLaiList);
        rcvDangkyhoclai.setAdapter(dangKyHocLaiAdapter);

 //       Button btnSignInHocLai = view.findViewById(R.id.btnSignInHocLai);
//        btnSignInHocLai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ThiLaiFragment thiLaiFragment = new ThiLaiFragment();
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.replace(android.R.id.content, thiLaiFragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });

        //
        //.baseUrl("https://demondev.games/api/")
        //Khởi tạo retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demondev.games/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Gọi API và cập nhật dữ liệu vào adapter khi nhận được kết quả
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<DangKyHocLai>> call = apiInterface.getThilai();
        call.enqueue(new Callback<List<DangKyHocLai>>() {
            @Override
            public void onResponse(Call<List<DangKyHocLai>> call, Response<List<DangKyHocLai>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<DangKyHocLai> dangKyHocLais = response.body();
                    dangKyHocLaiList.clear();
                    dangKyHocLaiList.addAll(dangKyHocLais);
                    dangKyHocLaiAdapter.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<DangKyHocLai>> call, Throwable t) {

            }
        });

        return rootView;
    }
}