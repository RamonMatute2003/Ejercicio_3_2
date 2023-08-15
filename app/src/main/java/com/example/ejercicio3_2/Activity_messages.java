package com.example.ejercicio3_2;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class Activity_messages extends AppCompatActivity {

    private Button btn_send_remote, btn_send_local;

    private boolean hasNotificationPermissionGranted = false;//variable para permiso de notificaciones
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        if (Build.VERSION.SDK_INT >= 33) {
            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
        } else {
            hasNotificationPermissionGranted = true;
        }

        btn_send_local=findViewById(R.id.btn_send_local);
        btn_send_remote=findViewById(R.id.btn_send_remote);

        btn_send_local.setOnClickListener(act->{
            SendMessageTask sendMessageTask = new SendMessageTask();
            sendMessageTask.execute();
        });

        btn_send_remote.setOnClickListener(act->{
            String url = "https://phpclusters-140120-0.cloudclusters.net/index.html?parametro="+Uri.encode(Token.token);

            // Crea un Intent para abrir la URL en el navegador
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            // Inicia el Intent
            startActivity(intent);
        });
        Token.retrieveFirebaseToken(getApplicationContext());
    }

    private ActivityResultLauncher<String> notificationPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                hasNotificationPermissionGranted = isGranted;
                if (!isGranted) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (Build.VERSION.SDK_INT >= 33) {
                            if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                                showNotificationPermissionRationale();
                            } else {
                                showSettingDialog();
                            }
                        }
                    }
                }
            });

    private void showSettingDialog() {
        new AlertDialog.Builder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3)
                .setTitle("Notificacion de permiso")
                .setMessage("Se requiere permiso de notificación, verifique el permiso de notificación en la configuración")
                .setPositiveButton("Ok", (dialog, which) -> {
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showNotificationPermissionRationale() {
        new AlertDialog.Builder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3)
                .setTitle("Alert")
                .setMessage("Se requiere permiso de notificación para mostrar la notificación.")
                .setPositiveButton("Ok", (dialog, which) -> {
                    if (Build.VERSION.SDK_INT >= 33) {
                        notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

}