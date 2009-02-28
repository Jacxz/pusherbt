/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.CORE;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.microedition.lcdui.Item;
import pusherblue.COMM.Client;
import pusherblue.COMM.Server;
import pusherblue.DATA.Data;
import pusherblue.DATA.PM;
import pusherblue.GUI.GUI;

/**
 *Class to handle logic
 * @author Olle & Henrik
 */
public class Core {

    private Data data;
    private Server svr;
    private Client cl;
    private Vector userList;
    private User mySelf;
    private GUI gui;
    private Vector devices = null;

    /**
     * Constructor for Core
     * @param gui
     */
    public Core(GUI gui) {
        try {
            this.gui = gui;
            userList = new Vector();
            svr = new Server();
            svr.start();
            cl = new Client(); 
            getUsers(); 
            //listUsers();
            userList = getUsers();
            
            //devices = cl.findDevices();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

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
    public void showPM(String from, String msg){
        //gui
    }
    public void sendPM(String to, String msg) {
        try {
            System.out.println(msg);
            cl.writeData(to, msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

        /**
     * Gets a vector with remoteDevices from Client.
     * Retreives info from vector and stores it into User objects
     */
    public Vector getUsers() {
        try {
            devices = cl.findDevices();
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
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    /**
     * GUI uses this method to get the user names 
     * @return Array of user names
     */
    public String[] listUsers() {
        //getUsers();
        String names[] = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.elementAt(i);
            names[i] = user.getName();
        }
        return names;
    }
}
