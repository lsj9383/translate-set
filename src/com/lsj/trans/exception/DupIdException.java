package com.lsj.trans.exception;

public class DupIdException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5743754674671902221L;
	
	public DupIdException(String info){
		super(info, null);
	}
}
