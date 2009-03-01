/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.COMM;

import java.io.IOException;
import java.io.InputStream;
import pusherblue.CORE.Core;

/**
 *
 * @author Niklas
 */
public class ReadThread extends Thread {

    InputStream ip = null;
    Core logic = null;

    public ReadThread(InputStream ip, Core logic) {
        this.ip = ip;
        this.logic = logic;
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
                    // show in the GUI
                    int pos = line.indexOf(":");
                    String from = line.substring(0,pos-1);
                    String msg = line.substring(pos+1);
                    logic.showPM(from, msg);
                    //System.out.println(from + msg);
                    //String upper = line.trim().toUpperCase();
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
