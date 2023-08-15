package com.example.ejercicio3_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Activity_video_view_background_services extends AppCompatActivity {

    private Button startButton;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_background_services);

        startButton = findViewById(R.id.startButton);
        videoView = findViewById(R.id.videoView);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la tarea en segundo plano al hacer clic en el botón.
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Uri> {

        private AlertDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Mostrar el mensaje de progreso mientras se descarga el video.
            AlertDialog.Builder builder = new AlertDialog.Builder(Activity_video_view_background_services.this);
            builder.setMessage("Descargando video...");
            builder.setCancelable(false);
            progressDialog = builder.create();
            progressDialog.show();
        }

        @Override
        protected Uri doInBackground(Void... voids) {
            // Descargar el video en segundo plano.
            try {
                // Se crea una instancia de URL con la dirección del video de youtube que se desea descargar,
                // configuraciones ya hechas en un servidor para poder acceder a este video de youtube
                // el video es la clase que la subi a youtube para probar.
                //se descarga y se almacena en firebase, todo esto en el servidor esta hecho
                URL url = new URL("https://firebasestorage.googleapis.com/v0/b/ui-transfer-file.appspot.com/o/Message_files%2F2023-08-02.mp4?alt=media&token=a0b37e08-2985-493c-b745-822363614368"); // Reemplaza con la URL real del video
                // Se abre una conexión HTTP a la URL especificada.
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream input = new BufferedInputStream(connection.getInputStream());
                // Se crea la ruta de almacenamiento local para guardar el video descargado.
                // getFilesDir().getPath() devuelve la ruta del directorio privado de la aplicación.
                String videoPath = getFilesDir().getPath() + "/video.mp4";
                Utils.copyInputStreamToFile(input, videoPath);
                // Se desconecta la conexión HTTP una vez que se ha descargado y guardado el video.
                connection.disconnect();
                return Uri.parse(videoPath);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Uri videoUri) {
            super.onPostExecute(videoUri);

            // Ocultar el mensaje de progreso.
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            // Reproducir el video si se descargó con éxito.
            if (videoUri != null) {
                videoView.setVideoURI(videoUri);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                        //Agregar controles de iteracion
                        MediaController mediaController = new MediaController(Activity_video_view_background_services.this);
                        mediaController.setMediaPlayer(videoView);
                        videoView.setMediaController(mediaController);
                    }
                });
            } else{
                // Manejar el caso en el que la descarga del video falla.
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        }
    }
}