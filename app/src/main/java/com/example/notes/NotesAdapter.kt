package com.example.notes

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    val allNotes=ArrayList<Note>()
    lateinit var listener:OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currItem : Note=allNotes[position]
        holder.note.text=currItem.text
    }

    override fun getItemCount(): Int = allNotes.size

    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener=listener
    }

    interface OnItemClickListener{
        fun onItemClicked(note:Note)
    }

    inner class NoteViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val note: TextView =itemView.findViewById(R.id.noteText)
        val deleteBtn:ImageView=itemView.findViewById(R.id.deleteIcon)
        init {
            deleteBtn.setOnClickListener {
                listener?.onItemClicked(allNotes[adapterPosition])
            }
        }
    }
}