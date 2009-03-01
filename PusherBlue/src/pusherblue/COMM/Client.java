/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.COMM;

import java.io.*;
import java.util.Vector;
import javax.bluetooth.*;
import javax.microedition.io.*;
import pusherblue.DATA.Data;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Niklas
 */
public class Client implements DiscoveryListener {

    private static LocalDevice localDevice = null;
    private DiscoveryAgent discoveryAgent = null;
    private String connectionURL = null;
    private Vector device = new Vector();
    private Vector records = new Vector();
    //private ServiceRecord[] records = null;
    int count = 0;
    int maxSearches = 10;
    InputStream ip = null;
    OutputStream op = null;

    public Client() throws IOException, InterruptedException {
        localDevice = LocalDevice.getLocalDevice();
        discoveryAgent = localDevice.getDiscoveryAgent();
    }

    public Vector findDevices() throws IOException, InterruptedException {
// Starts inquiry for devices in the proximity and waits till the
//inquiry is completed.
        System.out.println("\nSearching for Devices...\n");
        discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
        synchronized (this) {
            this.wait();
        }

//Once the Device inquiry is completed it starts searching for the
//required service. service search is done with the given uuid.
//After starting each search it waits for the result. If the
//connectionURL is null, ie, if No service Records obtained, then
//it continues search in the next device detected.
        int[] attrSet = {0, 3, 4, 0x100};
        UUID[] uuids = new UUID[1];
        uuids[0] = new UUID(0x1101);
        for (int i = 0; i < device.size(); i++) {
            RemoteDevice rd = (RemoteDevice) device.elementAt(i);
            int transactionid = discoveryAgent.searchServices(attrSet, uuids, rd, this);

            if (transactionid != -1) {
                synchronized (this) {
                    this.wait();
                }
            //System.out.println(records[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false));

            }
        /**
        if (connectionURL != null) {
        break;
        } */
        }
        /** // end of forloop
        //If the URL of the device begins with btspp, ie,of an SPP server then
        //we call the getConnection meethod which
        //establishes a connection with the SPPServer and returns it. Connection
        // returned is of type  StreamConnection.
        //A piece of raw data is being sent over RFCOMM.

        if (connectionURL == null) {
        System.out.println("No service available...........");
        } else if (connectionURL.startsWith("btspp")) {
        StreamConnection connection = getconnection();
        op = connection.openOutputStream();
        ip = connection.openInputStream();
        //tas bort
        } */
        return device;
    }

    public void writeData(String to, String msg) throws IOException {
        ServiceRecord sr = getRemote(to);
        sr.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
        StreamConnection connection = getconnection();
        op = connection.openOutputStream();
        String message = new String(localDevice.getFriendlyName() + ":" + msg);
        op.write(message.length());
        op.write(message.getBytes());
        op.close();
        connection.close();

    }

    private ServiceRecord getRemote(String to) {
        ServiceRecord sr = null;

        RemoteDevice rd;
        for (int i = 0; i < device.size(); i++) {
            rd = (RemoteDevice) device.elementAt(i);
            try {
                if (to.equals(rd.getFriendlyName(false))) {
                    //sr = records[i];
                    sr = (ServiceRecord) records.elementAt(i);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return sr;
    }
//When a device is discovered it is added to the remote device table.

    public synchronized void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        try {
            if (!device.contains(btDevice)) {
                System.out.println("New Device discovered : " + btDevice.getFriendlyName(false) + " (" + btDevice.getBluetoothAddress() + ")");
                device.addElement(btDevice);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//When a service is discovered in a particular device and the connection url is not null //then the thread that is waiting in the main is notified.
    public synchronized void servicesDiscovered(int transID,
            ServiceRecord[] servRecords) {

        //records = new ServiceRecord[servRecords.length];
        //records = servRecords;
        records.removeAllElements();
        for (int i = 0; i < servRecords.length; i++) {
            records.addElement(servRecords[i]);
            int[] atrids = servRecords[i].getAttributeIDs();
            String servName = (String) ((DataElement) servRecords[i].getAttributeValue(0x100)).getValue();
            System.out.println("Service Name : " + servName);
            connectionURL = servRecords[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, true);
            System.out.println("Connection url :" + connectionURL);
            if (connectionURL != null) {
                synchronized (this) {
                    this.notify();
                }
                break;
            }
        }
    }
//This function notifies the Thread waiting in main if a service search is terminated,ie,ig //the responsecode is SERVICE_SEARCH_COMPLETED or //SERVICE_SEARCH_NO_RECORDS

    public synchronized void serviceSearchCompleted(int transID, int respCode) {

        if (respCode == SERVICE_SEARCH_ERROR) {
            System.out.println("\nSERVICE_SEARCH_ERROR\n");
        }
        if (respCode == SERVICE_SEARCH_COMPLETED) {
            System.out.println("\nSERVICE_SEARCH_COMPLETED\n");
        }
        if (respCode == SERVICE_SEARCH_TERMINATED) {
            System.out.println("\n SERVICE_SEARCH_TERMINATED\n");
        }
        if (respCode == SERVICE_SEARCH_NO_RECORDS) {
            synchronized (this) {
                this.notify();
            }
            System.out.println("\n SERVICE_SEARCH_NO_RECORDS\n");
        }
        if (respCode == SERVICE_SEARCH_DEVICE_NOT_REACHABLE) {
            System.out.println("\n SERVICE_SEARCH_DEVICE_NOT_REACHABLE\n");
        }
    }
//Once the device inquiry is completed it notifies the Thread that waits in the Main.

    public synchronized void inquiryCompleted(int discType) {
        this.notify();
    }

    StreamConnection getconnection() throws IOException {
        return (StreamConnection) Connector.open(connectionURL);
    }

    //public Vector getDevices() {
    //    return device;
    //}

    //public ServiceRecord[] getRecords() {
    //    return records;
    //}
}
