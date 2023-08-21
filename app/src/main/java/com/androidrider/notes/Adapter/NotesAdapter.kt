package com.androidrider.notes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.androidrider.notes.Fragments.HomeFragmentDirections
import com.androidrider.notes.ModelClass.NotesModel
import com.androidrider.notes.R
import com.androidrider.notes.databinding.ItemListBinding

class NotesAdapter(val requireContext: Context, var notesList: List<NotesModel>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val data = notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubTitle.text = data.subTitle
        holder.binding.notesDate.text = data.date

        // setting the priority on recyclerView
        when(data.priority){
            "1"->{holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)}
            "2"->{holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)}
            "3"->{holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)}
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    class NotesViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){

    }

    fun filtering(newFilteredList: ArrayList<NotesModel>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }
}