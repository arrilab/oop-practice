package org.example.s2_webserver.server;

import org.example.s2_webserver.web.CalculateController;
import org.example.s2_webserver.web.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class HttpServer {

    private static final Logger log = LoggerFactory.getLogger(HttpServer.class);

    private final int port;
    private final Map<String, Controller> controllerMap;

    public HttpServer(int port) {
        this.port = port;
        this.controllerMap = Map.of("/calculate", new CalculateController());
    }

    public void start() throws IOException {
        try (var serverSocket = new ServerSocket(port)) {
            log.info("HTTP Server started on port {}", port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                log.info("Client connected: {}", clientSocket);
                new Thread(new ClientHandler(clientSocket, controllerMap)).start();
            }
        }
    }
}
