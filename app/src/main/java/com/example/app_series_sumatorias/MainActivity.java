package com.example.app_series_sumatorias;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        EditText numeroText = findViewById(R.id.numero);
        Button generarButton = findViewById(R.id.generar);
        TextView serieText = findViewById(R.id.serie);
        TextView sumaText = findViewById(R.id.suma);
        generarButton.setOnClickListener(v ->{
            String numeroStr = numeroText.getText().toString().trim();
            if (numeroStr.isEmpty()) {
                serieText.setText("Error: campo vacío");
                sumaText.setText("Suma: campo vacío");
                return;
            }
            int n;

            try {
                n = Integer.parseInt(numeroStr);
            } catch (NumberFormatException e) {
                serieText.setText("Error: valor no numérico");
                sumaText.setText("Error: valor no numérico");
                return;
            }

            if (n <= 0) {
                serieText.setText("Error: n debe ser mayor a 0");
                sumaText.setText("Error: n debe ser mayor a 0");

                return;
            }

            String serie = generarSerie(n);
            double suma = calcularSuma(n);

            serieText.setText("Serie: " + serie);
            sumaText.setText("Suma: " + suma);
        });
    }
    public String generarSerie(int n) {
        String resultado = "";
        for (int i = 1; i <= n; i++) {
            double termino = (i * i + 1) / 2.0;

            if (i % 2 == 0) {
                termino = termino * -1;
            }

            if (termino % 1 == 0) {
                resultado = resultado + (int) termino;
            } else {
                resultado = resultado + termino;
            }

            if (i < n) {
                resultado = resultado + ", ";
            }
        }
        return resultado;
    }

    public double calcularSuma(int n){
        double suma = 0;
        for (int i = 1; i <= n; i++) {
            double termino = (i * i + 1) / 2.0;

            if (i % 2 == 0) {
                termino = termino * -1;
            }

            suma = suma + termino;
        }
        return suma;
    }
}