package org.example.calculator.v3_vo.domain;// src/test/java/org/example/calculator/domain/PositiveNumberTest.java
import org.example.calculator.v3_vo.domain.vo.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

class PositiveNumberTest {

    @DisplayName("0 또는 음수를 전달하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    void createExceptionTest(int value) {
        assertThatCode(() -> new PositiveNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0 또는 음수를 전달할 수 없습니다");
    }

    @DisplayName("양수를 전달하면 객체가 정상 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void createSuccessTest(int value) {
        assertThatCode(() -> new PositiveNumber(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("경계값 1도 정상적으로 생성된다.")
    @Test
    void boundaryTest() {
        assertThatCode(() -> new PositiveNumber(1))
                .doesNotThrowAnyException();
    }
}
