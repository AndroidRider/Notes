package com.androidrider.notes.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androidrider.notes.ModelClass.NotesModel
import com.androidrider.notes.R
import com.androidrider.notes.ViewModel.NotesViewModel
import com.androidrider.notes.databinding.FragmentEditBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date


class EditFragment : Fragment() {

    lateinit var binding: FragmentEditBinding
    val myNotes by navArgs<EditFragmentArgs>()
    var priority:String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditBinding.inflate(layoutInflater,container,false)

        // Enable the back arrow in the toolbar
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true) // Enable options menu handling

        binding.edtTitle.setText(myNotes.data.title)
        binding.edtSubTitle.setText(myNotes.data.subTitle)
        binding.edtNotes.setText(myNotes.data.notes)

        when(myNotes.data.priority){
            "1"->{
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "2"->{
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "3"->{
                priority = "3"
                binding.pRed.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }

        // When want to change priority in the edit mode
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
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubTitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        val date = Date()
        val notesDate: CharSequence = DateFormat.format(" MMMM d, yyyy ", date.time)

        val data = NotesModel(
            myNotes.data.id,
            title= title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority)

        viewModel.updateNotes(data)

        Toast.makeText(context, "Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.action_delete -> {

                val bottomSheet = BottomSheetDialog(requireContext())
                bottomSheet.setContentView(R.layout.delete_bottom_dialog)

                val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
                val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialogNo)

                textViewYes?.setOnClickListener {
                    viewModel.deleteNotes(myNotes.data.id!!)

                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_editFragment_to_homeFragment)

                    Toast.makeText(
                        requireContext(),
                        "Notes Deleted Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    bottomSheet.dismiss()
                }

                textViewNo?.setOnClickListener {

                    bottomSheet.dismiss()
                }

                bottomSheet.show()
            }
            android.R.id.home -> {
                requireActivity().onBackPressed() // Navigate back to the previous fragment
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}