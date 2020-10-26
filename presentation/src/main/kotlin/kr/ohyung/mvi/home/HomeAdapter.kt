/*
 * Created by Lee Oh Hyung on 2020/10/24.
 */
package kr.ohyung.mvi.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ohyung.domain.entity.Forecast
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.mvi.R
import kr.ohyung.mvi.databinding.ItemCurrentWeatherBinding
import kr.ohyung.mvi.databinding.ItemPhotoBinding
import kr.ohyung.mvi.utility.extension.load
import kr.ohyung.mvi.utility.widget.Binder
import kr.ohyung.mvi.utility.widget.HolderItem

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType(val index: Int) {
        WEATHER(0),
        PHOTO(1)
    }

    private val currentList = arrayListOf<HolderItem>()

    fun submitList(forecast: Forecast, photos: List<PhotoSummary>) {
        currentList.clear()
        currentList += WeatherViewHolder.Item(forecast = forecast)
        currentList += photos.map { photo -> PhotoViewHolder.Item(photos = photo) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (ViewType.values()[viewType]) {
            ViewType.WEATHER -> WeatherViewHolder(parent)
            ViewType.PHOTO -> PhotoViewHolder(parent)
        }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Binder<HolderItem>)?.bindTo(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    override fun getItemViewType(position: Int): Int = currentList[position].viewType

    private class WeatherViewHolder(
        parent: ViewGroup,
        private val binding: ItemCurrentWeatherBinding =
            ItemCurrentWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) : RecyclerView.ViewHolder(binding.root), Binder<WeatherViewHolder.Item> {

        class Item(val forecast: Forecast) : HolderItem(ViewType.WEATHER.index)

        override fun bindTo(item: Item) = with(binding) {
            this.forecast = item.forecast
            executePendingBindings()
        }
    }

    private class PhotoViewHolder(
        parent: ViewGroup,
        private val binding: ItemPhotoBinding =
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) : RecyclerView.ViewHolder(binding.root), Binder<PhotoViewHolder.Item> {

        class Item(val photos: PhotoSummary) : HolderItem(ViewType.PHOTO.index)

        override fun bindTo(item: Item) = with(binding) {
            this.photo = item.photos
            ivThumbnail.load(item.photos.thumbnail) {
                centerCrop()
                placeholder(R.color.gray_400)
                error(R.color.gray_400)
            }
            executePendingBindings()
        }
    }
}
