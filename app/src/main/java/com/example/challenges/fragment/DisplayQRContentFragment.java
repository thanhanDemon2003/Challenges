package com.example.challenges.fragment;

import static me.dm7.barcodescanner.zxing.ZXingScannerView.*;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.challenges.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class DisplayQRContentFragment extends Fragment implements ResultHandler {

    static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    ZXingScannerView scannerView;
    private boolean isCameraFacingBack = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_q_r_content, container, false);
        scannerView = view.findViewById(R.id.scannerView);

        FloatingActionButton fabCapture = view.findViewById(R.id.fabCapture);
        fabCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiểm tra quyền truy cập camera
                if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED){
                    //nếu chưa có quyền thì yêu cầu quyền truy cập
                    ActivityCompat.requestPermissions(requireActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            CAMERA_PERMISSION_REQUEST_CODE);
                }else {
                    //nếu đã có quyền, chuyển giữa camera sau và trước
                    if (isCameraFacingBack){
                        //nếu đang ở camera sau, chuyển sang camera trước
                        scannerView.stopCamera();//dừng camera sau
                        scannerView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);//bắt đầu camera trước
                    }else {
                        //nếu đang ở camera trước, chuyển sang camera sau
                        scannerView.stopCamera();//dừng camera trước
                        scannerView.startCamera();//bắt đầu camera sau
                    }
                    //sau khi chuyển camera, cập nhật trạng thái của biến isCameraFacingBack
                    isCameraFacingBack = !isCameraFacingBack;
                }
            }
        });

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        }else {
            startCamera();
        }
        return view;
    }

    private void startCamera() {
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String qrText = rawResult.getText();
        if (qrText != null){
            //kiểm tra nội dung mã QR có phải là một URL không
            if (qrText.startsWith("http://") || qrText.startsWith("https://")){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qrText)));
            }else {
                Toast.makeText(requireContext(), "QR Code: " + qrText, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Yêu cầu quyền được chấp nhận, khởi động camera
                startCamera();
            } else {
                // Yêu cầu quyền bị từ chối, xử lý theo nhu cầu của ứng dụng
                Toast.makeText(requireContext(), "Bạn cần cấp quyền truy cập camera để quét mã QR.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}