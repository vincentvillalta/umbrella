package com.foo.umbrella.data;

import com.squareup.moshi.JsonAdapter;

@com.ryanharter.auto.value.moshi.MoshiAdapterFactory
abstract class MoshiAdapterFactory implements JsonAdapter.Factory {
  public static JsonAdapter.Factory create() {
    return new AutoValueMoshi_MoshiAdapterFactory();
  }
}
