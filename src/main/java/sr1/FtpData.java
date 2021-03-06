package sr1;

import java.net.Socket;

public class FtpData {
	private Socket s;
	private ThreadClient t;
	public FtpData(Socket s, ThreadClient t) {
		s=s;
		t=t;
	}

}
