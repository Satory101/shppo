package org.example.logger;

import java.util.ArrayList;
import java.util.List;

public class DocumentHistoryLogger {

    private final List<String> history =
            new ArrayList<>();

    public void addRecord(String text) {

        if (text != null && !text.isBlank()) {

            history.add(text);
        }
    }

    public List<String> getHistory() {

        return new ArrayList<>(history);
    }
}