package com.example.ejercicio3_2;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class Token{
    public static String token;

    public static void retrieveFirebaseToken(Context context) {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(context, "Error token", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        token = task.getResult();
                        Log.e("Token", token);
                    }
                });
    }
}
