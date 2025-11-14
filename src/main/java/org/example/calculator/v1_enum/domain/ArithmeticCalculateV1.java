package org.example.calculator.v1_enum.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

public enum ArithmeticCalculateV1 {
    ADDITION("+", (a, b) -> a + b),
    SUBTRACTION("-", (a, b) -> a - b),
    MULTIPLICATION("*", (a, b) -> a * b),
    DIVISION("/", (a, b) -> a / b);

    private final String operator;
    private final IntBinaryOperator expression;

    ArithmeticCalculateV1(String operator, IntBinaryOperator expression) {
        this.operator = operator;
        this.expression = expression;
    }

    public int calculate(int operand1, int operand2) {
        return expression.applyAsInt(operand1, operand2);
    }

    // 미리 맵으로 캐싱해서 매번 stream 돌리지 않기
    private static final Map<String, ArithmeticCalculateV1> BY_OPERATOR =
            Arrays.stream(values())
                    .collect(Collectors.toMap(o -> o.operator, o -> o));

    public static int calculate(int operand1, String operator, int operand2) {
        ArithmeticCalculateV1 arithmeticCalculate = BY_OPERATOR.get(operator);
        if (arithmeticCalculate == null) {
            throw new IllegalArgumentException("올바른 사칙연산이 아님: " + operator);
        }
        return arithmeticCalculate.calculate(operand1, operand2);
    }
}
