package com.dannytest.animal.controller

import java.io.Serializable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.dannytest.animal.R
import com.dannytest.animal.databinding.FragmentAnimalDetailBinding
import com.dannytest.animal.model.PetsItem
import com.dannytest.animal.viewModel.AnimalDetailViewModel
import com.squareup.picasso.Picasso

class AnimalDetailFragment : Fragment() {

    private lateinit var binding: FragmentAnimalDetailBinding
    private val viewModel: AnimalDetailViewModel by viewModels()
    private var petsItemList : List<PetsItem>? =null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAnimalDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            val petsItem = bundle.getSerializable("For_Detail") as? PetsItem
            petsItem?.let {
                binding.viewModel?.For_Detail?.value = it

                val imageView = binding.root.findViewById<ImageView>(R.id.De_img)
                Picasso.get().load(it.album_file).into(imageView)

            }
        }
    }
}