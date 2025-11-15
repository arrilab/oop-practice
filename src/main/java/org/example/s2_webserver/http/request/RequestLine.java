package org.example.s2_webserver.http.request;

import java.util.Objects;

public class RequestLine {
    private final String method;
    private final String path;
    private QueryParams queryParams;
    private final String protocol;

    // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
    public RequestLine(String line) {
        String[] tokens = line.split(" ");
        method = tokens[0];
        protocol = tokens[2];

        String[] pathTokens = tokens[1].split("\\?");
        path = pathTokens[0];

        if (pathTokens.length > 1) {
            queryParams = new QueryParams(pathTokens[1]);
        }
    }

    public RequestLine(String method, String path, QueryParams queryParams, String protocol) {
        this.method = method;
        this.path = path;
        this.queryParams = queryParams;
        this.protocol = protocol;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public QueryParams getQueryParams() {
        return this.queryParams;
    }

    public String getProtocol() {
        return protocol;
    }

    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }

    public boolean matchPath(String path) {
        return path.equals(path);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(path, that.path) && Objects.equals(queryParams, that.queryParams) && Objects.equals(protocol, that.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, path, queryParams, protocol);
    }
}
