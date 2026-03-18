package calculator;

import java.util.List;

/**
 * 힌트: 연산 이력을 관리하는 클래스.
 * 일급 컬렉션으로 구현해볼 것.
 * 반드시 이 클래스를 사용할 필요는 없다. 자유롭게 설계할 것.
 */
public class CalculationHistory {

    private final History history = new History();

    public void save(String input, String result) {
        String str = "입력 : " + input + " => 출력 : " + result;
        history.push(str);
    }

    public List<String> get() {
        return history.toList();
    }
}
