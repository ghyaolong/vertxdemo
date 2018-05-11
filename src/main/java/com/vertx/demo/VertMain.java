package com.vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VertMain extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(VertMain.class);

    public static void main(String[] args) {
        logger.info("something happened");
        logger.error("234234");
        Vertx vertx = Vertx.vertx(new VertxOptions().setClustered(false));
        vertx.deployVerticle(VertMain.class.getName());
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route();
        router.get("/products").handler(this::print2);
        router.get("/*").handler(this::print1);

        // 传递方法引用，监听端口
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    public void print1(RoutingContext routingContext){
        logger.info("something happened");
        logger.error("234234");
        routingContext.response().putHeader("content-type", "text/html").end("Hello World");
    }
    public void print2(RoutingContext routingContext){
        routingContext.response().putHeader("content-type", "text/html").end("Hi products");
    }

}
