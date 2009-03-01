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
import java.io.InputStream;
import java.io.OutputStream;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import pusherblue.CORE.Core;

public class Server extends Thread {

    StreamConnection con = null;
    StreamConnectionNotifier service = null;
    InputStream ip = null;
    OutputStream op = null;
    UUID uuid = null;
    Core logic;

    public Server(Core logic) {
        this.logic = logic;
    }

    public void run() {
        try {
            //Extends a stream for client to connect
            uuid = new UUID(0x1101);
            StringBuffer url = new StringBuffer("btspp://");
            url.append("localhost").append(':');
            url.append(uuid.toString());
            url.append(";name=ChatServer");
            url.append(";authorize=false");
            service = (StreamConnectionNotifier) Connector.open(url.toString());
            //Server waiting for client to connect
            System.out.println("Väntar på kontakt...");
            con = service.acceptAndOpen();
            System.out.println("Kontakt skapad...");
            //Open streams for two way communication.
            ip = con.openInputStream();
            //Starts a new thread for reading data from inputstream
            //while the present thread, goes forward and write data to outputstream
            //thus enabling a two way communication with the client
            ReadThread rdthr = new ReadThread(ip, logic);
            rdthr.start();
        //con.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}