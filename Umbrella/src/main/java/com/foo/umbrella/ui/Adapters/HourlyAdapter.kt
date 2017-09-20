package com.foo.umbrella.ui.Adapters

/**
 * Created by vincentvillalta on 9/19/17.
 */
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.foo.umbrella.R
import com.foo.umbrella.data.model.ForecastCondition
import com.foo.umbrella.utilities.getColorCompat
import com.foo.umbrella.utilities.inflate
import kotlinx.android.synthetic.main.hourly_individual_item.view.hour
import kotlinx.android.synthetic.main.hourly_individual_item.view.icon
import kotlinx.android.synthetic.main.hourly_individual_item.view.temperature

class HourlyAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var minimunTemperature: ForecastCondition? = null
    private var maximunTemperature: ForecastCondition? = null

    var forecast: List<ForecastCondition> = emptyList()
        set(value) {
            minimunTemperature = value.minBy { it.tempCelsius.toFloat() }
            maximunTemperature = value.maxBy { it.tempCelsius.toFloat() }
            field = value
        }
        get() = field

    var showCelsius: Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForecastHolder(parent.inflate(R.layout.hourly_individual_item))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with(forecast.get(position)) {
        with(holder as ForecastHolder) {
            forecast.maxBy { it.tempCelsius.toFloat() }
            timeLabel.text = displayTime
            val tempUnit = if (showCelsius) tempCelsius else tempFahrenheit
            temperatureLabel.text = temperatureLabel.context.getString(R.string.temperature, tempUnit)
            iconImageView.setImageResource(getDisplayIcon(icon))

            if (forecast.get(position).equals(minimunTemperature) && forecast.get(position).equals(maximunTemperature)) { // IN case the min and max are in the same position
                setTintColor(holder, R.color.default_color)
            }
            if (forecast.get(position).equals(minimunTemperature)) {
                setTintColor(holder, R.color.weather_cool)
            }
            if (forecast.get(position).equals(maximunTemperature)) {
                setTintColor(holder, R.color.weather_warm)
            }
        }
    }

    fun setTintColor(holder: RecyclerView.ViewHolder, colorResId: Int) = with(holder as ForecastHolder) {
        var color = iconImageView.context.getColorCompat(colorResId)
        iconImageView.setColorFilter(color)
        timeLabel.setTextColor(color)
        temperatureLabel.setTextColor(color)
    }

    class ForecastHolder(view: View): RecyclerView.ViewHolder(view) {
        val timeLabel: TextView = view.hour
        val temperatureLabel: TextView = view.temperature
        val iconImageView: ImageView = view.icon
    }

    fun getDisplayIcon(icon: String) = when (icon) {
        "cloudy" -> R.drawable.weather_cloudy
        "fog" -> R.drawable.weather_fog
        "sleet" -> R.drawable.weather_hail
        "chancetstorms" -> R.drawable.weather_lightning
        "tstorms" -> R.drawable.weather_lightning_rainy
        "partlycloudy" -> R.drawable.weather_partlycloudy
        "rain" -> R.drawable.weather_rainy
        "chancerain" -> R.drawable.weather_rainy
        "snow" -> R.drawable.weather_snowy
        "chancesnow" -> R.drawable.weather_snowy
        "hazy" -> R.drawable.weather_snowy_rainy
        "clear" -> R.drawable.weather_sunny
        "sunny" -> R.drawable.weather_sunny
        else -> R.drawable.weather_sunny
    }

    override fun getItemCount() = forecast.count()



}
