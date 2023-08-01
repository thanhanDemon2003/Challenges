package com.example.challenges;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    EditText edtCoSoSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = getSharedPreferences("userdata",MODE_PRIVATE);
        String coso = prefs.getString("coso",null);
        String name = prefs.getString("name",null);
        String email = prefs.getString("email",null);
        String picture = prefs.getString("picture",null);

        if (name != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("coso",coso);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            intent.putExtra("picture",picture);
            startActivity(intent);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        edtCoSoSelect = findViewById(R.id.edtCoSoSelect);
        edtCoSoSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] chonCoSo = {"Hà Nội", "Hồ Chí Minh", "Cần Thơ", "Quy Nhơn", "Đà Nẵng"};

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Chọn cơ sở");
                builder.setItems(chonCoSo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtCoSoSelect.setText(chonCoSo[which]);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        SignInButton btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN){
            //The Task returned from this call is always completed, no need to attach a listen
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completeTask) {
        try {
            GoogleSignInAccount account = completeTask.getResult(ApiException.class);
            //Signed in successfully, show authenticated UI
            updateUI(account);
        }catch (ApiException e){
            e.printStackTrace();
        }
    }

    private void updateUI(GoogleSignInAccount account){
        if (account != null){
            String email = account.getEmail();
            String name = account.getDisplayName();
            String picture = String.valueOf(account.getPhotoUrl());

            String coSo = edtCoSoSelect.getText().toString();

            if (isValidFptEmail(email) && coSo.equals("Hồ Chí Minh")){
                SharedPreferences.Editor editor = getSharedPreferences("userdata",MODE_PRIVATE).edit();
                editor.putString("coso",coSo);
                editor.putString("name",account.getDisplayName());
                editor.putString("email",account.getEmail());
                editor.putString("picture",picture);
                editor.apply();

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("coso",coSo);
                intent.putExtra("email",email);
                intent.putExtra("name",name);
                intent.putExtra("picture",picture);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Invalid FPT email address or facilities", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isValidFptEmail(String email) {
        //kiểm tra xem email có hợp lệ không
        if (email == null || email.isEmpty()){
            return false;
        }else {
            //kiểm tra email có kết thúc bằng @fpt.edu.vn
            if (email.endsWith("@fpt.edu.vn")){
                return true;
            }
        }
        return false;
    }
}