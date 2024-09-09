package com.example.notificationserviceexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private NotificationHelper notificationHelper;
    private final ActivityResultLauncher activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean o) {
        if(o){
            Toast.makeText(MainActivity.this,"Post notification permission granted",Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(MainActivity.this,"Post notification permission not granted",Toast.LENGTH_LONG).show();
        }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button btn_notify = findViewById(R.id.btn_notify);
        notificationHelper = new NotificationHelper(this);
        btn_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationHelper.createNotificationChannel();
//                notificationHelper.showNotification(10);
                notificationHelper.requestNotificationPermission(activityResultLauncher);
            }
        });
    }
}