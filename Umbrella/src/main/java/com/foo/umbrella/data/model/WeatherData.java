package com.foo.umbrella.data.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

/**
 * Represents weather information returned from the Weather Underground API
 *
 * Does not include all available only data- only potentially useful fields are included
 */
@AutoValue
public abstract class WeatherData {
  public static JsonAdapter<WeatherData> jsonAdapter(Moshi moshi) {
    return new AutoValue_WeatherData.MoshiJsonAdapter(moshi);
  }

  @Json(name = "current_observation")
  public abstract CurrentObservation getCurrentObservation();

  @Json(name = "hourly_forecast")
  public abstract List<ForecastCondition> getForecast();
}
