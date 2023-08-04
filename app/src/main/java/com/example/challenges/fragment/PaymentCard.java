package com.example.challenges.fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.challenges.ApiInterface;
import com.example.challenges.R;

import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentCard extends Fragment {

    private MyTextWatcher myTextWatcher;
    private Button btnThanhtoan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_card, container, false);

        EditText edtThe = view.findViewById(R.id.edtThe);
        EditText edtName = view.findViewById(R.id.edtName);
        EditText edtDate = view.findViewById(R.id.edtDate);
        EditText edtCvv = view.findViewById(R.id.edtCvv);

        TextView textCard = view.findViewById(R.id.textCard);
        TextView nameCard = view.findViewById(R.id.nameCard);
        TextView textDate = view.findViewById(R.id.textDate);
        TextView textCvv = view.findViewById(R.id.textCvv);

        myTextWatcher = new MyTextWatcher();
        myTextWatcher.setTextView(textCard);
        edtThe.addTextChangedListener(myTextWatcher);

        myTextWatcher = new MyTextWatcher();
        myTextWatcher.setTextView(nameCard);
        edtName.addTextChangedListener(myTextWatcher);

        myTextWatcher = new MyTextWatcher();
        myTextWatcher.setTextView(textDate);
        edtDate.addTextChangedListener(myTextWatcher);

        myTextWatcher = new MyTextWatcher();
        myTextWatcher.setTextView(textCvv);
        edtCvv.addTextChangedListener(myTextWatcher);

        btnThanhtoan = view.findViewById(R.id.btnThanhtoan);
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePaymentButtonClick();
            }
        });

        return view;
    }

    private void handlePaymentButtonClick() {
        btnThanhtoan.setEnabled(false);
        btnThanhtoan.setText("Đang xử lý...");
        btnThanhtoan.setBackgroundColor(getResources().getColor(R.color.gray));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnThanhtoan.setEnabled(true);
                btnThanhtoan.setText("Thanh toán");
                btnThanhtoan.setBackgroundColor(getResources().getColor(R.color.gray));
                performDelayedApiCall();
            }
        }, 10000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showPaymentSuccessFragment();
            }
        }, 10000);
    }

    private void showPaymentSuccessFragment() {
        ThanhToanThanhCongFragment fragment = new ThanhToanThanhCongFragment();
        fragment.show(getParentFragmentManager(), "thanh_toan_thanh_cong");
        getParentFragmentManager().popBackStack();
    }

    private void performDelayedApiCall() {
        // Delay 10 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create a new thread to perform the API call
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Create a URL object with the API endpoint
                            URL url = new URL("https://demondev.games/api/update_thilai_api.php?id=1&is_active=0");

                            // Open a connection to the URL
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                            // Set the request method to GET
                            connection.setRequestMethod("GET");

                            // Get the response code
                            int responseCode = connection.getResponseCode();

                            // Close the connection
                            connection.disconnect();

                            // Log the response code (optional)
                            Log.d("API Call", "Response Code: " + responseCode);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }, 1000);
    }


    private class MyTextWatcher implements TextWatcher {
        private TextView textView;

        public void setTextView(TextView textView) {
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
            if (textView != null) {
                textView.setText(s.toString());
            }
        }
    }
}
