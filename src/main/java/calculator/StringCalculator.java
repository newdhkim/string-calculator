package calculator;

import java.util.List;

/**
 * 진입점. 흐름 조율만 담당한다.
 * 파싱, 계산, 이력 관리를 직접 구현하지 말 것.
 * 각 책임은 별도 클래스에 위임할 것.
 */
public class StringCalculator {

    private final InputParser inputParser = new InputParser();
    private final CalculationHistory calculationHistory = new CalculationHistory();

    public double calculate(String input) {

        // 사전 검증. 텅 빈 문자열이나 null 을 입력한 경우
        if (inputParser.isNullOrEmpty(input))
            return 0.0;

        // 1. 사칙연산 구분 및 입력 문자열 파싱
        OperationType type = inputParser.operatorChecker(input);

        // 2. 음수, 숫자가 아닌 문자, 잘못된 연산자 처리
        Numbers extractedNumbers = new Numbers(inputParser.extractNumbers(input));

        // 3. 결과 계산 (소수점 처리 포함)
        double result = extractedNumbers.calculate(type);

        // 4. 결과 저장
        calculationHistory.save(input, String.valueOf(result));

        return result;
    }

    public List<String> getHistory() {
        return calculationHistory.get();
    }
}
