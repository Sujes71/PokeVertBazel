package es.zed.application.service;

import es.zed.application.RespModel;
import es.zed.domain.model.PokemonObject;
import es.zed.domain.model.input.ApiInputPort;
import es.zed.shared.Constants;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class ApiService implements ApiInputPort {
  private final WebClient client;

  public ApiService(WebClient client) {
    this.client = client;
  }

  public void getPokemonByNid(RoutingContext context) {
    String nid = context.pathParam(Constants.API_NID_PARAMETER);

    HttpRequest<Buffer> request = client
        .get(443, Constants.API_EXTERNAL_BASE_PATH, Constants.API_EXTERNAL_POKEMON_PATH + Constants.SIDEBAR + nid)
        .ssl(true);

    request.send(ar -> {
      if (ar.succeeded()) {
        HttpResponse<Buffer> response = ar.result();
        try {
          String responseBody = response.bodyAsString();
          JsonObject jsonResponse = new JsonObject(responseBody);
          PokemonObject pokemon = jsonResponse.mapTo(PokemonObject.class);
          context.json(new RespModel<>(pokemon, "Success"));
        } catch (Exception e) {
          e.printStackTrace();
          context.json(new RespModel<>(null, "JSON mapping error: " + e.getMessage()));
        }
      } else {
        System.out.println("Request error: " + ar.cause().getMessage());
        context.json(new RespModel<>(null, "Request error: " + ar.cause().getMessage()));
      }
    });
  }
}
