package com.ride_sharing.ridesharing.learning.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
//    public static void main(String[] args) {
//        Runnable task = () -> {
//            System.out.println("Hello from thread: " + Thread.currentThread().getName());
//        };
//
//        // Tạo 5 thread
//        for (int i = 0; i < 5; i++) {
//            Thread thread = new Thread(task);
//            thread.start();
//        }
//    }

    public static void main(String[] args) {
        // 1. Tạo thread pool có 3 thread cố định
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 2. Submit 10 task
        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Giả lập xử lý mất 1 giây
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 3. Shutdown thread pool
        executor.shutdown();
    }
}
