package me.sunshu.commonsdk.exception;

/**********************************************************
 * @文件名称：LogicException.java
 * @文件描述：自定义异常类,返回ecode,emsg到业务层
 *
 **********************************************************/
public class OkHttpException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * the server return code
	 */
	private int ecode;

	/**
	 * the server return error message
	 */
	private Object emsg;

	public OkHttpException(int ecode, Object emsg) {
		this.ecode = ecode;
		this.emsg = emsg;
	}

	public int getEcode() {
		return ecode;
	}

	public Object getEmsg() {
		return emsg;
	}
}