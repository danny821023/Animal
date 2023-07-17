package com.dannytest.animal.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dannytest.animal.R
import com.dannytest.animal.databinding.FragmentAnimalBinding
import com.dannytest.animal.model.PetOne
import com.dannytest.animal.viewModel.AnimalViewModel


class AnimalFragment : Fragment() {
    private lateinit var binding: FragmentAnimalBinding
    private lateinit var recyclerView: RecyclerView

    var BASE_URL = "https://data.coa.gov.tw/api/v1/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        // SearchView放在畫面頂端時通常會隱藏標題列
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewModel: AnimalViewModel by viewModels()
        binding = FragmentAnimalBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
//        binding.viewModel.fetchDataFromApi()
        recyclerView = binding.root.findViewById(R.id.recyclerView)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            viewModel?._petsItemList?.observe(viewLifecycleOwner) { Pets ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = MyAdapter(Pets)
                } else {
                    (recyclerView.adapter as MyAdapter ).updatePets(Pets)
                }
            }

           searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                   viewModel?.search(newText)
                    return true
                }

                override fun onQueryTextSubmit(text: String?): Boolean {
                    return false
                }

            })
        }
    }
}
