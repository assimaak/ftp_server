package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class List extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient t) throws IOException {
		try {
			t.getController().sendResponse(new FtpResponse(150, "Here comes the directory listing."));
			System.out.println("ba");
			t.getData().listing();
			return new FtpResponse(226, "Directory send ok.");
		} catch (IOException e) {
			return new FtpResponse(530, "Failed to send the directory.");
		}
	}

}
