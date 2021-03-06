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
		List<String> files = this.getFiles();
		for (String file : files) {
			printer.println(file);
		}
		so.close();
	}
	
	public List<String> getFiles() throws IOException {
		List<String> res = new ArrayList<String>();
		Path dir = Paths.get(tc.getPath());
		SimpleDateFormat date = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH); // To avoid special characters
		Stream<Path> files = Files.list(dir);
		files.forEach (p -> {
			StringBuilder s = new StringBuilder();
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
			s.append(" 1 ftp ftp ");
			s.append(f.length() + " ");
			s.append(date.format(f.lastModified()) + " ");
			s.append(f.getName());	
			res.add(s.toString());
			//System.out.println(s.toString());
		});
		return res;		
	}

	public void uploadASCII(String name) throws IOException {
		String path = tc.getPath() + "/" + name;
		InputStream in = so.getInputStream();
		BufferedReader readerData = new BufferedReader(new InputStreamReader(in));
		Path p = Paths.get(path);
		String line;
		String content = "";
		while ((line = readerData.readLine()) != null) {
			content += line + "\r\n";

		}
		Files.writeString(p, content);
		so.close();

	}

	public void uploadBinary(String name) throws IOException {
		String path = tc.getPath() + "/" + name;
		InputStream in = so.getInputStream();
		Path p = Paths.get(path);
		byte buf[] = in.readAllBytes();
		Files.write(p, buf);
		so.close();		
	}

	public void downloadASCII(String name) throws IOException {
		String path = tc.getPath() + "/" + name;
		File file = new File(path);

		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		while ((line = in.readLine()) != null) {
			printer.println(file);
		}
		so.close();

	}

	public void downloadBinary(String name) throws IOException {
		String path = tc.getPath() + "/" + name;
		Path p = Paths.get(path);
		OutputStream out = so.getOutputStream();
		byte[] data = Files.readAllBytes(p);
		out.write(data);
		out.flush();
		so.close();
		
	}

}
