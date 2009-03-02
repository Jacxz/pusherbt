package pusherblue.CORE;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.RemoteDevice;
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

    private Server svr;
    private Client cl;
    private Vector userList = new Vector();
    private GUI gui;
    private Vector devices = null;
    private Vector inBox;

    /**
     * Constructor for Core
     * @param gui
     */
    public Core(GUI gui) {
        try {
            this.gui = gui;
            svr = new Server(this);
            svr.start();
            cl = new Client();
            userList = getUsers();
            getUsers();
            inBox = new Vector();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
    /*
     * Processes current data from comm
     * @param msgString String from comm containing from+mess+to
     * 
     */
    public void processData(String msgString) {
        createData(msgString).sendGUI(gui);
    }

    /**
     * gui calls this method, depending on what kind of data different methods
     * will be called
     * @param data Data from GUI
     */
    public void sendData(Data data) {
        data.sendCom(cl);
    }

    /**
     * Called by GUI to get local name
     * @return String name
     */
    public String getFriendlyName() {
        return cl.getLocalFriendlyName();
    }

    /**
     * Creates the appropriate data object depending on the msgstrings and returns it
     *@param msgString String containing from+mess+to
     *@return Data object to be handled by GUI
     */
    private Data createData(String msgString) {
        String type = msgString.substring(0, msgString.indexOf("::"));
        msgString = msgString.substring(msgString.indexOf("::") + 2, msgString.length());
        if (type.equals("PM")) {
            PM pm = new PM(msgString);
            inBox.addElement(pm);
            return pm;
        }
        System.out.println("Kunde inte matcha på någon typ av meddelande!");
        return null;
    }

    /**
     * Gets a vector with remoteDevices from Client.
     * Retreives info from vector and stores it into User objects
     */
    private Vector getUsers() {
        try {
            devices = cl.findDevices();
            userList.removeAllElements();
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
        getUsers();
        String names[] = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.elementAt(i);
            names[i] = user.getName();
        }
        return names;
    }
}
