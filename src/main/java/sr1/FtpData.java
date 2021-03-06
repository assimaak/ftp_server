package sr1;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class FtpData {
	private Socket so;
	private ThreadClient tc;
	private OutputStream out;
	private PrintWriter printer;

	public FtpData(Socket s, ThreadClient t) throws IOException {
		so = s;
		tc = t;
		out = so.getOutputStream();
		printer = new PrintWriter(out, true);

	}

	public void listing() throws IOException {
		System.out.println(tc.getPath());
		List<String> files = this.getFiles();
		for (String file : files) {
			printer.print(file);
		}
		so.close();
	}
	
	public List<String> getFiles() throws IOException {
		List<String> res = new ArrayList<String>();
		Path dir = Paths.get(""+tc.getPath());
		SimpleDateFormat date = new SimpleDateFormat("dd MM yyyy");
		Stream<Path> files = Files.list(dir);
		StringBuilder s = new StringBuilder();
		files.forEach (p -> {
			File f = new File(p.toAbsolutePath().toString());
			if (f.isDirectory()) {
				s.append("d");
			} else {
				s.append("-");
			}
			String rights = "";
			rights += f.canRead() ? "r" : "-";
			rights += f.canWrite() ? "w" : "-";
			rights += f.canExecute() ? "x" : "-";
			for (int i = 0 ; i<3 ; i++) { // Same rights for everyone
				s.append(rights);
			}
			s.append("1 ftp ftp");
			s.append(f.length() + " ");
			s.append(date.format(f.lastModified()) + " ");
			s.append(f.getName() + "\r\n");	
			res.add(s.toString());
		});
		return res;		
	}

}
