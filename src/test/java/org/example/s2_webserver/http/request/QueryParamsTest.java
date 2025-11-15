package org.example.s2_webserver.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class QueryParamsTest {

    @Test
    @DisplayName("쿼리 파라미터를 정상적으로 파싱한다")
    void parseQueryParams() {
        // given
        String params = "operand1=10&operator=*&operand2=5";

        // when
        var queryParams = new QueryParams(params);
        List<QueryParam> result = queryParams.getQueryParams();

        // then
        assertThat(result).hasSize(3);

        assertThat(result).extracting("key")
                .containsExactly("operand1", "operator", "operand2");

        assertThat(result).extracting("value")
                .containsExactly("10", "*", "5");
    }

    @Test
    @DisplayName("잘못된 쿼리 파라미터 형식은 예외를 발생시킨다")
    void invalidQueryParamThrows() {
        // given
        String params = "operand1=10&invalidParam&operand2=5";

        // when & then
        assertThatThrownBy(() -> new QueryParams(params))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalidParam");
    }

    @Test
    @DisplayName("리턴된 queryParams 리스트는 불변이다")
    void queryParamsIsImmutable() {
        // given
        var queryParams = new QueryParams("a=1&b=2");

        List<QueryParam> list = queryParams.getQueryParams();

        // then
        assertThatThrownBy(() -> list.add(new QueryParam("c", "3")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
