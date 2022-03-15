package in_.turker.nasarovervehicle.ui.fragment.curiosity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.databinding.ItemPhotoBinding
import in_.turker.nasarovervehicle.utils.loadImagesWithGlide

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

class VehiclePhotoAdapter(private val onClickAction: ((Photo) -> Unit)) :
    PagingDataAdapter<Photo, VehiclePhotoViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: VehiclePhotoViewHolder, position: Int) {
        getItem(position)?.let { result -> holder.bind(result) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclePhotoViewHolder {
        val binding =
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VehiclePhotoViewHolder(binding, onClickAction)
    }

    object Comparator : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Photo,
            newItem: Photo
        ): Boolean {
            return oldItem == newItem
        }
    }
}

class VehiclePhotoViewHolder(
    private val binding: ItemPhotoBinding,
    private val onClickAction: ((Photo) -> Unit)
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Photo) {
        binding.apply {

            binding.imgPhoto.loadImagesWithGlide(item.imgSrc)
            binding.clParent.setOnClickListener {
                onClickAction(item)
            }

        }
    }
}