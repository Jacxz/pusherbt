/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.DATA;

import java.io.IOException;
import pusherblue.COMM.Client;
import pusherblue.GUI.GUI;

/**
 *
 * @author Niklas
 */
public class PM extends Data {

    private String msg;
    private String from;
    private String to;

    public PM(String from, String to, String msg) {
        this.msg = msg;
        this.from = from;
        this.to = to;
    }

    public PM(String msgString) {
        convertStringToData(msgString);
    }

    public String getMsg() {
        return msg;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String convertDataToString() {
        return ("PM::" + getFrom() + ":@" + getMsg() + ":@" + getTo());
    }

    /**
     * Send the data object to gui
     */
    public void sendGUI(GUI gui) {
        gui.displayPM(this);
    }

    public void sendCom(Client cl) {
        try {
            cl.writeData(to, convertDataToString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void convertStringToData(String msgString) {

        int i = 0;
        int curr = 0;
        String tmp = "";
        String[] msgAr = {new String(), new String(), new String()};

        while (i < msgString.length()) {
            tmp += msgString.charAt(i);
            if (tmp.equals(":")) {                                                            //if the current letter is : check if the following letter
                if (tmp.replace(msgString.charAt(i), msgString.charAt(i + 1)).equals("@")) {  // is @. Then change to a new word
                    i = i + 2;
                    curr++;
                }
            }
            msgAr[curr] += msgString.charAt(i);
            i++;
            tmp = "";
        }

        //set the data values
        from = msgAr[0];
        msg = msgAr[1];
        to = msgAr[2];
    }
}
