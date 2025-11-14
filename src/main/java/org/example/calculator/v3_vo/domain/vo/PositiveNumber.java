package org.example.calculator.v3_vo.domain.vo;

public class PositiveNumber {
    private final int value;

    public PositiveNumber(int value) {
        validate(value);
        this.value = value;
    }

    public void validate(int value) {
        if (isNegativeNumber(value)) {
            throw new IllegalArgumentException("0 또는 음수를 전달할 수 없습니다");
        }
    }

    public boolean isNegativeNumber(int value) {
        return value <= 0;
    }

    public int toInt() {
        return value;
    }
}
