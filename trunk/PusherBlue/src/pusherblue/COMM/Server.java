/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.COMM;

/**
 *
 * @author Niklas
 */
import java.io.IOException;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import pusherblue.CORE.Core;

public class Server extends Thread {

    private StreamConnection con = null;
    private StreamConnectionNotifier service = null;
    private UUID uuid = null;
    private Core logic;
    
    public Server(Core logic) {
            this.logic = logic;
    }

    public void run() {
        try {
            uuid = new UUID(0xB984FE2A); //1101
            StringBuffer url = new StringBuffer("btspp://");
            url.append("localhost").append(':');
            url.append(uuid.toString());
            url.append(";name=ChatServer");
            url.append(";authorize=false");
            int i = 0;

            service = (StreamConnectionNotifier) Connector.open(url.toString());
            while (true) {
                //Server waiting for client to connect
                con = service.acceptAndOpen();
                //Starts a new thread for reading data from inputstream
                ReadThread rdthr = new ReadThread(con, logic);
                rdthr.start();
                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}