package org.example.s2_webserver.http.request;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class QueryParams {
    private final List<QueryParam> queryParams;

    public QueryParams(String params) {
        this.queryParams = Stream.of(params.split("&"))
                .map(param -> {
                    String[] kv = param.split("=");
                    if (kv.length != 2) {
                        throw new IllegalArgumentException("Invalid query parameter: " + param);
                    }
                    return new QueryParam(kv[0], kv[1]);
                }).toList(); // 불변 리스트
    }

    public List<QueryParam> getQueryParams() {
        return queryParams;
    }

    public String getValue(String key) {
        return this.queryParams.stream()
                .filter(param -> param.exists(key))
                .map(QueryParam::getValue)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        QueryParams that = (QueryParams) o;
        return Objects.equals(queryParams, that.queryParams);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(queryParams);
    }
}
