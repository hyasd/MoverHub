package com.HouseMovers.UserApp.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.HouseMovers.UserApp.R;
import com.skydoves.elasticviews.ElasticButton;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private ElasticButton send;
    private FirebaseAuth mAuth;
    private Animation animBlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        email = findViewById(R.id.et_email);
        send = findViewById(R.id.resetBtn);
        mAuth = FirebaseAuth.getInstance();

        send.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == send){
            resetPassword();
        }

    }
    private void resetPassword() {

        String email1;
        email1 = email.getText().toString().trim();
        final ProgressDialog progressDialog = ProgressDialog.show(ForgotPassword.this, "Sending Reset Email....",
                "Please Wait...", false, false);
        if (email1.isEmpty()) {

            progressDialog.dismiss();
            Toast.makeText(ForgotPassword.this, "Please enter your email address", Toast.LENGTH_SHORT).show();

        } else {

            mAuth.sendPasswordResetEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {

                        progressDialog.dismiss();
                        Toast.makeText(ForgotPassword.this, "Please check your email to reset Password", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                        finish();

                    } else {

                        progressDialog.dismiss();
                        Toast.makeText(ForgotPassword.this, "Error in sending email", Toast.LENGTH_SHORT).show();


                    }

                }
            });


        }
    }

}
