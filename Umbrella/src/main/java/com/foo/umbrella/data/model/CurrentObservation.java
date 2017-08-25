package com.foo.umbrella.data.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Represents the "current_observation" data returned from Weather Underground
 *
 * Does not include all available only data- only potentially useful fields are included
 */
@AutoValue
public abstract class CurrentObservation {
  public static JsonAdapter<CurrentObservation> jsonAdapter(Moshi moshi) {
    return new AutoValue_CurrentObservation.MoshiJsonAdapter(moshi);
  }

  @Json(name = "display_location")
  public abstract DisplayLocation getDisplayLocation();

  @Json(name = "temp_f")
  public abstract String getTempFahrenheit();

  @Json(name = "temp_c")
  public abstract String getTempCelsius();

  /**
   * @return String with a single word "weather" description such as "Sunny" or "Overcast"
   */
  @Json(name = "weather")
  public abstract String getWeatherDescription();

  @Json(name = "icon")
  public abstract String getIconName();
}
