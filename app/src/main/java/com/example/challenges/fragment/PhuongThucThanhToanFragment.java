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
import android.widget.Toast;

//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.challenges.R;

import com.example.challenges.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PhuongThucThanhToanFragment extends Fragment {
    private static final String MOMO_TEST_API_URL = "https://test-payment.momo.vn/pay/app";
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
        LinearLayout bankLayout = view.findViewById(R.id.bank);

        bankLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankFragment bankFragment = new BankFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(android.R.id.content, bankFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

//            LinearLayout MomoPayment =view.findViewById(R.id.MomoPayment);
//        MomoPayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Gọi API thanh toán MoMo test
//                callMoMoTestApi();
//            }
//        });
//
        return view;

    }
//    private void callMoMoTestApi() {
//        StringRequest request = new StringRequest(Request.Method.POST, MOMO_TEST_API_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Xử lý phản hồi từ API
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            // Kiểm tra trạng thái thanh toán
//                            if (jsonResponse.optInt("errorCode") == 0) {
//                                // Lấy link thanh toán từ phản hồi và mở trình duyệt
//                                String paymentUrl = jsonResponse.optString("payUrl");
//                                // Mở trình duyệt để thanh toán
//                                // TODO: Implement your browser open logic here
//                            } else {
//                                // Xử lý khi có lỗi
//                                Toast.makeText(getContext(), "Có lỗi khi tạo link thanh toán", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Xử lý lỗi
//                        Toast.makeText(getContext(), "Lỗi trong quá trình tạo link thanh toán", Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("partnerCode", "MOMOOJOI20210710");
//                params.put("accessKey", "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAmx7HUFPtY0zqeiGOmdaqBv/MhAIpC4HTUMxvatGvUVuo37Jr+e2D1ApbDi9ClQPqbZA1TN4mLEEGA7v1QapSTsqnpVEG5RXpr2aF+oSfcymVED9+MXakxjFKzEAZ4rxtKpiverQeQl0Nd8e6Vl9491Y2vVaT0nOpL7ozAn5rIWXeQeUVQsjg5K1gGbAhCqbBYq1aOAOJ4iTQ6fq+LGix2tyJ3KdL6VpLstUBKXBQtDNCCDTyb3kLJAkV3R5xMS9H+HOSYa/hZg4P2jZ0XEB4tAM7xea6xuicoSd8aPUb1AWbJ79qRLwMgn7G41ErHD/IfuYpY2K5HtxTXnbqZlyZ2RzlcXqxTlWb/Gr+XyXGBStg00VihUip9OVkYEg8YVLSxIxSBi6kJDfpcjbZcwkSET9zUhC6JD5+twRdzt7Bd9s01bC7wVVdh++g/3f1FKW2lpnFcxGbrGagEhnDI1OGjx5VncCn82WN6Cfo+2CqEMQzTuuXAHuG6nZ3XbNatcHA7CQXAFp433cXpjRzK3H7Oli5BkhB9u1FZC9b2bi3ip0dHTEVAB2sSjKtdQdaGZHVskyhvzXdyhGnd1CgUL2T6RZhA0fY38BRGpzBVfZOUcwDl9JqfFBRTn4z8XMoAPfAY5Z+ebXMwze85y7C0F9wX7u8oiQwklAZEBZ9vWKneFkCAwEAAQ==");
//                params.put("requestId", "1");
//                params.put("amount", "10000");
//                params.put("orderId", "MYPL123");
//                params.put("orderInfo", "Thanh Toán Học Phí");
//                params.put("returnUrl", "YOUR_RETURN_URL");
//                params.put("notifyUrl", "YOUR_NOTIFY_URL");
//                params.put("extraData", "YOUR_EXTRA_DATA");
//                return params;
//            }
//        };
//
//        // Thêm request vào hàng đợi của Volley
//        Volley.newRequestQueue(requireContext()).add(request);

}
