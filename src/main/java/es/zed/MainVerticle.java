package es.zed;

import es.zed.application.service.ApiService;
import es.zed.infrastructure.controller.ApiController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    Router router = Router.router(vertx);

    WebClient webClient = WebClient.create(vertx);

    ApiService apiService = new ApiService(webClient);
    ApiController apiController = new ApiController(apiService);

    apiController.registerRoutes(router);

    vertx.createHttpServer()
        .requestHandler(router)
        .listen(8080, http -> {
          if (http.succeeded()) {
            System.out.println("Server started on port 8080");
          } else {
            http.cause().printStackTrace();
          }
        });
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }
}
