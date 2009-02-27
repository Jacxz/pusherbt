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

public class Server {

    StreamConnection con = null;
    StreamConnectionNotifier service = null;
    InputStream ip = null;
    OutputStream op = null;
    UUID uuid = null;


    //"btspp://localhost:"1111;name=ChatServer;authorize=false";
    public Server() throws IOException {
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
        op = con.openOutputStream();
        //Starts a new thread for reading data from inputstream
        //while the present thread, goes forward and write data to outputstream
        //thus enabling a two way communication with the client
        ReadThread rdthr = new ReadThread(ip);
        rdthr.start();
        //int i = 0;
        //while (true) {
        //    writeData("Server skickar" + i);
        //    i++;
        //}
    }

    /**
    private void writeData(String msg) throws IOException {

        op.write(msg.length());
        op.write(msg.getBytes());
    }
    */

    class ReadThread extends Thread {

        InputStream ip = null;

        public ReadThread(InputStream inp) {
            ip = inp;
        }

        public void run() {
            boolean isRunning = true;
            String line;
            while (isRunning) {
                if ((line = readData()) == null) {
                    isRunning = false;
                } else { // there was some input
                    if (line.trim().equals("bye$$")) {
                        isRunning = false;
                    } else {
                        //ecm.showMessage(clientName + ": " + line);
                        System.out.println("Tog emot: " + line);
                        // show in the GUI
                        String upper = line.trim().toUpperCase();
                    //if (isRunning) {
                    //    sendMessage(upper);
                    //}
                    }
                }
            }
            System.out.println("Handler finished");
        }

        private String readData() {
            byte[] data = null;
            try {
                int len = ip.read(); // get the message length
                if (len <= 0) {
                    System.out.println("Message Length Error");
                    return null;
                }
                data = new byte[len];
                len = 0;
                // read the message, perhaps requiring several read() calls
                while (len != data.length) {
                    int ch = ip.read(data, len, data.length - len);
                    if (ch == -1) {
                        System.out.println("Message Read Error");
                        return null;
                    }
                    len += ch;
                }
            } catch (IOException e) {
                System.out.println("readData(): " + e);
                return null;
            }
            return new String(data); // convert byte[] to String
        }
    }
}