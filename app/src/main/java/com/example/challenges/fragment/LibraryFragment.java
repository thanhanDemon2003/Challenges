package com.example.challenges.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.challenges.R;
import com.example.challenges.adapter.DangKyHocLaiAdapter;
import com.example.challenges.adapter.NotificationAdapter;
import com.example.challenges.model.DangKyHocLai;
import com.example.challenges.model.Notification;

import java.util.List;

public class LibraryFragment extends Fragment {

    // code dang ky học lại trong này

    private RecyclerView rcvNotification;
    private DangKyHocLaiAdapter dangKyHocLaiAdapter;
    private List<DangKyHocLai> dangKyHocLaiList;
    private List<Notification> filteredListHocLai; //ds lưu kết quả tìm kiếm

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        Button btnDangKyHocLai = view.findViewById(R.id.btnDangKyHocLai);

        btnDangKyHocLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThiLaiFragment thiLaiFragment = new ThiLaiFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .add(android.R.id.content, thiLaiFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }




}