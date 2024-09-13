LoginActivitypackage com.HouseMovers.UserApp.Welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import com.HouseMovers.UserApp.Auth.LoginActivity;
import com.HouseMovers.UserApp.Dashboard.HomeDashboardActivity.HomeDashboardActivity;
import com.HouseMovers.UserApp.R;


public class SplashActivity extends AppCompatActivity {

    // set timer
    public static int timer = 2000;;

    private FirebaseAuth mAuth;
    private boolean viewLanguage = false;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        // firebase Reference
        mAuth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAuth.getCurrentUser()!= null ) {
                    Intent intent = new Intent(SplashActivity.this, HomeDashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, 2000);
    }




}
