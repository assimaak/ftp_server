package sr1;

import java.util.HashMap;
import java.util.Map;

import sr1.manage.*;

public class ManageFactory {
	private Map<String,FtpManage> manages;
	
	public ManageFactory() {
		manages = new HashMap<String,FtpManage>();
		manages.put("AUTH", new Auth());
		manages.put("LIST", new List());
		manages.put("PASV", new Pasv());
		manages.put("PASV", new Pasv());
		manages.put("USER", new User());
		manages.put("PASS", new Pass());
		manages.put("SYST", new Syst());
		manages.put("FEAT", new Feat());
		manages.put("PWD", new Pwd());
		manages.put("TYPE", new Type());
		manages.put("CWD", new Cwd());
		manages.put("CDUP", new Cdup());
		manages.put("QUIT", new Quit());
		manages.put("RETR", new Retr());
		manages.put("MKD", new Mkd());
		manages.put("RNFR", new Rnfr());
		manages.put("RNTO", new Rnto());
		manages.put("DELE", new Dele());
		manages.put("RMD", new Rmd());
		manages.put("STOR", new Stor());
	}
	
	public Map<String,FtpManage> managesMap () {
		return manages;
	}
}
