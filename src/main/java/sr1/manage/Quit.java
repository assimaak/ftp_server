package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class Quit extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient threadClient) throws IOException {
		try {
			threadClient.getSocket().close();
			return new FtpResponse(221, "Server Closed");
		} catch (IOException e) { 
			return new FtpResponse(500,"Error");
		}
	}

}
