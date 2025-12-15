package com.leveraon.csfoundmental.jdk.features;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThreadsDemo {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();

		// Use a Virtual Thread Executor
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			IntStream.range(0, 10_000).forEach(i -> {
				executor.submit(() -> {
					// Simulate a blocking I/O operation or CPU-bound task
					try {
						Thread.sleep(Duration.ofMillis(10)); // Virtual thread yields here
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					if (i % 1000 == 0) {
						System.out.println("Task " + i + " completed by thread: " + Thread.currentThread()
								+ " (Virtual: " + Thread.currentThread().isVirtual() + ")");
					}
				});
			});
		} // The executor's close() method waits for all tasks to complete

		long end = System.currentTimeMillis();
		System.out.println("All 10,000 tasks completed in " + (end - start) + " ms");

		// You can also create a single virtual thread directly:
		Thread virtualThread = Thread.startVirtualThread(() -> {
			System.out.println("Hello from a direct virtual thread! " + Thread.currentThread());
		});
		virtualThread.join(); // Wait for it to finish
	}
}
