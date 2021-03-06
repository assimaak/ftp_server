package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class Retr extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		String name = c.getArg();
		t.getController().sendResponse(new FtpResponse(150, "OK to send data."));
		try {
			if (t.getType()==true) { //Ascii
				t.getController().sendResponse(new FtpResponse(150, "Opening ASCII."));
				t.getData().downloadBinary(name);
				return new FtpResponse(226, "Transfer completed.");
			} else if (t.getType()==false) { //Binary
				t.getController().sendResponse(new FtpResponse(150, "Opening Binary."));
				t.getData().downloadASCII(name);
				return new FtpResponse(226, "Transfer completed.");
			}
		} catch (IOException e) {
			return new FtpResponse(552, "Failed to transfer.");
		}
		return null;
	}

}
