package com.example.mechanic.createEntryRep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mechanic.R
import com.example.mechanic.database.CarsDatabase
import com.example.mechanic.databinding.FragmentCreateEntryBinding

class CreateEntryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentCreateEntryBinding>(
            inflater, R.layout.fragment_create_entry, container, false
        )

        binding.HomeB.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_createEntry_to_mainMenu)      //MainMenuFragmentDirections.actionMainMenuToCarsList())
        }

        val application = requireNotNull(this.activity).application
        val dataSource = CarsDatabase.getInstance(application).carsDatabaseDao
        val viewModelFactory = CreateEntryViewModelFactory(dataSource)

        val createEntryViewModel = ViewModelProvider(
            this, viewModelFactory).get(CreateEntryViewModel::class.java)

        binding.createEntryViewModel = createEntryViewModel



        return binding.root
    }


}