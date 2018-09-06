package com.studynetty.bio;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Ïß³Ì³Ø
 * @author Administrator
 *
 */
public class ExecutePool {
	private ExecutorService executor;
	
	public ExecutePool(int maxSize){
		this.executor = Executors.newFixedThreadPool(maxSize);
	}
	
	public void execute(Runnable runnable){
		executor.execute(runnable);
	}
	
	public void close(){
		executor.shutdown();
	}

}
