package sr1.manage;

public abstract class FtpManage{

	public FtpManage() {
		super();
	}

	/**
	 * Handles the command and returns the correct response. Override this method
	 * in each FtpManage to handle each command received.
	 * 
	 * @param command a command to handle
	 * @return FtpReply the good response
	 * @throws IOException in case of an error
	 */
	public abstract FtpResponse handle(FtpCommand command) throws IOException;
}