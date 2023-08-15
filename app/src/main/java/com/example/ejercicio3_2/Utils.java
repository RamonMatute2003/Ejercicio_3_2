package com.example.ejercicio3_2;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils{
    public static void copyInputStreamToFile(InputStream input, String filePath) throws IOException {
        OutputStream output = null;
        try {
            byte[] buffer = new byte[1024];
            output = new FileOutputStream(new File(filePath));
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }
}
