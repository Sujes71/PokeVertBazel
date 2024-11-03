package es.zed.infrastructure.controller;

import es.zed.domain.model.input.ApiInputPort;
import es.zed.shared.Constants;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class ApiController {

  private final ApiInputPort apiInputPort;

  public ApiController(ApiInputPort apiInputPort) {
    this.apiInputPort = apiInputPort;
  }

  public void registerRoutes(Router router) {
    router.get(Constants.API_POKEMON_BY_NID).handler(this::getPokemonByNid);
  }

  private void getPokemonByNid(RoutingContext context) {
    apiInputPort.getPokemonByNid(context);
  }
}
