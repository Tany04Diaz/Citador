package org.tany.prototipo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button LogInMedico;
    private Button LogInPaciente;
    private Button SingInMedico;
    private Button SingInPaciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LogInMedico = findViewById(R.id.LogInMedico);
        LogInMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(MainActivity.this, DoctorLogin.class );
                startActivity(act);
                Toast.makeText(MainActivity.this, "abriendo Login Medico", Toast.LENGTH_SHORT).show();
            }
        });
        LogInPaciente = findViewById(R.id.LogInPaciente);
        LogInPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(MainActivity.this, PacientLogIn.class);
                startActivity(act);
                Toast.makeText(MainActivity.this, "abriendo Login Paciente", Toast.LENGTH_SHORT).show();
            }
        });
        SingInMedico = findViewById(R.id.SingInMedico);
        SingInMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(MainActivity.this, DoctorRegistration.class);
                startActivity(act);
                Toast.makeText(MainActivity.this, "abriendo Registrar Medico", Toast.LENGTH_SHORT).show();
            }
        });
        SingInPaciente = findViewById(R.id.SingInPaciente);
        SingInPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(MainActivity.this, PacientRegistration.class);
                startActivity(act);
                Toast.makeText(MainActivity.this, "abriendo Registrar Paciente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}