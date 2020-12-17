package com.example.mechanic.carDetailRep

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mechanic.R
import com.example.mechanic.database.CarsDatabase
import com.example.mechanic.databinding.FragmentCarDetailBinding

class CarDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding: FragmentCarDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_car_detail, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = CarDetailFragmentArgs.fromBundle(requireArguments())

        val dataSource = CarsDatabase.getInstance(application).carsDatabaseDao
        val viewModelFactory = CarDetailViewModelFactory(arguments.carKey, dataSource)

        val carDetailViewModel = ViewModelProvider(
            this, viewModelFactory).get(CarDetailViewModel::class.java)

        binding.carDetailViewModel = carDetailViewModel

        binding.setLifecycleOwner(this)

        carDetailViewModel.navigateToCarsList.observe(viewLifecycleOwner, Observer{
            if(it == true){
                this.findNavController().navigate(
                    CarDetailFragmentDirections.actionCarDetailToCarsList()
                )
                carDetailViewModel.doneNavigating()
            }
        })

        return binding.root
    }


}