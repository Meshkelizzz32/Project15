package project6;

import java.util.*;
import java.util.concurrent.*;


class Fibonacci implements Callable<Integer>{
	private Integer howMany = 0;
	ExecutorService exec = Executors.newSingleThreadExecutor();
	private int fib(int n) {
		if (n < 2)
			return 1;
		return fib(n - 2) + fib(n - 1);
	}
	
	public Integer call() {
		int sum = 0;
		for (int i = 0; i < howMany; i++) {
			sum += fib(i);
		}
		return sum;
	}
	public Future<Integer> runTask(int howMany) {
		this.howMany = howMany;
		return exec.submit(this);
	}
}

public class ExFibonacci {
	public static void main(String[] args) {
		Fibonacci fibObj = new Fibonacci();
		try {
			int i = 17;
			Integer result = fibObj.runTask(i).get();
			System.out.println("Sum of first " + i + " Fibonacci numbers = " + result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			fibObj.exec.shutdown();
		}
	}
}
