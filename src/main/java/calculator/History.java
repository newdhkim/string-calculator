package calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class History {

    private final ArrayDeque<String> history;

    public History() {
        this.history = new ArrayDeque<>();
    }

    public void push(String result) {
        if (history.size() >= 10) {
            history.removeLast();
        }
        history.addFirst(result);
    }

    public List<String> toList() {
        return new ArrayList<>(history);
    }
}