package com.example.stempediaproject.stempediaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stempediaproject.R
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    private var items = mutableListOf<item>()
    lateinit var adap: Adap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        items.add(item("physics"))
        items.add(item("chemistry"))
        items.add(item("biology"))
        items.add(item("maths"))
        setUPFireStore()
        setUpRV()
    }

    private fun setUPFireStore() {
        firestore = FirebaseFirestore.getInstance()
        Log.d("DATA", items.toString())
        val collection = firestore.collection("items")
        collection.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(item::class.java).toString())
            items.clear()
            items.addAll(value.toObjects(item::class.java))
            adap.notifyDataSetChanged()
        }
    }

    private fun setUpRV() {
        adap = Adap(this, items)
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adap
    }

}