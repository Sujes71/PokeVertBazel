package es.zed.domain.model.input;

import io.vertx.ext.web.RoutingContext;

public interface ApiInputPort {

  void getPokemonByNid(RoutingContext context);
}
