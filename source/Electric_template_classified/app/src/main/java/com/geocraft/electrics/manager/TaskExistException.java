package com.geocraft.electrics.manager;

/**
 * Created by Administrator on 2016/6/5.
 */
public class TaskExistException extends Exception{

	public TaskExistException() {
		super("Task info error");
	}

	public TaskExistException(String msg) {
		super(msg);
	}
}
