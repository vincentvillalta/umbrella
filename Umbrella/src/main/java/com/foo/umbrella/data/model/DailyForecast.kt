package com.foo.umbrella.data.model

/**
 * Created by vincentvillalta on 9/19/17.
 */

data class DailyForecast(
        var title: String,
        var forecast: List<ForecastCondition>
)