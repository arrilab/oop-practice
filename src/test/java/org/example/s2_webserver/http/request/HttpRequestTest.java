package org.example.s2_webserver.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HttpRequestTest {

    @Test
    @DisplayName("GET 요청을 읽어 RequestLine과 헤더를 정확히 파싱한다")
    void parseGetRequest() throws IOException {
        // given
        String raw =
                "GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1\r\n" +
                        "Host: localhost:8080\r\n" +
                        "Connection: keep-alive\r\n" +
                        "\r\n";   // Header 끝

        BufferedReader reader = new BufferedReader(new StringReader(raw));

        // when
        HttpRequest request = new HttpRequest(reader);

        // then — RequestLine
        RequestLine rl = request.getRequestLine();
        assertThat(rl.getMethod()).isEqualTo("GET");
        assertThat(rl.getPath()).isEqualTo("/calculate");
        assertThat(rl.getProtocol()).isEqualTo("HTTP/1.1");
        assertThat(rl.getQueryParams())
                .isEqualTo(new QueryParams("operand1=11&operator=*&operand2=55"));

        // then — Headers
        Map<String, String> headers = request.getHeaders();
        assertThat(headers).containsEntry("Host", "localhost:8080");
        assertThat(headers).containsEntry("Connection", "keep-alive");

        // then – GET은 body 없음
        assertThat(request.getBody()).isNull();
    }

    @Test
    @DisplayName("POST 요청에서 RequestLine, Header, Body 를 모두 정확히 파싱한다")
    void parsePostRequest() throws IOException {
        // given
        String body = "a=1&b=2"; // 7 bytes
        String raw =
                "POST /submit HTTP/1.1\r\n" +
                        "Host: localhost:8080\r\n" +
                        "Content-Type: application/x-www-form-urlencoded\r\n" +
                        "Content-Length: 7\r\n" +
                        "\r\n" +
                        body;

        BufferedReader reader = new BufferedReader(new StringReader(raw));

        // when
        HttpRequest request = new HttpRequest(reader);

        // then — RequestLine
        RequestLine rl = request.getRequestLine();
        assertThat(rl.getMethod()).isEqualTo("POST");
        assertThat(rl.getPath()).isEqualTo("/submit");
        assertThat(rl.getProtocol()).isEqualTo("HTTP/1.1");
        assertThat(rl.getQueryParams()).isNull();

        // then — Headers
        assertThat(request.getHeader("Host")).isEqualTo("localhost:8080");
        assertThat(request.getHeader("Content-Type")).isEqualTo("application/x-www-form-urlencoded");
        assertThat(request.getHeader("Content-Length")).isEqualTo("7");

        // then — Body
        assertThat(request.getBody()).isEqualTo(body);
    }

    @Test
    @DisplayName("POST 요청이지만 Content-Length 가 없으면 Body는 빈 문자열로 처리된다")
    void parsePostRequestWithoutContentLength() throws IOException {
        // given
        String raw =
                "POST /submit HTTP/1.1\r\n" +
                        "Host: localhost:8080\r\n" +
                        "\r\n";

        BufferedReader reader = new BufferedReader(new StringReader(raw));

        // when
        HttpRequest request = new HttpRequest(reader);

        // then
        assertThat(request.getRequestLine().getMethod()).isEqualTo("POST");
        assertThat(request.getBody()).isEqualTo("");  // 기본 값 0 → 빈 문자열
    }

    @Test
    @DisplayName("헤더가 하나도 없는 경우에도 정상 파싱된다")
    void parseRequestWithoutHeaders() throws IOException {
        // given
        String raw =
                "GET /hello HTTP/1.1\r\n" +
                        "\r\n";

        BufferedReader reader = new BufferedReader(new StringReader(raw));

        // when
        HttpRequest request = new HttpRequest(reader);

        // then
        assertThat(request.getHeaders()).isEmpty();
        assertThat(request.getBody()).isNull();
    }
}
