package com.example.api_integration;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseConnection {
    public FirebaseFirestore getConnection(){

        return FirebaseFirestore.getInstance();

    }
}
