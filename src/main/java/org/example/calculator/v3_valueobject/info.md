# 패키지 구조 설명

## 1. `org.example.calculator.domain`

**핵심 도메인 객체 패키지**

- 이 패키지는 계산기의 핵심 비즈니스 로직을 구성하는 **도메인 모델**을 포함합니다.
- 예를 들어, `PositiveNumber` 같은 **값 객체(Value Object, VO)**가 해당됩니다.
- 외부 영향과 무관하게 **비즈니스 규칙 자체를 표현하는 순수한 객체들**을 배치합니다.

---

## 2. `org.example.calculator.domain.operator`

**연산 전략(Strategy) 패키지**

- 사칙 연산 같은 **도메인 로직을 캡슐화한 전략 객체들**이 위치합니다.
- 예:
    - 인터페이스: `ArithmeticOperator`
    - 구현체:
        - `AdditionOperator`
        - `SubtractionOperator`
        - `MultiplicationOperator`
        - `DivisionOperator`
- 새로운 연산을 추가하려면 이 패키지에 구현체를 추가하면 됩니다.

---

## 3. `org.example.calculator.application`

**애플리케이션 서비스 계층**

- 도메인 객체 및 연산 전략들을 조합해 **실제 기능을 수행하는 진입점 클래스**들이 위치합니다.
- 예:
    - `Calculator` 클래스
        - 연산자 선택
        - PositiveNumber 생성
        - 도메인 로직 실행 등의 주요 동작을 수행함
- UI나 인프라 계층과 분리된, 순수한 **애플리케이션 서비스 레이어**입니다.

