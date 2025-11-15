package org.example.s2_webserver;

import org.example.s2_webserver.server.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        log.info("starting server app");
        var server = new HttpServer(PORT);
        server.start();
    }
}
