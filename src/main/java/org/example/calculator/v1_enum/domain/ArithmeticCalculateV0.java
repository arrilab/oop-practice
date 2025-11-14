package org.example.calculator.v1_enum.domain;

import java.util.Arrays;

public enum ArithmeticCalculateV0 {
    ADDITION("+") {
        @Override
        public int arithmeticCalculate(int op1, int op2) {
            return op1 + op2;
        }
    },

    SUBTRACTION("-") {
        @Override
        public int arithmeticCalculate(int op1, int op2) {
            return op1 - op2;
        }
    },

    MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(int op1, int op2) {
            return op1 * op2;
        }
    },

    DIVISION("/") {
        @Override
        public int arithmeticCalculate(int op1, int op2) {
            return op1 / op2;
        }
    };

    private final String operator;

    ArithmeticCalculateV0(String operator) {
        this.operator = operator;
    }

    public abstract int arithmeticCalculate(final int operand1, final int operand2);

    public static int calculate(int operand1, String operator, int operand2) {
        ArithmeticCalculateV0 arithmeticCalculate = Arrays.stream(values())
                .filter(v -> v.operator.equals(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아님"));
        return arithmeticCalculate.arithmeticCalculate(operand1, operand2);
    }
}
