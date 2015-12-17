package com.oss.common.exception;

/**
 * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/21.
 */
public class DbException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DbException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DbException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	public DbException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public DbException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	
}
