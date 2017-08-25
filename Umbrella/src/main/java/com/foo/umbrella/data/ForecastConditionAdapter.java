package com.foo.umbrella.data;

import com.google.auto.value.AutoValue;
import com.foo.umbrella.data.model.ForecastCondition;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import org.threeten.bp.LocalDateTime;

/**
 * Moshi Adapter to convert goofy weather underground data to a easier to use ForecastCondition.
 */
public class ForecastConditionAdapter {
  @FromJson public ForecastCondition forecastConditionFromJson(
      ForecastConditionJson forecastConditionJson) {

    FctTime fcTTime = forecastConditionJson.getFcTTime();
    LocalDateTime dateTime = LocalDateTime.of(fcTTime.getYear(), fcTTime.getMon(),
        fcTTime.getMday(), fcTTime.getHour(), fcTTime.getMin());

    String displayTime = String.format("%s:%s %s", fcTTime.getHour(), fcTTime.getMin(),
        fcTTime.getAmpm());

    Temperature temperature = forecastConditionJson.getTemperature();

    return ForecastCondition.builder()
        .setCondition(forecastConditionJson.getCondition())
        .setDisplayTime(displayTime)
        .setIcon(forecastConditionJson.getIcon())
        .setDateTime(dateTime)
        .setTempCelsius(temperature.getMetric())
        .setTempFahrenheit(temperature.getEnglish())
        .build();
  }

  @ToJson public ForecastConditionJson forecastConditionToJson(ForecastCondition forecastCondition) {
    throw new UnsupportedOperationException("Don't do this");
  }

  /**
   * Maps directly to the json in the api
   */
  @AutoValue abstract static class ForecastConditionJson {
    public static JsonAdapter<ForecastConditionJson> jsonAdapter(Moshi moshi) {
      return new AutoValue_ForecastConditionAdapter_ForecastConditionJson.MoshiJsonAdapter(moshi);
    }

    abstract String getIcon();
    abstract String getCondition();
    @Json(name = "FCTTIME") abstract FctTime getFcTTime();
    @Json(name = "temp") abstract Temperature getTemperature();
  }

  @AutoValue abstract static class FctTime {
    public static JsonAdapter<FctTime> jsonAdapter(Moshi moshi) {
      return new AutoValue_ForecastConditionAdapter_FctTime.MoshiJsonAdapter(moshi);
    }

    abstract int getHour();
    abstract int getMin();
    abstract int getYear();
    abstract int getMon();
    abstract int getMday();
    abstract String getAmpm();
  }

  @AutoValue abstract static class Temperature {
    public static JsonAdapter<Temperature> jsonAdapter(Moshi moshi) {
      return new AutoValue_ForecastConditionAdapter_Temperature.MoshiJsonAdapter(moshi);
    }

    abstract String getEnglish();
    abstract String getMetric();
  }
}
