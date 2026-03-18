package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 힌트: 입력 문자열 파싱을 담당하는 클래스.
 * 구분자 추출, 숫자 토큰 분리, 연산자 추출 등을 여기서 처리할 수 있다.
 * 반드시 이 클래스를 사용할 필요는 없다. 자유롭게 설계할 것.
 */
public class InputParser {

    public boolean isNullOrEmpty(String input) {
        if (input == null || input.isBlank())
            return true;
        return false;
    }

    // 연산자 추출 메서드
    public OperationType operatorChecker(String input) {
        // 지정 연산자가 있는 경우
        if (input.startsWith("op=")) {
            int sign = input.indexOf("=") + 1;

            if (input.charAt(sign) == '+')
                return OperationType.ADDITION;

            if (input.charAt(sign) == '-')
                return OperationType.SUBTRACTION;

            if (input.charAt(sign) == '*')
                return OperationType.MULTIPLICATION;

            if (input.charAt(sign) == '/')
                return OperationType.DIVISION;

            throw new IllegalArgumentException("지원하지 않는 연산자입니다.");
        }
        // 지정 연산자가 없는 경우
        return OperationType.ADDITION;
    }

    // 구분자 추출 메서드
    public List<Character> separatorChecker(String input) {
        List<Character> separators = new ArrayList<>();
        separators.add(',');
        separators.add(':');

        // 커스텀 구분자가 있는 경우
        if (input.startsWith("//"))
            separators.add(input.charAt(2));

        // 커스텀 구분자가 없는 경우
        return separators;
    }

    // 숫자 토큰 분리
    public List<String> extractNumbers(String input) {
        // 구분자 추출 메서드 호출
        List<Character> separators = separatorChecker(input);

        // 지정 연산자가 있는 경우
        if (input.startsWith("op="))
            input = input.substring(input.indexOf('|') + 1);

        // 커스텀 구분자가 있는 경우
        if (input.startsWith("//"))
            input = input.substring(input.indexOf('\n') + 1);

        // 숫자 분리를 위한 정규표현식 생성
        String pattern = separators.stream()
                .map(String::valueOf)   // Character 변수를 String으로 변환
                .map(Pattern::quote)    // 정규표현식에서 특수문자인 구분자를 순수한 문자열로 취급
                .collect(Collectors.joining("|"));  // 요소 사이에만 구분자 추가

        // 숫자 토큰 분리 및 빈 문자열 제거
        return Arrays.stream(input.split(pattern))
                .filter(token -> !token.isEmpty())  // 구분자가 연속될 경우 발생하는 빈 문자열 제거
                .collect(Collectors.toList());
    }
}
