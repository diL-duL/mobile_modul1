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
    private Operasi2Angka operasi2Angka;
    private EditText angka1, angka2;
    private TextView tvResultMath;
    private Button btnMath1, btnMath2, btnMath3, btnMath4;
    private static final String STATE_RESULT_MATH = "state_result_math";

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

        angka1 = findViewById(R.id.angka1);
        angka2 = findViewById(R.id.angka2);
        btnMath1 = findViewById(R.id.btn_Jumlah);
        btnMath2 = findViewById(R.id.btn_Kurang);
        btnMath3 = findViewById(R.id.btn_Kali);
        btnMath4 = findViewById(R.id.btn_Bagi);
        tvResultMath = findViewById(R.id.tv_resultMath);
        operasi2Angka = new Operasi2Angka();



        btnCalculate.setOnClickListener(this); // menghubungkan klik ke interface
        btnMath1.setOnClickListener(this);
        btnMath2.setOnClickListener(this);
        btnMath3.setOnClickListener(this);
        btnMath4.setOnClickListener(this);
        if(savedInstanceState!=null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);

            String result_Math = savedInstanceState.getString(STATE_RESULT_MATH);
            tvResultMath.setText(result_Math);
        }
    }

    @Override // untuk simpan hasil kalau ta refresh/rotate
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
        outState.putString(STATE_RESULT_MATH, tvResultMath.getText().toString());
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
        else if(v.getId() == R.id.btn_Jumlah || v.getId() == R.id.btn_Kurang || v.getId() == R.id.btn_Kali || v.getId() == R.id.btn_Bagi){
            String inputAngka1 = angka1.getText().toString().trim();
            String inputAngka2 = angka2.getText().toString().trim();

            // Validasi input
            boolean isEmptyFields = false;
            if(inputAngka1.isEmpty()) {
                isEmptyFields = true;
                angka1.setError("Field ini tidak boleh kosong");
            }
            if(inputAngka2.isEmpty()) {
                isEmptyFields = true;
                angka2.setError("Field ini tidak boleh kosong");
            }
            if(!isEmptyFields) {
                operasi2Angka.setAngka1(Double.parseDouble(inputAngka1));
                operasi2Angka.setAngka2(Double.parseDouble(inputAngka2));
                double hasil;
                if(v.getId() == R.id.btn_Jumlah){
                    hasil = operasi2Angka.penjumlahan();
                    tvResultMath.setText(String.valueOf(hasil));
                }
                else if(v.getId() == R.id.btn_Kurang){
                    hasil = operasi2Angka.pengurangan();
                    tvResultMath.setText(String.valueOf(hasil));
                }
                else if(v.getId() == R.id.btn_Kali){
                    hasil = operasi2Angka.perkalian();
                    tvResultMath.setText(String.valueOf(hasil));
                }
                else if(v.getId() == R.id.btn_Bagi){
                    hasil = operasi2Angka.pembagian();
                    tvResultMath.setText(String.valueOf(hasil));
                }
            }
        }
    }
}