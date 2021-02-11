package sr1;

public class FtpCommand {
	private String msg;
	private String argument;

	public FtpCommand(String msg, String argument) {
		super();
		this.msg = msg;
		this.argument = argument;
	}

	public FtpCommand(String msg) {
		this(msg, null);
	}

	public String getMessage() {
		return msg;
	}

	public String getArg() {
		return argument;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}

	public void setArg(String argument) {
		this.argument = argument;
	}
}
