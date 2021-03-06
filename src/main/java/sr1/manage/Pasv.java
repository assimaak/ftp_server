package sr1.manage;

import java.io.IOException;
import java.net.*;
import java.util.*;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class Pasv extends FtpManage {
	private ServerSocket s;
	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient t) throws IOException {
		try {
			Random random = new Random();
			int p1 = random.nextInt(240) + 10;
			int p2 = random.nextInt(256);
			int port = p1 * 256 + p2;
			s = new ServerSocket(port);
			int p = s.getLocalPort();
			t.setPassive(s);
			return new FtpResponse(227, "Entering passive mode (127,0,0,1," + p1 + "," + p2+")");
		} catch (Exception e) {
			return new FtpResponse(530, "Failed to enter in passive mode.");
		}
	}

}
