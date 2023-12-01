package com.fersa.alojamiento;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;

public class MainActivity2 extends AppCompatActivity {

    private TextView alojamientoTv;
    private TextView adultosTv;
    private TextView ninyosTv;
    private TextView fDesdeTv;
    private TextView fHastaTv;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        alojamientoTv = findViewById(R.id.tvApartamento);
        adultosTv = findViewById(R.id.tvAdultos);
        ninyosTv = findViewById(R.id.tvNinios);
        fDesdeTv = findViewById(R.id.tvDesde);
        fHastaTv = findViewById(R.id.tvHasta);

        String txtAlojam= getIntent().getStringExtra("TIPOALO");

        String txtAdultos = getIntent().getStringExtra("ADULT");
        if (txtAdultos.equals("1")) txtAdultos+= " adulto";
        else txtAdultos+= " adultos";

        String txtNinyos = getIntent().getStringExtra("NIN");
        if (txtNinyos.equals("0")) txtNinyos= "Sin niños";
        else if (txtNinyos.equals("1")) txtNinyos+= " niño";
        else txtNinyos+= " niños";

        String fdesde = "Desde: " + getIntent().getStringExtra("FECHAINI");
        String fhasta = "Hasta: " + getIntent().getStringExtra("FECHAFIN");

        alojamientoTv.setText(txtAlojam);
        adultosTv.setText(txtAdultos);
        ninyosTv.setText(txtNinyos);
        fDesdeTv.setText(fdesde);
        fHastaTv.setText(fhasta);

    }
}