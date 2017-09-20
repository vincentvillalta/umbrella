package com.foo.umbrella.ui

/**
 * Created by vincentvillalta on 9/19/17.
 */
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.foo.umbrella.R
import com.foo.umbrella.data.ApiServicesProvider
import com.foo.umbrella.data.model.ForecastCondition
import com.foo.umbrella.data.model.WeatherData
import com.foo.umbrella.presenter.Presenter
import com.foo.umbrella.ui.Adapters.DailyAdapter
import com.foo.umbrella.utilities.getColorCompat
import com.foo.umbrella.utilities.launchActivity
import com.foo.umbrella.utilities.showToast
import kotlinx.android.synthetic.main.activity_main.daysList
import kotlinx.android.synthetic.main.activity_main.description
import kotlinx.android.synthetic.main.activity_main.header
import kotlinx.android.synthetic.main.activity_main.temperature
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.TextStyle
import java.util.Locale
import android.text.Spannable
import android.graphics.Color
import android.text.style.ForegroundColorSpan
import android.text.SpannableString
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem


class MainActivity: AppCompatActivity() {

    private val presenter by lazy { Presenter(ApiServicesProvider(application)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setTitle("")
    }

    override fun onStart() {
        super.onStart()
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val zip = preferences.getString(getString(R.string.settings_zip_key), getString(R.string.default_zipcode))
        val unit = preferences.getString(getString(R.string.settings_units_key), getString(R.string.unit_celsius))
        presenter.fetchData(zip, { updateUI(it, unit) }, this::showMessage)

        daysList.layoutManager = LinearLayoutManager(this)
        daysList.adapter = DailyAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = this.menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        launchActivity(SettingsActivity::class.java)
        return true
    }

    // After presenter emmit an event, update the UI for it
    /**
     * After the presenter emmit a signal the UI should be updated
     * It updates the action bar title and the current temp color for the header
     */
    private fun updateUI(weatherData: WeatherData, unit: String) {
        val isCelsius = unit.equals(getString(R.string.unit_celsius))
        with(weatherData.currentObservation) {
            val temperatureUnit = if (isCelsius) tempCelsius else tempFahrenheit
            val temperatureRoundUp = Math.round(temperatureUnit.toFloat())
            temperature.text = getString(R.string.temperature, temperatureRoundUp)
            supportActionBar?.setTitle(displayLocation.fullName)
            description.text = weatherDescription
            header.setBackgroundColor(getColorCompat(presenter.getTemperatureColor(tempFahrenheit)))
            findViewById(R.id.loadingPanel).setVisibility(View.GONE)
        }

        with(daysList.adapter as DailyAdapter) {
            forecasts = weatherData.forecast.groupBy { it.dateTime.dayOfYear }.map { com.foo.umbrella.data.model.DailyForecast(getCardTitle(it.value.first()), it.value) }
            showCelsius = isCelsius
            notifyDataSetChanged()
        }
    }

    /**
     * Determine the value for the label on the card
     */
    private fun getCardTitle(forecastCondition: ForecastCondition): String {
        val today = LocalDateTime.now().dayOfYear
        return when (forecastCondition.dateTime.dayOfYear) {
            today -> getString(R.string.today)
            today + 1 -> getString(R.string.tomorrow)
            else -> forecastCondition.dateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        }
    }


    /**
     * If the signal emmits an error the user should be informed using a toast and
     * the app will request the data from API using the default zip code
     */
    private fun showMessage(message: String) {
        showToast(message)
        presenter.fetchData(getString(R.string.default_zipcode), { updateUI(it, getString(R.string.settings_units_key)) }, this::showMessage)
    }

    /**
     * When this activity is destroyed we need to detroy and deallocate the presenter
     */
    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}