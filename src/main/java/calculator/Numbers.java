package calculator;

import java.util.List;

public class Numbers {

    private final List<Double> numbers;

    public Numbers(List<String> numbers) {
        List<Double> convertedNumbers = numbers.stream()
                .map(num -> {
                    try {
                        double temp = Double.parseDouble(num);
                        if (temp < 0)
                            throw new IllegalArgumentException("음수는 입력할 수 없습니다");
                        return temp;

                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다");
                    }
                })
                .toList();

        this.numbers = convertedNumbers;
    }

    public double calculate(OperationType operation) {

        double result = numbers.get(0);

        if (operation == OperationType.ADDITION)
            return add(result);


        if (operation == OperationType.SUBTRACTION) {
            return subtract(result);
        }

        if (operation == OperationType.MULTIPLICATION) {
            return multiply(result);
        }

        return divide(result);
    }

    private double add(double result) {
        for (int i = 1; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }

    private double subtract(double result) {
        for (int i = 1; i < numbers.size(); i++) {
            result -= numbers.get(i);
        }
        return result;
    }

    private double multiply(double result) {
        for (int i = 1; i < numbers.size(); i++) {
            result *= numbers.get(i);
        }
        return result;
    }

    private double divide(double result) {
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) == 0)
                throw new IllegalArgumentException("0으로 나눌 수 없습니다");
            result /= numbers.get(i);
        }
        return Double.parseDouble(String.format("%.1f", result));
    }
}
