/*
 * Created by Lee Oh Hyung on 2020/10/24.
 */
package kr.ohyung.mvi.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.ohyung.domain.entity.Forecast
import kr.ohyung.mvi.databinding.ItemCurrentWeatherBinding
import kr.ohyung.mvi.utility.widget.Binder
import kr.ohyung.mvi.utility.widget.HolderItem

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class ViewType(val index: Int) {
        WEATHER(0),
        PHOTO(1)
    }

    private val currentList = arrayListOf<HolderItem>()

    fun submitList(forecast: Forecast) {
        currentList.clear()
        currentList += WeatherViewHolder.Item(forecast = forecast)

        notifyDataSetChanged()
    }

    // For Pagination
    // fun submitList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(ViewType.values()[viewType]) {
            ViewType.WEATHER -> WeatherViewHolder(parent)
            ViewType.PHOTO -> TODO()
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

    private class PhotoViewHolder
}

@BindingAdapter("setForecast")
fun RecyclerView.setForecast(forecast: Forecast?) =
    forecast?.let {
        val adapter = adapter as? HomeAdapter
        adapter?.submitList(it)
    }
