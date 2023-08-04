package com.example.challenges.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.challenges.R;


public class PhuongThucThanhToanFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phuong_thuc_thanh_toan, container, false);

        LinearLayout cardPayment = view.findViewById(R.id.cardPayment);

        cardPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentCard paymentCard = new PaymentCard();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(android.R.id.content, paymentCard)
                        .addToBackStack(null)
                        .commit();
            }
        });

//        cardPayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

        return view;
    }
}
