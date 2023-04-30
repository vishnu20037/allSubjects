package com.example.stempediaproject.stempediaproject

import com.google.firebase.firestore.FirebaseFirestore

class SubjectRepository(val firestore: FirebaseFirestore) {
    private var items = mutableListOf<item>()
    fun getList() {
        firestore.collection("items").addSnapshotListener { value, error ->
            if (value == null || error != null) {
                return@addSnapshotListener
            }
            items.addAll(value.toObjects(item::class.java))
        }
    }
}