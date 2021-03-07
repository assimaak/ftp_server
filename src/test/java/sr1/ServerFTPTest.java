package sr1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Some quick test for ServerFTP.
 */
public class ServerFTPTest 
{
    @Test
    public void CommandTest()
    {
    	FtpCommand c = new FtpCommand("CWD", "fichier");
        assertEquals(c.getMessage(), "CWD");
    	assertEquals(c.getArg(), "fichier");    	
    }
    
    @Test
    public void ResponseTest()
    {
    	FtpResponse c = new FtpResponse(550, "Error");
        assertEquals(c.getCode(), String.valueOf(550));
    	assertEquals(c.getMsg(), "Error");    	
    }
}
