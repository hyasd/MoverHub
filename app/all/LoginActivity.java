package com.HouseMovers.UserApp.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.HouseMovers.UserApp.Dashboard.HomeDashboardActivity.HomeDashboardActivity;
import com.HouseMovers.UserApp.R;
import com.skydoves.elasticviews.ElasticButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView forgotPassword, signUpText;
    private ElasticButton signIngBtn;

    private FirebaseAuth mAuth;
    private DatabaseReference mReference, userRef;
    private AwesomeValidation awesomeValidation;
    private String userEmailS,userPasswordS;
    private EditText userEmail,userPassword;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    String selectlanguage;

    private Animation animBlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // initialization
        forgotPassword = findViewById(R.id.forgotPassword);
        signUpText = findViewById(R.id.signUpText);
        signIngBtn = findViewById(R.id.loginBtn);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);






        // set on click listeners
        forgotPassword.setOnClickListener(this);
        signUpText.setOnClickListener(this);
        signIngBtn.setOnClickListener(this);



        //Validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(userEmail, Patterns.EMAIL_ADDRESS, "Please Enter Valid Email");
        String regexPassword = ".{8,}";
        awesomeValidation.addValidation(userPassword, regexPassword, "Please Enter 8 digit password");

        //fireBase Auth
        mAuth = FirebaseAuth.getInstance();



    }
    @Override
    public void onClick(View v) {

        if (v == forgotPassword) {
            startActivity(new Intent(LoginActivity.this, ForgotPassword.class));

        }

        if (v == signUpText) {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

        }

        if (v == signIngBtn) {
            if (awesomeValidation.validate()) {

                userLogin();


            }
        }
    }
    private void userLogin() {

        //getting data to string
        userEmailS = userEmail.getText().toString();
        userPasswordS = userPassword.getText().toString();

        //progressDialog
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Authentication....",
                "Please Wait...", false, false);

        mAuth.signInWithEmailAndPassword(userEmailS, userPasswordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeDashboardActivity.class));
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }





}
