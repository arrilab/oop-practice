package org.example.s2_webserver.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueryParamTest {

    @Test
    @DisplayName("QueryParam 생성자가 key와 value를 올바르게 설정한다")
    void constructorSetsFields() {
        var queryParam = new QueryParam("operand1", "10");

        assertThat(queryParam.getKey()).isEqualTo("operand1");
        assertThat(queryParam.getValue()).isEqualTo("10");
    }
}
