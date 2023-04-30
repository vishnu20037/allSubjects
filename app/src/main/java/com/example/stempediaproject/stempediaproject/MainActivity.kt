package com.example.stempediaproject.stempediaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stempediaproject.R
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    lateinit var adap: Adap
    lateinit var subjectViewModel: SubjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subjectViewModel =
            ViewModelProvider(this, SubjectViewModelFactory(application)).get(SubjectViewModel::class.java)
        setUPFireStore()
        setUpRV()
    }

    private fun setUPFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collection = firestore.collection("items")
        collection.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                return@addSnapshotListener
            }
            subjectViewModel.setListItems(value.toObjects(item::class.java))
            adap.notifyDataSetChanged()
        }
    }

    private fun setUpRV() {
        adap = Adap(this, subjectViewModel.items)
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adap
    }

}