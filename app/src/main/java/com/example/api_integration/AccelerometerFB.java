package com.example.api_integration;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class AccelerometerFB {

    public void saveAccelerometer(Accelerometer accelerometer){
        // Create a new clientMap with a first and last name
        Map<String, Object> clientMap = new HashMap<>();
        clientMap.put("z-axis(m/s^2)",accelerometer.zAxis);
        clientMap.put("x-axis(m/s^2)", accelerometer.xAxis);
        clientMap.put("y-axis(m/s^2)", accelerometer.yAxis);

        // Add a new document with a generated ID
        new FirebaseConnection().getConnection().collection("AccelerometerSensorCollection")
                .add(clientMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}
