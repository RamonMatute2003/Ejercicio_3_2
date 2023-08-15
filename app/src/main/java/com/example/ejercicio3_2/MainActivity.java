package com.example.ejercicio3_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_send_messages, btn_video, btn_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_send_messages=findViewById(R.id.btn_send_messages);
        btn_video=findViewById(R.id.btn_video);
        btn_list=findViewById(R.id.btn_list);

        btn_send_messages.setOnClickListener(act->{
            Intent new_intent=new Intent(getApplicationContext(), Activity_messages.class);
            startActivity(new_intent);
        });

        btn_video.setOnClickListener(act->{
            Intent new_intent=new Intent(getApplicationContext(), Activity_video_view_background_services.class);
            startActivity(new_intent);
        });

        btn_list.setOnClickListener(act->{
            Intent new_intent=new Intent(getApplicationContext(), Activity_list.class);
            startActivity(new_intent);
        });
    }
}