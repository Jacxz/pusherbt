/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.CORE;

import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import pusherblue.COMM.BTComm;
import pusherblue.DATA.Data;
import pusherblue.DATA.PM;

/**
 *
 * @author Olle & Henrik
 */
public class Core {

    private Data data;
    private BTComm bCom;
    private Vector userList;

    /**
     * Constructor for Core
     */
    public Core() {
        userList = new Vector();
        bCom = new BTComm();

    }

    /**
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

    /**
     * Gets a vector with remoteDevices from BTcomm.
     * Retreives info from vector and stores it into User objects
     */
    private void getUsers() {

        Vector devices = new Vector();
        devices = bCom.getDevices();
        for(int i = 0; i< devices.size();i++){
            RemoteDevice device;
            device = (RemoteDevice) devices.elementAt(i);
            User user = new User(device.getBluetoothAddress());
            userList.addElement(user);
        }
    }

    /**
     * GUI uses this method to get the user names (addresses)
     * @return Array of user names
     */
    public String[] listUsers(){

        String names[] = null;
        for(int i = 0; i< userList.size();i++){
            User user = (User) userList.elementAt(i);
            names[i] = user.getAddress();
        }
        return names;
    }
}
