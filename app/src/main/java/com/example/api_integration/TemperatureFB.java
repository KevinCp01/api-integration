package com.example.api_integration;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class TemperatureFB {
    public void saveTemperature(Temperature temperature){
        // Create a new clientMap with a first and last name
        Map<String, Object> clientMap = new HashMap<>();
        clientMap.put("temperature(°C)", temperature.temperature);

        // Add a new document with a generated ID
        new FirebaseConnection().getConnection().collection("TemperatureSensorCollection")
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
