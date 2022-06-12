package my.helper;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ExecutorMonitor {
    private Queue<Future<Exception>> list = new LinkedList<>();

    public ExecutorMonitor add(Future future) {
        list.add(future);
        return this;
    }
    public CompletableFuture allGone() {
        return CompletableFuture.runAsync(() -> {
            while (list.size() > 0) {
                if (list.peek().isDone()) {
                    list.remove();
                }
            }
        });
    }
}
