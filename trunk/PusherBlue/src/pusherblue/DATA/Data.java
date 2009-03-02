/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.DATA;

import pusherblue.COMM.Client;
import pusherblue.GUI.GUI;

/**
 *
 * @author Niklas
 */
public abstract class Data {

    private String to;
    private String from;
    private String msg;

    public abstract void sendCom(Client cl);
    public abstract void sendGUI(GUI gui);
    public abstract String convertDataToString();
    public abstract void convertStringToData(String msgString);
}
