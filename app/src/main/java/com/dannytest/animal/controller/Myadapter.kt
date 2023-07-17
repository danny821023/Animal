package com.dannytest.animal.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dannytest.animal.R
import com.dannytest.animal.databinding.AnimalItemViewBinding
import com.dannytest.animal.model.PetsItem
import com.dannytest.animal.viewModel.AnimalViewModel


class MyAdapter(var pets: List<PetsItem>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    fun updatePets(pets: List<PetsItem>) {
        this.pets = pets
        /*notifyDataSetChanged()*/
    }

    class MyViewHolder(val binding: AnimalItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        fun bind(position: Int, viewModel: AnimalViewModel) {
//            val luvpet = pets.Data?.get(position)
//            petItem?.let {
//                Glide.with(binding.root)
//
//
//                binding.RVBodytype.text = it.animal_bodytype
//                binding.RVAge.text = it.animal_age
//                binding.RVColor.text = it.animal_colour
//                binding.RVSheltername.text = it.shelter_name
//                binding.RVDatefound.text= it.cDate
//                // 其他视图绑定代码...
//            }
//        }
    }

    override fun getItemCount(): Int {
        return pets.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemViewBinding = AnimalItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        itemViewBinding.viewModel = AnimalViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()



        return MyViewHolder(itemViewBinding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val luvpet = pets?.get(position)
        Log.d("DEV DEV", luvpet.toString())

        with(holder) {
            val PetsItem = listOf(luvpet)

            holder.binding.RVAge.text = luvpet?.animal_age ?: "--"
            holder.binding.RVColor.text = luvpet?.animal_colour ?: "--"
            holder.binding.RVSheltername.text = luvpet?.shelter_name ?: "--"
            holder.binding.RVBodytype.text = luvpet?.animal_bodytype ?: "--"
            holder.binding.RVDatefound.text = luvpet?.cDate ?: "--"

            Glide.with(holder.itemView)
                .load(luvpet?.album_file)
//                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.binding.RVImage)

            holder.binding.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("For_Detail", luvpet)
                Navigation.findNavController(it)
                    .navigate(R.id.animalDetailFragment, bundle)
            }
        }
    }
}