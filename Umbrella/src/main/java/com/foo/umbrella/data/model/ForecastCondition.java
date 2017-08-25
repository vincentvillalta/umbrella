package com.foo.umbrella.data.model;

import com.google.auto.value.AutoValue;
import org.threeten.bp.LocalDateTime;

/**
 * Represents a forecast weather condition returned from Weather Underground
 *
 * Does not include all available only data- only potentially useful fields are included
 */
@AutoValue
public abstract class ForecastCondition {

  public static Builder builder() {
    return new AutoValue_ForecastCondition.Builder();
  }

  /**
   * Formatted time suitable for display
   */
  public abstract String getDisplayTime();

  /**
   * Date representation of the time associated with this forecast
   */
  public abstract LocalDateTime getDateTime();

  /**
   * The icon to use for this reading
   */
  public abstract String getIcon();

  /**
   * The human-readable name of the condition
   */
  public abstract String getCondition();

  /**
   * The temperature that is forecast (in degrees Fahrenheit)
   */
  public abstract String getTempFahrenheit();

  /**
   * The temperature that is forecast (in degrees Celsius)
   */
  public abstract String getTempCelsius();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder setDisplayTime(String displayTime);
    public abstract Builder setDateTime(LocalDateTime dateTime);
    public abstract Builder setIcon(String icon);
    public abstract Builder setCondition(String condition);
    public abstract Builder setTempFahrenheit(String tempFahrenheit);
    public abstract Builder setTempCelsius(String tempCelsius);
    public abstract ForecastCondition build();
  }
}
