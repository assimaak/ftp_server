package sr1;

/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class FtpCommand.
 */
public class FtpCommand {
	private String msg;
	private String argument;

	/**
	 * Instantiates a new ftp command.
	 *
	 * @param msg the msg
	 * @param argument the argument
	 */
	public FtpCommand(String msg, String argument) {
		super();
		this.msg = msg;
		this.argument = argument;
	}

	/**
	 * Instantiates a new ftp command without any arguments.
	 *
	 * @param msg the msg
	 */
	public FtpCommand(String msg) {
		this(msg, null);
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return msg;
	}

	/**
	 * Gets the argument.
	 *
	 * @return the argument
	 */
	public String getArg() {
		return argument;
	}

	/**
	 * Sets the message.
	 *
	 * @param msg the new message
	 */
	public void setMessage(String msg) {
		this.msg = msg;
	}

	/**
	 * Sets the argument.
	 *
	 * @param argument the new argument
	 */
	public void setArg(String argument) {
		this.argument = argument;
	}
}
