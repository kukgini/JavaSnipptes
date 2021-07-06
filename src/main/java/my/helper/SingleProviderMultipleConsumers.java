package my.helper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SingleProviderMultipleConsumers {
    private static final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    private static Supplier<Stream<Integer>> supplier = () -> Stream.generate(queue::poll);
    
    public static void main(String[] args) {
        System.err.println("now i will make a provider.");
        ExecutorService provideExecutor = Executors.newSingleThreadExecutor();
        provideExecutor.execute(SingleProviderMultipleConsumers::provide);
        
        System.err.println("now i will make multiple consumers.");
        int numOfConsumerThreads = 4;
        ExecutorService consumerExecutor = Executors.newFixedThreadPool(numOfConsumerThreads);
    	for (int i = 0; i < numOfConsumerThreads; i++) {
    		consumerExecutor.execute(SingleProviderMultipleConsumers::consume);
    	}
    }
    
    private static void provide() {
        for (int i = 0; i < 10000; i++) {
            try {
                queue.offer(i);
                Thread.sleep(5);
            } catch (InterruptedException e) {
                
            }
        }        
    }
    
    private static void consume() {
        supplier.get()
            .filter(x -> x != null)
            .map(x -> String.format("consumer[%s] %d",Thread.currentThread().getId(), x))
            .forEach(System.out::println);
    }
}
