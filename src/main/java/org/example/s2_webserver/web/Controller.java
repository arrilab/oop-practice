package org.example.s2_webserver.web;

import org.example.s2_webserver.http.request.HttpRequest;
import org.example.s2_webserver.http.response.HttpResponse;

public interface Controller {
    void handle(HttpRequest request, HttpResponse response);
}
