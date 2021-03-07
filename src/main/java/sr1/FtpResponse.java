package sr1;

/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class FtpResponse.
 */
public class FtpResponse {
	
	private String code;
	
	private String msg;

	/**
	 * Instantiates a new ftp response.
	 *
	 * @param code    the code to put in the response
	 * @param msg     msg of the reply
	 */
	public FtpResponse(int code, String msg) {
		super();
		this.code = String.valueOf(code);
		this.msg = msg;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets the msg.
	 *
	 * @param msg the new msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.code + " " + this.msg + "\r\n";
	}
}
