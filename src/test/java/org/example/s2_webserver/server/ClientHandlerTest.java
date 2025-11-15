package org.example.s2_webserver.server;

import org.example.s2_webserver.web.Controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientHandlerTest {

    @Test
    @DisplayName("GET 요청이고 매핑된 path가 있으면 해당 컨트롤러의 handle이 호출된다")
    void run_delegatesToController_onGetAndMappedPath() throws IOException {
        // given
        String rawRequest =
                "GET /calculate HTTP/1.1\r\n" +
                        "Host: localhost\r\n" +
                        "\r\n";

        var in = new ByteArrayInputStream(rawRequest.getBytes(StandardCharsets.UTF_8));
        var out = new ByteArrayOutputStream();

        Socket fakeSocket = new FakeSocket(in, out);

        Controller controller = mock(Controller.class);
        Map<String, Controller> controllerMap = Map.of(
                "/calculate", controller
        );

        var handler = new ClientHandler(fakeSocket, controllerMap);

        // when
        handler.run();

        // then
        verify(controller, times(1)).handle(any(), any());
    }

    @Test
    @DisplayName("GET 이 아닌 메서드는 컨트롤러가 호출되지 않는다")
    void run_doesNotCallController_onNonGetMethod() throws IOException {
        // given
        String rawRequest =
                "POST /calculate HTTP/1.1\r\n" +
                        "Host: localhost\r\n" +
                        "\r\n";

        var in = new ByteArrayInputStream(rawRequest.getBytes(StandardCharsets.UTF_8));
        var out = new ByteArrayOutputStream();

        Socket fakeSocket = new FakeSocket(in, out);

        Controller controller = mock(Controller.class);
        Map<String, Controller> controllerMap = Map.of(
                "/calculate", controller
        );

        var handler = new ClientHandler(fakeSocket, controllerMap);

        // when
        handler.run();

        // then
        verify(controller, never()).handle(any(), any());
    }

    @Test
    @DisplayName("매핑된 컨트롤러가 없으면 handle이 호출되지 않는다")
    void run_doesNotCallController_whenNoMatchingController() throws IOException {
        // given
        String rawRequest =
                "GET /unknown HTTP/1.1\r\n" +
                        "Host: localhost\r\n" +
                        "\r\n";

        var in = new ByteArrayInputStream(rawRequest.getBytes(StandardCharsets.UTF_8));
        var out = new ByteArrayOutputStream();

        Socket fakeSocket = new FakeSocket(in, out);

        Controller controller = mock(Controller.class);
        Map<String, Controller> controllerMap = Map.of(
                "/calculate", controller
        );

        var handler = new ClientHandler(fakeSocket, controllerMap);

        // when
        handler.run();

        // then
        verify(controller, never()).handle(any(), any());
    }

    /**
     * 실제 네트워크 대신 메모리 스트림을 사용하는 테스트용 소켓
     */
    static class FakeSocket extends Socket {
        private final InputStream in;
        private final OutputStream out;

        FakeSocket(InputStream in, OutputStream out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public InputStream getInputStream() {
            return in;
        }

        @Override
        public OutputStream getOutputStream() {
            return out;
        }

        @Override
        public synchronized void close() throws IOException {
            in.close();
            out.close();
        }
    }
}
