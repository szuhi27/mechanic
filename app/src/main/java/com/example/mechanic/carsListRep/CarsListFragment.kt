package com.example.mechanic.carsListRep

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mechanic.R
import com.example.mechanic.databinding.FragmentCarsListBinding
import androidx.navigation.findNavController
import com.example.mechanic.createEntryRep.CreateEntryViewModel
import com.example.mechanic.createEntryRep.CreateEntryViewModelFactory
import com.example.mechanic.database.CarsDatabase
import com.google.android.material.snackbar.Snackbar

class CarsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentCarsListBinding>(
            inflater, R.layout.fragment_cars_list, container, false
        )

        binding.homeB.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_carsList_to_mainMenu)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = CarsDatabase.getInstance(application).carsDatabaseDao
        val viewModelFactory = CarsListViewModelFactory(dataSource,application)

        val carsListViewModel = ViewModelProvider(
                this, viewModelFactory).get(CarsListViewModel::class.java)

        binding.carsListViewModel = carsListViewModel

        val adapter = CarsAdapter()
        binding.listOfCars.adapter = adapter

        carsListViewModel.cars.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner(this)

        /*carsListViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if(it == true){
                Snackbar.make{
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                }.show()
                carsListViewModel.doneShowingSnackbar()
            }
        })*/


        return binding.root
    }

}