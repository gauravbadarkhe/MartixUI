package com.shopclients.martixui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int[][] matrix1;
    int[][] matrix2;
    int height_1;
    int width_1;

    int height_2;
    int width_2;
    boolean isFirst = true;


    ArrayList<String> resultList;


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
        final Button button = findViewById(R.id.btn);
        final Button btn_multiply = findViewById(R.id.btn_multiply);


        if (getIntent().hasExtra("resultList")) {
            resultList = getIntent().getStringArrayListExtra("resultList");
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, getIntent().getIntExtra("width", 2)));
            recyclerView.setAdapter(new MatrixAdapter(resultList, MainActivity.this));

            btn_multiply.setVisibility(View.GONE);
            edt_height.setVisibility(View.GONE);
            edt_width.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
        } else {
            btn_multiply.setVisibility(View.VISIBLE);
            edt_height.setVisibility(View.VISIBLE);
            edt_width.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
        }


        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplyMatrix(matrix1, matrix2);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFirst = !isFirst;
                if (isFirst) {
                    button.setText("Switch current is 1");
                } else {
                    button.setText("Switch current is 2");
                }

                recyclerView.setAdapter(null);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isFirst) {
                    height_1 = Integer.valueOf(edt_height.getText().toString());
                    width_1 = Integer.valueOf(edt_width.getText().toString());
                    matrix1 = new int[height_1][width_1];

                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, width_1));
                    recyclerView.setAdapter(new MatrixAdapter(height_1 * width_1, MainActivity.this));
                } else {
                    height_2 = Integer.valueOf(edt_height.getText().toString());
                    width_2 = Integer.valueOf(edt_width.getText().toString());
                    matrix2 = new int[height_2][width_2];

                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, width_2));
                    recyclerView.setAdapter(new MatrixAdapter(height_2 * width_2, MainActivity.this));
                }


                Snackbar.make(view, "Matrix Created", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void multiplyMatrix(int[][] matrix1, int[][] matrix2) {


        int[][] restults = new int[height_1][width_2];


        int sum = 0;
        for (int i = 0; i < height_1; i++) {
            for (int j = 0; j < width_2; j++) {
                for (int k = 0; k < height_2; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                restults[i][j] = sum;
                sum = 0;
            }
        }


        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < height_1; i++) {
            for (int j = 0; j < width_2; j++) {
                // c[i][j] += a[i][j] * b[j][i];
                Log.d("", "multiplyMatrix: " + i+"," + j + "--->" + restults[i][j] + "");
                resultList.add(restults[i][j] + "");
            }
        }


        Intent intent = new Intent(this, MainActivity.class);
        intent.putStringArrayListExtra("resultList", (ArrayList<String>) resultList);
        intent.putExtra("width", width_2);
        startActivity(intent);

    }


    public void updateCellValue(int value, int position) {

        if (isFirst) {
            int x = position / width_1;
            int y = position % width_1;
            matrix1[x][y] = value;
            Log.d("MainActivity", "updateCellValue for first matrix1: " + x + " , " + y);
        } else {
            int x = position / width_2;
            int y = position % width_2;
            matrix2[x][y] = value;
            Log.d("MainActivity", "updateCellValue for second matrix1: " + x + " , " + y);
        }

    }

}
