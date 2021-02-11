package sr1;

public class FtpResponse {
	private String code;
	private String msg;

	/**
	 * 
	 * @param code    the code to put in the response
	 * @param msg     msg of the reply
	 */
	public FtpResponse(int code, String msg) {
		super();
		this.code = String.valueOf(code);
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return this.code + " " + this.msg + "\r\n";
	}
}
