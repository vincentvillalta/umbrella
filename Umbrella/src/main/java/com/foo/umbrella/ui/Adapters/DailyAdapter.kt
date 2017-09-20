package com.foo.umbrella.ui.Adapters

/**
 * Created by vincentvillalta on 9/19/17.
 */
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.foo.umbrella.R
import com.foo.umbrella.data.model.DailyForecast
import com.foo.umbrella.utilities.inflate
import kotlinx.android.synthetic.main.daily_forecast.view.hours
import kotlinx.android.synthetic.main.daily_forecast.view.title

class DailyAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val NUMBER_OF_COLUMNS = 4
    }

    var showCelsius: Boolean = false
    var forecasts: List<DailyForecast> = emptyList()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) = with(holder as DailyForecastHolder) {
        with(forecasts.get(position)) {
            titleTextView.text = title
            val adapter = HourlyAdapter()
            adapter.forecast = forecast
            adapter.showCelsius = showCelsius
            hourlyForecastListView.layoutManager = GridLayoutManager(hourlyForecastListView.context, NUMBER_OF_COLUMNS)
            hourlyForecastListView.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DailyForecastHolder(parent.inflate(R.layout.daily_forecast))

    override fun getItemCount() = forecasts.count()

    class DailyForecastHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.title
        val hourlyForecastListView: RecyclerView = view.hours
    }
}
