package sr1.manage;

import java.io.*;

import sr1.*;


/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class FtpManage.
 */
public abstract class FtpManage{

	/**
	 * Instantiates a new ftp manage.
	 */
	public FtpManage() {
		super();
	}

	/**
	 * Handles the command and returns the correct response. Override this method
	 * in each FtpManage to handle each command received.
	 * 
	 * @param command a command to handle
	 * @param threadClient the thread dealing with the client
	 * @return FtpReply the good response
	 * @throws IOException in case of an error
	 */
	public abstract FtpResponse handle(FtpCommand command, ThreadClient threadClient) throws IOException;
}