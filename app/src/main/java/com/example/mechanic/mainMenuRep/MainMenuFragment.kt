package com.example.mechanic.mainMenuRep;

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mechanic.databinding.FragmentMainMenuBinding
import com.example.mechanic.R


class MainMenuFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

       // return inflater.inflater(R.layout.fragment_main_menu, container, false)


      /*  val binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(inflater, R.layout.fragment_main_menu, container, false)
        binding.listCarsB.setOnClickListener { view: View ->
            view.findNavController().navigate(MainMenuFragmentDirections.actionMainMenuToCarList())
        }
        setHasOptionsMenu(true)*/
        val binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(inflater, R.layout.fragment_main_menu, container, false)

        binding.listCarsB.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainMenu_to_carsList)      //MainMenuFragmentDirections.actionMainMenuToCarsList())
        }
       // setHasOptionsMenu(true)
        binding.createCarB.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainMenu_to_createEntry2)      //MainMenuFragmentDirections.actionMainMenuToCarsList())
        }

        return binding.root

    }

}
