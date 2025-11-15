package org.example.s2_webserver.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

class HttpResponseTest {

    @Test
    @DisplayName("response200Header가 올바른 HTTP 200 헤더를 전송한다")
    void response200Header_writesCorrectHeader() throws IOException {
        // given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        HttpResponse response = new HttpResponse(dos);

        String contentType = "application/json";
        byte[] body = "hello".getBytes(UTF_8);

        // when
        response.response200Header(contentType, body.length);

        // then
        String result = baos.toString(UTF_8);
        String expected =
                "HTTP/1.1 200 OK \r\n" +
                        "Content-Type: application/json;charset=utf-8\r\n" +
                        "Content-Length: 5\r\n" +
                        "\r\n";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("responseBody가 바디를 전송하고 flush한다")
    void responseBody_writesBody() throws IOException {
        // given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        HttpResponse response = new HttpResponse(dos);

        byte[] body = "605".getBytes(UTF_8);

        // when
        response.responseBody(body);

        // then
        // DataOutputStream.write(...) 는 그대로 바이트를 쓴다
        assertThat(baos.toByteArray()).isEqualTo(body);
        assertThat(baos.toString(UTF_8)).isEqualTo("605");
    }
}
