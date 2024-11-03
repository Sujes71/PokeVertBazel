package es.zed.application;

import io.vertx.core.json.JsonObject;

public class RespModel<T> {

  private final T data;
  private final String message;

  public RespModel (T data, String message) {
    this.data = data;
    this.message = message;
  }
  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    json.put("data", data);
    json.put("message", message);
    return json;
  }

  public T getData() {
    return this.data;
  }

  public String getMessage(){
    return this.message;
  }
}