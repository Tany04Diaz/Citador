package org.tany.prototipo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

public class DoctorRegistration extends AppCompatActivity {
    EditText fehcaNac;
    Button btnApertura;
    Button btnCierre;
    private String horarioApertura = " ";
    private String horarioCierre = " ";
    private String EspecialidadSeleccionada= " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fehcaNac = findViewById(R.id.fechaNac);
        fehcaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        //BtnApertura
        btnApertura = findViewById(R.id.btnHoraApertura);
        btnApertura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirReloj(new OnTimeSelectedListener() {
                    @Override
                    public void onSelected(String hora) {
                        horarioApertura = hora;
                        btnApertura.setText("Horario Apertura: "+ hora);
                    }
                });
            }
        });
        //BtnCierre
        btnCierre = findViewById(R.id.btnHoraCierre);
        btnCierre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirReloj(new OnTimeSelectedListener() {
                    @Override
                    public void onSelected(String hora) {
                        horarioCierre = hora;
                        btnCierre.setText("Horario Cierre: "+ hora);
                    }
                });
            }
        });
        //SpinnerExpecialidades
        Spinner spinner = findViewById(R.id.Especialidades);
        String[] opciones = {
          "Seleccionar especialidad",
          "Medicina General",
          "Cardiologia",
          "Neumologia"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EspecialidadSeleccionada = opciones[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    //Calendario
    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    fehcaNac.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }
    //Reloj
    private void abrirReloj(final OnTimeSelectedListener listener) {
        Calendar c = Calendar.getInstance();
        int horaActual = c.get(Calendar.HOUR_OF_DAY);
        int minutoActual = c.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            String horaFormateada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
            listener.onSelected(horaFormateada);
        }, horaActual, minutoActual, true); // true para formato 24h

        timePicker.show();
    }
}
interface OnTimeSelectedListener {
    void onSelected(String hora);
}
