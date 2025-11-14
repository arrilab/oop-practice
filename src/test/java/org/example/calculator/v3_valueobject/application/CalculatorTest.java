// src/test/java/org/example/calculator/application/CalculatorTest.java
package org.example.calculator.v3_valueobject.application;

import org.example.calculator.v3_valueobject.domain.vo.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalculatorTest {

    @DisplayName("사칙연산을 정상 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int op1, String or, int op2, int result) {
        var positiveOp1 = new PositiveNumber(op1);
        var positiveOp2 = new PositiveNumber(op2);

        int calResult = Calculator.calculate(positiveOp1, or, positiveOp2);

        assertThat(calResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(4, "*", 2, 8),
                arguments(4, "/", 2, 2)
        );
    }

    @DisplayName("잘못된 연산자를 전달하면 예외가 발생한다.")
    @Test
    void invalidOperatorTest() {
        assertThatCode(() ->
                Calculator.calculate(new PositiveNumber(1), "%", new PositiveNumber(2))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 사칙연산이 아님");
    }

    @DisplayName("연산자가 null이면 예외가 발생한다.")
    @Test
    void nullOperatorTest() {
        assertThatCode(() ->
                Calculator.calculate(new PositiveNumber(1), null, new PositiveNumber(2))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 사칙연산이 아님");
    }
}
