package com.jva.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DeepActivity extends AppCompatActivity {

    Button notifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep);

        Uri uri = getIntent().getData();
        /// Daca s-a deschis printr-un link cu informatii
        if(uri!=null)
        {
            /// parsam link-ul
            List<String> params = uri.getPathSegments();
            String id = params.get(params.size()-1);
            Toast.makeText(this,id, Toast.LENGTH_SHORT).show();

            TextView deepTextView = findViewById(R.id.deepTextView);
            /// Setam textul sa contina informatia din link
            deepTextView.setText("Your Secret ingredient is "+id);
        }

        /// Setam notification channel-ul
        NotificationChannel channel = new NotificationChannel("MyNotification","MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        ///Cand dam click pe buton
        notifyBtn = findViewById(R.id.getNotifiedButton);
        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// Generam un intent care sa contina link-ul de mai jos pentru ca atunciu cand dam click pe notificare sa ni se deschida aplicatia cu informatiile din link
                Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
                notificationIntent.setData(Uri.parse("http://www.cookplus.com/v1/Asparagus"));
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

                /// Generam o notificare
                NotificationCompat.Builder builder = new NotificationCompat.Builder(DeepActivity.this, "MyNotification");
                builder.setContentTitle("Cook plus");
                builder.setContentText("Click to get secret");
                builder.setContentIntent(pi);
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(DeepActivity.this);
                // Afisam notificarea
                managerCompat.notify(1,builder.build());
            }
        });

        Button shareBest = findViewById(R.id.shareBestIngredient);
        // Cand dam click pe al2lea buton
        shareBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                String shareBody = "http://www.cookplus.com/v1/Milk";
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "http://www.cookplus.com/v1/Milk");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share CookPlus"));
            }
        });


    }
}