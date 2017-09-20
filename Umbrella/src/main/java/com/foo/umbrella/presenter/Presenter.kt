package com.foo.umbrella.presenter

/**
 * Created by vincentvillalta on 9/19/17.
 */
import com.foo.umbrella.R
import com.foo.umbrella.data.ApiServicesProvider
import com.foo.umbrella.data.model.WeatherData
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class Presenter(private val apiServicesProvider: ApiServicesProvider) {

    companion object {
        private const val MAX_COOL_TEMPERATURE_FARENHEIT = 60
    }

    /**
     * Subscriptor for the RxJava
     */
    private var subscriptor: Subscription? = null

    private val weatherService
        get() = apiServicesProvider.weatherService

    /**
    * Fetch new data from the API
    * have two possible call backs
    * @param zipCode the zip code the user have selected, has to be on string
    * @return onSuccess If JSON succeed the observer will emit a signal with the parsed data to be handled by the view
    * @return onError If there is an error, we will emit that to the view
    */
    fun fetchData(zipCode: String, onSuccess: (WeatherData) -> Unit, onError: (String) -> Unit) {
        subscriptor = weatherService.forecastForZipObservable(zipCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ result ->
            if (result.isError) {
                onError(result.error().localizedMessage)
            } else {
                onSuccess(result.response().body())
            }
        }, {
            onError(it.localizedMessage)
        })
    }

    /**
     * Compare the min and max temp from the API
     */
    fun getTemperatureColor(temperatureFarenheit: String) = when {
        temperatureFarenheit.toFloat() < MAX_COOL_TEMPERATURE_FARENHEIT -> R.color.weather_cool
        else -> R.color.weather_warm
    }

    fun destroy() = subscriptor?.unsubscribe()
}