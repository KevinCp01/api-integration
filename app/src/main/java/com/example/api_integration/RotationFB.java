package com.example.api_integration;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class RotationFB {
    public void saveRotation(Rotation rotation){
        // Create a new clientMap with a first and last name
        Map<String, Object> clientMap = new HashMap<>();
        clientMap.put("z-rotation(°)", rotation.zrot);
        clientMap.put("x-rotation(°)", rotation.xrot);
        clientMap.put("y-rotation(°)", rotation.yrot);

        // Add a new document with a generated ID
        new FirebaseConnection().getConnection().collection("RotationSensorCollection")
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
