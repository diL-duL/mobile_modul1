package com.fadilwirawan.kalkulatorvolume;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtWidth,edtHeight, edtLength;
    private Button btnCalculate;
    private TextView tvResult;
    private BalokModel balokModel;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        balokModel = new BalokModel(); // membuat objek dari class
        btnCalculate.setOnClickListener(this); // menghubungkan klik ke interface

        if(savedInstanceState!=null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            // Validasi input
            boolean isEmptyFields = false;
            if (inputLength.isEmpty()) {
                isEmptyFields = true;
                edtLength.setError("Field ini tidak boleh kosong");
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true;
                edtWidth.setError("Field ini tidak boleh kosong");
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true;
                edtHeight.setError("Field ini tidak boleh kosong");
            }

            if (!isEmptyFields) {
                // Menggunakan method setter dari object balokModel
                balokModel.setPanjang(Double.parseDouble(inputLength));
                balokModel.setLebar(Double.parseDouble(inputWidth));
                balokModel.setTinggi(Double.parseDouble(inputHeight));

                // Menggunakan method hitungVolume dari object balokModel
                double volume = balokModel.hitungVolume();
                tvResult.setText(String.valueOf(volume));
            }
        }
    }
}