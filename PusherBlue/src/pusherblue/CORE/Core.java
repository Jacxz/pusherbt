/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.CORE;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import javax.microedition.lcdui.Item;
import pusherblue.COMM.Client;
import pusherblue.COMM.Server;
import pusherblue.DATA.Data;
import pusherblue.DATA.PM;
import pusherblue.GUI.GUI;

/**
 *
 * @author Olle & Henrik
 */
public class Core {

    private Data data;
    private Server svr;
    private Client cl;
    private Vector userList;
    private User mySelf;
    private GUI gui;

    /**
     * Constructor for Core
     */
    public Core(GUI gui) {
        
            this.gui = gui;
            userList = new Vector();
            svr = new Server();
            svr.start();
            cl = new Client();

    }
    /*
     * Processes current data
     * @param data
     * @return
     */

    public Data processData(Data data) {
        this.data = data;

        if (data instanceof PM) {
            //...
        }
        return data;
    }

    /**
     * Initializes the chat
     */
    public void initChat() {
    }

    public void sendPM(Item to, Item msg) {
        try {
            cl.writeData(to.toString(), msg.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets a vector with remoteDevices from BTcomm.
     * Retreives info from vector and stores it into User objects
     */
    private void getUsers() {

        Vector devices = new Vector();
        devices = cl.getDevices();

        for (int i = 0; i < devices.size(); i++) {
            RemoteDevice device;
            device = (RemoteDevice) devices.elementAt(i);
            User user = null;
            try {
                user = new User(device.getFriendlyName(false), device.getBluetoothAddress());
            } catch (IOException ex) {
                System.out.println("Problem getting friendlyname...");
                ex.printStackTrace();
            }
            userList.addElement(user);
        }
    }

    /**
     * GUI uses this method to get the user names 
     * @return Array of user names
     */
    public String[] listUsers() {

        String names[] = null;
        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.elementAt(i);
            names[i] = user.getName();
        }
        return names;
    }
}
