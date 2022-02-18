package com.example.api_integration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Objeto de la clase SensorManager, ademas de otros 3 de la clase Sensor
    private SensorManager sensorManager;
    private Sensor temperature, rotation, accelerometer;

    private TextView tempTextView,
            zrotTextView, xrotTextView,yrotTextView,
            zAxisTextView, xAxisTextView,yAxisTextView;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Relacionar con las id's de los TexView
        tempTextView = findViewById(R.id.tempTextView);
        zrotTextView= findViewById(R.id.zrotTextView);
        yrotTextView = findViewById(R.id.yrotTextView);
        xrotTextView = findViewById(R.id.xrotTextView);
        zAxisTextView= findViewById(R.id.zAxisTextView);
        yAxisTextView = findViewById(R.id.yAxisTextView);
        xAxisTextView = findViewById(R.id.xAxisTextView);


        // Asignamos a nuestro objeto sensorManager el servicio de sensores
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Asignamos el valor de cada sensor a una variable especifica dependiendo del tipo de sensor
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    // Metodo que ejecuta un evento si un sensor cambia de valor
    @Override
    public void onSensorChanged(SensorEvent event) {



        // Obtenemos el tipo de sensor en variable
        int sensorType = event.sensor.getType();

        // Dependiendo del sensor obtenido realizamos cambios en el TextView correspondiente a cada tipo de sensor.
        switch (sensorType) {

            case Sensor.TYPE_AMBIENT_TEMPERATURE:

                // Mostramos valores en el activity_main
                tempTextView.setText(event.values[0] + " °C");

                // Se instancian las clases de TemperatureFB y Temperature
                TemperatureFB temperatureFB = new TemperatureFB();
                Temperature temperature = new Temperature();

                // Se asigna nuestro valor a la variable del objeto temperature de la clase Temperature
                temperature.temperature = event.values[0];

                // Se manda llamar a la funcion de saveTemperature enviando nuestro objeto temperature
                temperatureFB.saveTemperature(temperature);
                break;

            case Sensor.TYPE_ROTATION_VECTOR:

                // Mostramos valores en el activity_main
                zrotTextView.setText(event.values[2] + " °");
                xrotTextView.setText(event.values[0] + " °");
                yrotTextView.setText(event.values[1] + " °");

                //Se instancian las clases de RotationFB y Rotation
                RotationFB rotationFB = new RotationFB();
                Rotation rotation = new Rotation();

                // Se asignan los valores a las variables al objeto rotation de la clase Rotation
                rotation.zrot = event.values[2];
                rotation.xrot = event.values[0];
                rotation.yrot = event.values[1];

                // Se manda llamar a la funcion de saveRotation enviando nuestro objeto rotation
                rotationFB.saveRotation(rotation);

                break;

            case Sensor.TYPE_ACCELEROMETER:

                // Mostramos valores en el activity_main
                zAxisTextView.setText(event.values[2] + " m/s^2");
                xAxisTextView.setText(event.values[0] + " m/s^2");
                yAxisTextView.setText(event.values[1] + " m/s^2");

                //Se instancian las clases de AccelerometerFB y Accelerometer
                AccelerometerFB accelerometerFB = new AccelerometerFB();
                Accelerometer accelerometer = new Accelerometer();

                // Se asignan los valores a las variables al objeto accelerometer de la clase Accelerometer
                accelerometer.zAxis = event.values[2];
                accelerometer.xAxis = event.values[0];
                accelerometer.yAxis = event.values[1];

                // Se manda llamar a la funcion saveAccelerometer enviando el objeto accelerometer
                accelerometerFB.saveAccelerometer(accelerometer);

                break;

            default:
                break;
        }





    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {

        // Registrar un listener para cada sensor.
        super.onResume();

        // Si algun sensor no es nulo se ejecutara el metodo onSensorChange con su respectivo value de sensor cada 5,000,000 microsegundos = 5 seg.
        if(temperature != null){
            sensorManager.registerListener(this,temperature, 5000000);
        }
        if(rotation != null){
            sensorManager.registerListener(this,rotation, 5000000);
        }
        if(accelerometer != null){
            sensorManager.registerListener(this,accelerometer, 5000000);
        }

    }

    @Override
    protected void onPause() {

        // Deja de registrar el sensor cuando el activiy deja de operar
        super.onPause();
            sensorManager.unregisterListener(this);

    }
}