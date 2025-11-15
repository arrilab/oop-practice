package org.example.s2_webserver.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private final RequestLine requestLine;
    private final Map<String, String> headers = new HashMap<>();
    private final String body;

    public HttpRequest(BufferedReader in) throws IOException {
        // 1. Request Line 읽기
        this.requestLine = new RequestLine(in.readLine());

        // 2. Header 읽기
        String line;
        while (!(line = in.readLine()).isEmpty()) {
            String[] headerTokens = line.split(": ");
            headers.put(headerTokens[0], headerTokens[1]);
        }

        // 3. Body 읽기 – Content-Length 기준
        if ("POST".equals(requestLine.getMethod())) {
            int contentLength = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
            char[] buffer = new char[contentLength];
            in.read(buffer, 0, contentLength);
            this.body = new String(buffer);
        } else {
            this.body = null;
        }
    }

    // RequestLine
    public RequestLine getRequestLine() {
        return this.requestLine;
    }

    public String getPath() {
        return this.requestLine.getPath();
    }

    public QueryParams getQueryParams() {
        return requestLine.getQueryParams();
    }

    public boolean isGetRequest() {
        return requestLine.isGetRequest();
    }

    // Headers
    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    // Body
    public String getBody() {
        return body;
    }

}
