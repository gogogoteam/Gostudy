package com.example.gostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SaveCallback;

public class ManageAccountActivity extends AppCompatActivity {

    Button btnSaveAccount, btnPasswordReset, btnBackFromAccount;
    EditText etChangeUsername, etChangeEmail;
    ParseUser user;

    public static final String TAG = "ManageAccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        etChangeUsername = findViewById(R.id.etChangeUsername);
        etChangeEmail = findViewById(R.id.etChangeEmail);
        btnSaveAccount = findViewById(R.id.btnSaveAccount);
        btnPasswordReset = findViewById(R.id.btnPasswordReset);
        btnBackFromAccount = findViewById(R.id.btnBackFromAccount);

        user = ParseUser.getCurrentUser();
        etChangeUsername.setText(user.getUsername());
        etChangeEmail.setText(user.getEmail());

        btnSaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setUsername(etChangeUsername.getText().toString());
                user.setEmail(etChangeEmail.getText().toString());
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e!=null) {
                            Log.e(TAG, "Error while saving account",e);
                            return;
                        }
                        Log.i(TAG,"Account save was successful!");
                    }
                });
            }
        });

        btnBackFromAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etChangeEmail.getText().toString().isEmpty()) {
                    Toast.makeText(ManageAccountActivity.this, "You need a valid email to reset password!", Toast.LENGTH_SHORT).show();
                } else {
                    user.requestPasswordResetInBackground(user.getEmail(), new RequestPasswordResetCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(ManageAccountActivity.this, "Email sent!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ManageAccountActivity.this, "Failed to send email.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}