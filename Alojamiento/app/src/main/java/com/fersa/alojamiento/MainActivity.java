package com.fersa.alojamiento;

import static com.fersa.alojamiento.R.id.spTipoAlojamiento;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner tipoAloSp;
    private Spinner adultosSp;
    private Spinner ninyosSp;
    private ImageButton btnFechaIni;
    private ImageButton btnFechaFin;
    private static TextView txtFechaIni;
    private static TextView txtFechaFin;
    private static boolean desde=true;
    private Button btnEnviar;
    private static int dia1=1;
    private static int mes1=1;
    private static int anyo1=1000;
    private static int dia2=1;
    private static int mes2=1;
    private static int anyo2=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipoAloSp = (Spinner) findViewById(spTipoAlojamiento);
        adultosSp = (Spinner) findViewById(R.id.spAdultos);
        ninyosSp = (Spinner) findViewById(R.id.spNinyos);
        btnFechaIni = findViewById(R.id.btnDesde);
        btnFechaFin = findViewById(R.id.btnHasta);
        txtFechaIni = findViewById(R.id.tvDesde);
        txtFechaFin = findViewById(R.id.tvHasta);
        btnEnviar = findViewById(R.id.btnEnviar);

        ArrayAdapter<CharSequence> adapterAlo = ArrayAdapter.createFromResource(this,
                R.array.tipoAlojamiento, android.R.layout.simple_spinner_item);
        adapterAlo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoAloSp.setAdapter(adapterAlo);

        ArrayAdapter<CharSequence> adapterAdul = ArrayAdapter.createFromResource(this,
                R.array.adulto, android.R.layout.simple_spinner_item);
        adapterAdul.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adultosSp.setAdapter(adapterAdul);

        ArrayAdapter<CharSequence> adapterNin = ArrayAdapter.createFromResource(this,
                R.array.ninyo, android.R.layout.simple_spinner_item);
        adapterNin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ninyosSp.setAdapter(adapterNin);


        btnFechaIni.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                desde = true;
                // Code here executes on main thread after user presses button
                DatePickerFragment dpfDesde = new DatePickerFragment();
                dpfDesde.show(getSupportFragmentManager(), "Desde");
            }
        });

        btnFechaFin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                desde = false;
                // Code here executes on main thread after user presses button
                DatePickerFragment dpfHasta = new DatePickerFragment();
                dpfHasta.show(getSupportFragmentManager(), "Hasta");
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {

                String alojamiento = tipoAloSp.getSelectedItem().toString();
                String adultos = adultosSp.getSelectedItem().toString();
                String ninyos = ninyosSp.getSelectedItem().toString();

                LocalDate fdesde= LocalDate.of(anyo1,mes1,dia1);
                LocalDate fhasta= LocalDate.of(anyo2,mes2,dia2);


                if (alojamiento.equals("Tipo de alojamiento")){

                    Toast.makeText(MainActivity.this, "Falta alojamiento por seleccionar", Toast.LENGTH_LONG).show();
                }
                else if(adultos.equals("Adultos")){

                    Toast.makeText(MainActivity.this, "Falta adultos por seleccionar", Toast.LENGTH_LONG).show();
                }
                else if(ninyos.equals("Niños")){

                    Toast.makeText(MainActivity.this, "Falta niños por seleccionar", Toast.LENGTH_LONG).show();
                }
                else if(anyo1==1000){

                    Toast.makeText(MainActivity.this, "Fecha desde no seleccionada", Toast.LENGTH_LONG).show();
                }
                else if(anyo2==1000){

                    Toast.makeText(MainActivity.this, "Fecha hasta no seleccionada", Toast.LENGTH_LONG).show();
                }
                else if (fhasta.isBefore(fdesde)){

                    Toast.makeText(MainActivity.this, "Fecha hasta anterior a fecha desde", Toast.LENGTH_LONG).show();

                }
                else{

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("TIPOALO", alojamiento);
                    intent.putExtra("ADULT", adultos);
                    intent.putExtra("NIN", ninyos);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String fdesdeS = fdesde.format(formatter);
                    String fhastaS = fhasta.format(formatter);

                    intent.putExtra("FECHAINI", fdesdeS);
                    intent.putExtra("FECHAFIN", fhastaS);
                    startActivity(intent);



                }

            }
        });

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            if (desde) {
                dia1 = day;
                month++;
                mes1 = month;
                anyo1 = year;
                txtFechaIni.setText(dia1 +"/" + mes1 + "/" + anyo1);
            }
            else {
                dia2 = day;
                month++;
                mes2 = month;
                anyo2 = year;
                txtFechaFin.setText(dia2 + "/" + mes2 + "/" + anyo2);
            }
        }
    }

}