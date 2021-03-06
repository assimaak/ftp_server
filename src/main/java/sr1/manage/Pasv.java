package sr1.manage;

import java.io.IOException;
import java.net.*;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class Pasv extends FtpManage {
	private ServerSocket s;
	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient t) throws IOException {
		try {
			s = new ServerSocket(0);
			int p = s.getLocalPort();
			t.setPassive(s);
			return new FtpResponse(227, "Entering passive mode (127,0,0,1," + p / 256 + "," + (p - (p / 256) * 256)+")");
		} catch (Exception e) {
			return new FtpResponse(530, "Failed to enter in passive mode.");
		}
	}

}
