package com.example.ejercicio3_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Activity_list extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Forum_adapter forumAdapter;
    private List<Forum_post> forumPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crea algunos datos de ejemplo
        forumPosts = new ArrayList<>();
        forumPosts.add(new Forum_post("Grafico semanal", "Este se presenta cada semana y tiene que ir bien detallado", R.drawable.ic_launcher_background));
        forumPosts.add(new Forum_post("Mi logo", "Lo cree en adobe Ilustrator el 27 de julio de 2023", R.drawable.ic_launcher_foreground));
        forumPosts.add(new Forum_post("Mi celular", "Carcasa de celular viejo", R.drawable.carcasa_telefono));
        forumPosts.add(new Forum_post("Reporte", "Reporte de cierre de mes de agosto 2021", R.drawable.excel));

        forumAdapter = new Forum_adapter(forumPosts);
        recyclerView.setAdapter(forumAdapter);
    }
}