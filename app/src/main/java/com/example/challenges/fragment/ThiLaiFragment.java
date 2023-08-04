package com.example.challenges.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.challenges.R;


public class ThiLaiFragment extends DialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thi_lai, container, false);

        ImageView btnBack = view.findViewById(R.id.btnBack);

        TextView payManent = view.findViewById(R.id.payManent);

        btnBack.setImageResource(R.drawable.btnback);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        view.setLayoutParams(params);
        payManent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhuongThucThanhToanFragment phuongThucThanhToanFragment = new PhuongThucThanhToanFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(android.R.id.content, phuongThucThanhToanFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }


}