
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.COMM;

import java.io.IOException;
import java.io.OutputStream;
import javax.bluetooth.ServiceRecord;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author Niklas
 */
public class WriteThread extends Thread {

    private String connectionURL = null;
    private String from = null;
    private String msg = null;
    private ServiceRecord sr = null;

    public WriteThread(ServiceRecord sr, String from, String msg) {
        this.sr = sr;
        this.from = from;
        this.msg = msg;
    }

    public void run() {
        OutputStream op;
        String URL = null;
        StreamConnection connection = null;
        try {
            URL = sr.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            System.out.println(URL);
            connection = (StreamConnection) Connector.open(URL, Connector.WRITE, true);
            System.out.println("2");
            op = connection.openOutputStream();
            String message = new String(msg);
            op.write(message.length());
            op.write(message.getBytes());
            System.out.println("- - - Skickar: " + message);
            op.flush();
            op.close();
            connection.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Fel i medd. sändning...");
        }
    }
}
