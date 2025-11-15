package org.example.s2_webserver.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestLineTest {

    @Test
    @DisplayName("문자열 한 줄에서 요청라인을 올바르게 파싱한다 - 쿼리스트링 O")
    void parseRequestLine_withQueryParam() {
        // given
        String line = "GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1";

        // when
        var requestLine = new RequestLine(line);

        // then
        assertThat(requestLine.getMethod()).isEqualTo("GET");
        assertThat(requestLine.getPath()).isEqualTo("/calculate");
        assertThat(requestLine.getQueryParams()).isEqualTo(new QueryParams("operand1=11&operator=*&operand2=55"));
        assertThat(requestLine.getProtocol()).isEqualTo("HTTP/1.1");
    }

    @Test
    @DisplayName("문자열 한 줄에서 요청라인을 올바르게 파싱한다 - 쿼리스트링 X")
    void parseRequestLine_withoutQueryParam() {
        // given
        String line = "GET /index.html HTTP/1.1";

        // when
        var requestLine = new RequestLine(line);

        // then
        assertThat(requestLine.getMethod()).isEqualTo("GET");
        assertThat(requestLine.getPath()).isEqualTo("/index.html");
        assertThat(requestLine.getQueryParams()).isNull();
        assertThat(requestLine.getProtocol()).isEqualTo("HTTP/1.1");
    }

    @Test
    @DisplayName("두 번째 생성자로 필드가 올바르게 설정된다")
    void constructorAndGetters() {
        // given
        String method = "POST";
        String path = "/users";
        String query = "id=1";
        String protocol = "HTTP/1.1";

        // when
        var params = new QueryParams(query);
        var requestLine = new RequestLine(method, path, params, protocol);

        // then
        assertThat(requestLine.getMethod()).isEqualTo(method);
        assertThat(requestLine.getPath()).isEqualTo(path);
        assertThat(requestLine.getQueryParams()).isEqualTo(new QueryParams("id=1"));
        assertThat(requestLine.getProtocol()).isEqualTo(protocol);
    }

    @Test
    @DisplayName("동일한 필드는 equals와 hashCode가 동일해야 한다")
    void equalsAndHashCode_sameValues() {
        // given
        var r1 = new RequestLine("GET", "/calculate", new QueryParams("a=1&b=2"), "HTTP/1.1");
        var r2 = new RequestLine("GET", "/calculate", new QueryParams("a=1&b=2"), "HTTP/1.1");

        // then
        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }

    @Test
    @DisplayName("서로 다른 필드는 equals가 false여야 한다")
    void equals_differentValues() {
        // given
        var r1 = new RequestLine("GET", "/calculate", new QueryParams("a=1&b=2"), "HTTP/1.1");
        var r2 = new RequestLine("POST", "/calculate", new QueryParams("a=1&b=2"), "HTTP/1.1");

        // then
        assertThat(r1).isNotEqualTo(r2);
    }
}
