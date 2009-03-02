/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.COMM;

import java.io.IOException;
import java.io.OutputStream;
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

    public WriteThread(String connectionURL, String from, String msg) {
        this.connectionURL = connectionURL;
        this.from = from;
        this.msg = msg;
    }

    public void run() {
        OutputStream op;
        try {
            StreamConnection connection = (StreamConnection) Connector.open(connectionURL);
            op = connection.openOutputStream();
            String message = new String(msg);
            System.out.println("- - - Skickar: " + message);
            op.write(message.length());
            op.write(message.getBytes());
            op.close();
            connection.close();
            this.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}