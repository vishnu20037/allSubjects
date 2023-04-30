package com.example.stempediaproject.stempediaproject
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class SubjectViewModel (val context: Context): ViewModel() {
    val firestore = FirebaseFirestore.getInstance()
    val collection = firestore.collection("items")
    var items = mutableListOf<item>()
    fun setListItems(items: List<item>) {
        this.items.addAll(items)
    }
    val _items = MutableLiveData<List<item>>()
    val item: LiveData<List<item>>
            get()= _items
}