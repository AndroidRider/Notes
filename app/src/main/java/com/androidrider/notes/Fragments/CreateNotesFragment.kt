package com.androidrider.notes.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.androidrider.notes.ModelClass.NotesModel
import com.androidrider.notes.R
import com.androidrider.notes.ViewModel.NotesViewModel
import com.androidrider.notes.databinding.FragmentCreateNotesBinding
import java.util.Date


class CreateNotesFragment : Fragment() {

    private lateinit var binding: FragmentCreateNotesBinding

    var priority:String = "1"
    val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        // set green default
        binding.pGreen.setImageResource(R.drawable.baseline_done_24)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.btnSave.setOnClickListener {
            //function calling
            CreateNotes(it)
        }



        return binding.root
    }

    private fun CreateNotes(it: View?) {

        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubTitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        val date = Date()
        val notesDate: CharSequence = DateFormat.format(" MMMM d, yyyy ", date.time)

        val data = NotesModel(
            null,
            title= title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority)

        viewModel.addNotes(data)

        Toast.makeText(context, "Notes Created Successfully", Toast.LENGTH_SHORT).show()

        // Navigating
        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)

    }
}


