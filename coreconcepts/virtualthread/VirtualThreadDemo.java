package coreconcepts.virtualthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Virtual Thread demonstration for Java 25
 * Virtual threads are lightweight threads that make it easier to write, maintain, and observe
 * high-throughput concurrent applications.
 */
public class VirtualThreadDemo {

    /**
     * Example 1: Creating and starting a virtual thread
     */
    public static void example1_BasicVirtualThread() {
        System.out.println("\n=== Example 1: Basic Virtual Thread ===");
        
        // Create and start a virtual thread
        Thread virtualThread = Thread.ofVirtual()
                .name("virtual-worker")
                .start(new Task("Task 1"));
        
        try {
            virtualThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Example 2: Creating multiple virtual threads
     */
    public static void example2_MultipleVirtualThreads() {
        System.out.println("\n=== Example 2: Multiple Virtual Threads ===");
        
        List<Thread> threads = new ArrayList<>();
        
        // Create 10 virtual threads
        for (int i = 0; i < 10; i++) {
            Thread virtualThread = Thread.ofVirtual()
                    .name("virtual-worker-" + i)
                    .start(new Task("Task " + i));
            threads.add(virtualThread);
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Example 3: Using ExecutorService with virtual threads
     * @throws Exception 
     */
    public static void example3_VirtualThreadExecutor() throws InterruptedException , ExecutionException{
        System.out.println("\n=== Example 3: Virtual Thread Executor ===");
        
        // Create a virtual thread executor
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            // Submit 10 tasks
            List<Future<?>> futures = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                int taskId = i;
                Future<?> future = executor.submit(() -> {
                    System.out.println("Task " + taskId + " running on " + 
                            Thread.currentThread().getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                futures.add(future);
            }
            
            // Wait for all tasks to complete
            for (Future<?> future : futures) {
                future.get();
            }
        }
    }

    /**
     * Example 4: Virtual thread with return value
     */
    public static void example4_VirtualThreadWithResult() throws InterruptedException, ExecutionException {
        System.out.println("\n=== Example 4: Virtual Thread with Result ===");
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            // Submit a callable task
            Future<Integer> future = executor.submit(() -> {
                System.out.println("Computing result on " + Thread.currentThread().getName());
                Thread.sleep(1000);
                return 42;
            });
            
            // Get the result
            Integer result = future.get();
            System.out.println("Result: " + result);
        }
    }

    /**
     * Example 5: Structured concurrency with virtual threads
     */
    public static void example5_StructuredConcurrency() throws InterruptedException {
        System.out.println("\n=== Example 5: Structured Concurrency ===");
        
        // Simulate structured concurrency pattern
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            List<Future<?>> futures = new ArrayList<>();
            
            // Submit related tasks
            for (int i = 0; i < 5; i++) {
                int taskId = i;
                Future<?> future = executor.submit(() -> {
                    System.out.println("Subtask " + taskId + " started");
                    try {
                        Thread.sleep((taskId + 1) * 100);
                        System.out.println("Subtask " + taskId + " completed");
                    } catch (InterruptedException e) {
                        System.out.println("Subtask " + taskId + " interrupted");
                        Thread.currentThread().interrupt();
                    }
                });
                futures.add(future);
            }
            
            // Wait for all subtasks
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (ExecutionException e) {
                    System.err.println("Task failed: " + e.getCause().getMessage());
                }
            }
            
            System.out.println("All subtasks completed");
        }
    }

    /**
     * Example 6: Virtual thread information
     */
    public static void example6_VirtualThreadInfo() {
        System.out.println("\n=== Example 6: Virtual Thread Information ===");
        
        Thread virtualThread = Thread.ofVirtual()
                .name("info-thread")
                .start(() -> {
                    Thread current = Thread.currentThread();
                    System.out.println("Thread Name: " + current.getName());
                    System.out.println("Is Virtual: " + current.isVirtual());
                    System.out.println("Priority: " + current.getPriority());
                    System.out.println("Thread ID: " + current.threadId());
                    System.out.println("State: " + current.getState());
                });
        
        try {
            virtualThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Example 7: Massive concurrency with virtual threads
     */
    public static void example7_MassiveConcurrency() throws InterruptedException {
        System.out.println("\n=== Example 7: Massive Concurrency ===");
        
        long startTime = System.currentTimeMillis();
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            int taskCount = 1000;
            List<Future<?>> futures = new ArrayList<>();
            
            // Submit 1000 tasks
            for (int i = 0; i < taskCount; i++) {
                int taskId = i;
                Future<?> future = executor.submit(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                futures.add(future);
            }
            
            // Wait for all tasks
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (ExecutionException e) {
                    System.err.println("Task failed: " + e.getMessage());
                }
            }
            
            long endTime = System.currentTimeMillis();
            System.out.println("Completed " + taskCount + " tasks in " + 
                    (endTime - startTime) + "ms");
        }
    }

    /**
     * Helper task class
     */
    static class Task implements Runnable {
        private final String name;

        Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " running on " + Thread.currentThread().getName() +
                    " (virtual: " + Thread.currentThread().isVirtual() + ")");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(name + " completed");
        }
    }

    public static void main(String[] args) {
        System.out.println("Java Virtual Thread Examples");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        try {
            example1_BasicVirtualThread();
            example2_MultipleVirtualThreads();
            example3_VirtualThreadExecutor();
            example4_VirtualThreadWithResult();
            example5_StructuredConcurrency();
            example6_VirtualThreadInfo();
            example7_MassiveConcurrency();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== All examples completed ===");
    }
}
