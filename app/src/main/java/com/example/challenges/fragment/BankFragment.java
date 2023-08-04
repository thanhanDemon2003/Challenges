package com.example.challenges.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challenges.R;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnXacNhanBank;
    private TextView countdownTimer;
    private int timeRemaining = 10 * 60 * 1000;

    public BankFragment() {
        // Required empty public constructor
    }
    public static BankFragment newInstance(String param1, String param2) {
        BankFragment fragment = new BankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank, container, false);
        countdownTimer = view.findViewById(R.id.time);

        // Start the countdown
        startCountdown();
        btnXacNhanBank = view.findViewById(R.id.btnXacNhanBank);
        btnXacNhanBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePaymentButtonClick();
            }
        });

        return view;
    }
    private void startCountdown() {
        new CountDownTimer(timeRemaining, 1000) {
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;

                String timeFormatted = String.format("%02d:%02d", minutes, seconds);
                countdownTimer.setText(timeFormatted);
            }

            public void onFinish() {
                // Countdown finished, perform any actions you need
                countdownTimer.setText("00:00");
                // Here you can perform the API call or other actions
            }
        }.start();
    }
    private int clickCount = 0;
    private Handler handler = new Handler();
    private Runnable resetButtonRunnable = new Runnable() {
        @Override
        public void run() {
            btnXacNhanBank.setEnabled(true);
            btnXacNhanBank.setText("Thanh toán");
            btnXacNhanBank.setBackgroundColor(getResources().getColor(R.color.cam123));
        }
    };

    private void handlePaymentButtonClick() {
        clickCount++;
        btnXacNhanBank.setEnabled(false);
        btnXacNhanBank.setText("Đang xác nhận...");
        btnXacNhanBank.setBackgroundColor(getResources().getColor(R.color.gray));

        if (clickCount == 1) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    performDelayedApiCall();
                    Toast.makeText(getContext(), "Vui lòng thanh toán", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(resetButtonRunnable, 5000);
                }
            }, 3000);
        } else if (clickCount == 2) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Đang xác nhận...", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(resetButtonRunnable, 5000);
                    showPaymentSuccessFragment();
                }
            }, 10000);
        }

    }



    private void showPaymentSuccessFragment() {
        ThanhToanThanhCongFragment fragment = new ThanhToanThanhCongFragment();
        fragment.show(getParentFragmentManager(), "thanh_toan_thanh_cong");
        getParentFragmentManager().popBackStack();
    }

    private void performDelayedApiCall() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create a new thread to perform the API call
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Create a URL object with the API endpoint
                            URL url = new URL("https://demondev.games/api/update_thilai_api.php?id=2&is_active=0");

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
        }, 1000); // Delay in milliseconds
    }
}