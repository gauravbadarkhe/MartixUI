package com.shopclients.martixui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final RecyclerView recyclerView = findViewById(R.id.rv_matrix);

        final EditText edt_height = findViewById(R.id.edt_height);
        final EditText edt_width = findViewById(R.id.edt_width);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int height = Integer.valueOf(edt_height.getText().toString());
                int width = Integer.valueOf(edt_width.getText().toString());

                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, width));
                recyclerView.setAdapter(new MatrixAdapter(height * width));

                Snackbar.make(view, "Matrix Created", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
