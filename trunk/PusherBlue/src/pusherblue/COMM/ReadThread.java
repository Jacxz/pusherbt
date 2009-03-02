/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.COMM;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.StreamConnection;
import pusherblue.CORE.Core;

/**
 *
 * @author Niklas
 */
public class ReadThread extends Thread {

    private InputStream ip = null;
    private Core logic = null;
    private StreamConnection con;

    public ReadThread(StreamConnection con, Core logic) {
        this.con = con;
        this.logic = logic;
    }

    public void run() {
        try {
            boolean isRunning = true;
            String line;
            //Open streams for two way communication.
            ip = con.openInputStream();
            while (isRunning) {
                if ((line = readData()) == null) {
                    isRunning = false;
                } else {
                    // there was some input
                    if (line.trim().equals("bye$$")) {
                        isRunning = false;
                    } else {
                        
                        logic.processData(line);
                        isRunning = false;
                    }
                }
            }
            ip.close();
            con.close();
            System.out.println("Input closed..");
            this.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
