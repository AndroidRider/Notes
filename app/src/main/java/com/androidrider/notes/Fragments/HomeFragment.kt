package com.androidrider.notes.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidrider.notes.Adapter.NotesAdapter
import com.androidrider.notes.ModelClass.NotesModel
import com.androidrider.notes.R
import com.androidrider.notes.ViewModel.NotesViewModel
import com.androidrider.notes.databinding.FragmentHomeBinding
import java.util.Locale


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var notesAdapter: NotesAdapter
    private val viewModel: NotesViewModel by viewModels()
    private var myNotesList = arrayListOf<NotesModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        // Layout Manager
//        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
//        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(),2,LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)


        // get All Notes from database
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            myNotesList = notesList as ArrayList<NotesModel>
            notesAdapter = NotesAdapter(requireContext(),notesList)
            binding.recyclerview.adapter= notesAdapter
        }

        // filter all notes
        binding.filterAllNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                myNotesList = notesList as ArrayList<NotesModel>
                notesAdapter = NotesAdapter(requireContext(),notesList)
                restoreStaggeredGridLayout()// When use StaggeredGridLayout
                binding.recyclerview.adapter= notesAdapter
            }
        }

        // filter High priority
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                myNotesList = notesList as ArrayList<NotesModel>
                notesAdapter = NotesAdapter(requireContext(),notesList)
                restoreStaggeredGridLayout()// When use StaggeredGridLayout
                binding.recyclerview.adapter= notesAdapter
            }
        }

        // filter Medium priority
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                myNotesList = notesList as ArrayList<NotesModel>
                notesAdapter = NotesAdapter(requireContext(),notesList)
                restoreStaggeredGridLayout()// When use StaggeredGridLayout
                binding.recyclerview.adapter= notesAdapter
            }
        }

        // filter Low priority
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                myNotesList = notesList as ArrayList<NotesModel>
                notesAdapter = NotesAdapter(requireContext(),notesList)
                restoreStaggeredGridLayout()// When use StaggeredGridLayout
                binding.recyclerview.adapter= notesAdapter
            }
        }

        // Navigating Home to create Fragment
        binding.btnAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search notes here..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchFilterNotes(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    // Search Bar filter
    fun searchFilterNotes(newText:String?) {

        val newFilteredList = arrayListOf<NotesModel>()

        if (newText!!.isBlank()) {
            // If search query is blank, show all items
            newFilteredList.addAll(myNotesList)
        }
        else{
            for (item in myNotesList) {
                if (item.title.lowercase(Locale.ROOT).contains(newText.lowercase(Locale.getDefault())) ||
                    item.subTitle.lowercase(Locale.ROOT).contains(newText.lowercase(Locale.getDefault()))) {
                    newFilteredList.add(item)
                }
            }
        }
        notesAdapter.filtering(newFilteredList)
    }



//    when we perform filter operation then StaggeredGridLayout conflicting ->
//    so for that this code (only use for StaggeredGridLayout -> Other Layout working fine rest of the code )
private fun restoreStaggeredGridLayout(){
        // Store the current scroll position
        val layoutManagerState = binding.recyclerview.layoutManager?.onSaveInstanceState()
        // Restore the scroll position
        binding.recyclerview.layoutManager?.onRestoreInstanceState(layoutManagerState)
    }

}