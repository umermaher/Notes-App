package com.example.notes

import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NotesAdapter.OnItemClickListener {
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRecyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesAdapter(this)
        adapter.setOnItemClickListener(this)
        noteRecyclerView.adapter=adapter

//        viewModel=ViewModelProvider(this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel= NoteViewModel(this.application)
        viewModel.allNotes.observe(this, Observer {
            it?.let {
                //if list is not null
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.delete(note)
        Toast.makeText(this,"${note.text} deleted!",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: android.view.View) {
        val note=noteEditText.text.toString()
        if(note.isNotEmpty()){
            viewModel.insert(Note(note))
            Toast.makeText(this,"$note inserted!",Toast.LENGTH_LONG).show()
        }
    }
}