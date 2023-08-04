package com.example.challenges.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.challenges.MainActivity;
import com.example.challenges.R;

public class PaymentCard extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_card, container, false);

        // Lấy tham chiếu đến các EditText
        EditText edtThe = view.findViewById(R.id.edtThe);
        EditText edtName = view.findViewById(R.id.edtName);
        EditText edtDate = view.findViewById(R.id.edtDate);
        EditText edtCvv = view.findViewById(R.id.edtCvv);

        // Lấy tham chiếu đến các TextView
        TextView textCard = view.findViewById(R.id.textCard);
        TextView nameCard = view.findViewById(R.id.nameCard);
        TextView textDate = view.findViewById(R.id.textDate);
        TextView textCvv = view.findViewById(R.id.textCvv);

        // Thêm TextWatcher cho mỗi EditText
        edtThe.addTextChangedListener(new MyTextWatcher(textCard));
        edtName.addTextChangedListener(new MyTextWatcher(nameCard));
        edtDate.addTextChangedListener(new MyTextWatcher(textDate));
        edtCvv.addTextChangedListener(new MyTextWatcher(textCvv));

        Button btnThanhtoan = view.findViewById(R.id.btnThanhtoan);

        // Thiết lập sự kiện khi nút "Thanh toán" được nhấn
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển trạng thái của nút và hiển thị "Đang xử lý"
                btnThanhtoan.setEnabled(false);
                btnThanhtoan.setText("Đang xử lý...");
                btnThanhtoan.setBackgroundColor(getResources().getColor(R.color.gray));

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // Reset button state after processing
                                btnThanhtoan.setEnabled(true);
                                btnThanhtoan.setText("Thanh toán");
                                btnThanhtoan.setBackgroundColor(getResources().getColor(R.color.gray));
                            }
                        }, 5000);

                new android.os.Handler().postDelayed(new Runnable() {
                    public void run() {
                        ThanhToanThanhCongFragment fragment = new ThanhToanThanhCongFragment();
                        fragment.show(getParentFragmentManager(), "thanh_toan_thanh_cong");

                        getParentFragmentManager().popBackStack();
                        Intent intent = new Intent(requireContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }, 5000);
            }
        });


        return view;

    }

    private class MyTextWatcher implements TextWatcher {
        private TextView textView;

        public MyTextWatcher(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            textView.setText(s.toString());
        }
    }
}
