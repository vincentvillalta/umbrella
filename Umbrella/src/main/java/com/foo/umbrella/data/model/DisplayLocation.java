package com.foo.umbrella.data.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Represents a "display_location" returned from Weather Underground
 *
 * Does not include all available only data- only potentially useful fields are included
 */
@AutoValue
public abstract class DisplayLocation {
    public static JsonAdapter<DisplayLocation> jsonAdapter(Moshi moshi) {
        return new AutoValue_DisplayLocation.MoshiJsonAdapter(moshi);
    }

    /**
     * @return a String in the form of "City, StateAbbreviation". Ex. "Minneapolis, MN"
     */
    @Json(name = "full")
    public abstract String getFullName(); abstract String getCity();

    @Json(name = "state")
    public abstract String getStateAbbreviation();

    @Json(name ="state_name")
    public abstract String getStateName();

    public abstract String getCountry();
    public abstract String getZip();
}
