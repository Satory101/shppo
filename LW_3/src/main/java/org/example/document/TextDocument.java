package org.example.document;

import org.example.logger.DocumentHistoryLogger;

import java.util.concurrent.locks.ReentrantLock;

public class TextDocument extends Document {

    private String content = "";

    private final ReentrantLock lock =
            new ReentrantLock();

    public TextDocument(
            DocumentHistoryLogger historyLogger
    ) {
        super(historyLogger);
    }

    @Override
    public String getContent() {

        lock.lock();

        try {
            return content;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setContent(String content) {

        lock.lock();

        try {

            this.content = content;

            notifyObserver();

        } finally {

            lock.unlock();
        }
    }
}