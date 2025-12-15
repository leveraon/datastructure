package com.leveraon.csfoundmental.jdk.features;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;

public class ScopedValuesDemo {
	// Declare a ScopedValue
	private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();
	private static final ScopedValue<String> USER_SESSION = ScopedValue.newInstance();

	public static void main(String[] args) throws Exception {
		// 1. Establish initial scope in the main thread
		ScopedValue.where(REQUEST_ID, "main-req-123").where(USER_SESSION, "user-A-session").run(() -> {
			System.out.println(
					"Main thread: Request ID = " + REQUEST_ID.get() + ", User Session = " + USER_SESSION.get());

			// 2. Virtual thread inherits the ScopedValue
			try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
				executor.submit(() -> {
					System.out.println("Virtual Thread 1: Inherited Request ID = " + REQUEST_ID.get());
					System.out.println("Virtual Thread 1: Inherited User Session = " + USER_SESSION.get());
				}).get(); // Wait for task to complete
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 3. New ScopedValue for a specific virtual thread (overrides inherited)
			try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
				executor.submit(() -> ScopedValue.where(REQUEST_ID, "child-req-456").run(() -> {
					System.out.println("Virtual Thread 2: New Request ID = " + REQUEST_ID.get());
					System.out.println("Virtual Thread 2: Inherited User Session = " + USER_SESSION.get());
				})).get(); // Wait for task to complete
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 4. Using with StructuredTaskScope (demonstrates proper propagation)
			try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
				scope.fork(() -> {
					// This virtual thread inherits the REQUEST_ID and USER_SESSION from the
					// enclosing scope
					System.out.println("Structured Task Scope thread: Request ID = " + REQUEST_ID.get());
					System.out.println("Structured Task Scope thread: User Session = " + USER_SESSION.get());
					return null;
				});
				scope.join().throwIfFailed();
			} catch (ExecutionException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		// Outside the 'where' scope, the ScopedValue is not available (or reverts to
		// its default)
		// System.out.println(REQUEST_ID.get()); // Throws NoSuchElementException if not
		// set in current scope
	}
}
