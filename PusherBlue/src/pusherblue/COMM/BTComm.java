/**
 *
 * @author Niklas
 */
package pusherblue.COMM;


import java.io.IOException;
import java.util.*;
//Bluetooth imports
import javax.bluetooth.*;


public class BTComm implements Runnable, DiscoveryListener {

    //Local device
    private LocalDevice device;

    private DiscoveryAgent agent;
  
    private Object lock = new Object();
    // list of RemoteDevice discovered
    private static Vector devices = new Vector();
    // list of ServiceRecord discovered for one RemoteDevice
    private static Vector services = new Vector();
    
    private Thread processorThread;

    public BTComm(){
        processorThread = new Thread(this);
        processorThread.start();
        System.out.println("TRÅD STARTAD");
    }
    public void run() {
        
           doDiscoverDevice();
        
    }
//****************************************************************************
//Perform Bluetooth device discovery.
//****************************************************************************
    public void doDiscoverDevice() {
        try {
            device = LocalDevice.getLocalDevice();
            agent = device.getDiscoveryAgent();
            //BTComm btcomm = new BTComm();
            System.out.println("Local: " + device.getBluetoothAddress() +
                    " (" + device.getFriendlyName() + ")");

            System.out.println("Starting device inquiry...");
            agent.startInquiry(DiscoveryAgent.GIAC, this);
            try {
                synchronized(lock){
				lock.wait(); // until devices are found
                }
            } catch (InterruptedException e) {
                System.err.println("Unexpected interruption: " + e);
            }
            System.out.println("Device Inquiry Completed. ");
            System.out.println("Antal enheter i listan: " + devices.size());
            //print all devices in vecDevices
            /**int deviceCount=devices.size();
            if(deviceCount <= 0){
                System.out.println("No Devices Found .");
            }
            else{
                //print bluetooth device addresses and names in the format [ No. address (name) ]
                System.out.println("Bluetooth Devices: ");
                for (int i = 0; i <deviceCount; i++) {
                    RemoteDevice remoteDevice=(RemoteDevice)devices.elementAt(i);
                    try {
                        System.out.println((i + 1) + ". " + remoteDevice.getBluetoothAddress() + " (" + remoteDevice.getFriendlyName(true) + ")");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            */
        } catch (BluetoothStateException exp) {
            System.err.println("Unexpected interruption: " + exp);
        }
        
    }

//****************************************************************************
//Perform service discovery on a remote device.
//****************************************************************************
    public void doDiscoverService(RemoteDevice remote) {

    }

//****************************************************************************
//This function is invoked for each RemoteDevice discovered by JABWT
//****************************************************************************
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass){
        if(!devices.contains(remoteDevice))
            devices.addElement(remoteDevice);
    }

//****************************************************************************
//This function is invoked when the device discovery is completed
//****************************************************************************
    public void inquiryCompleted(int complete){
        synchronized(lock){
			lock.notify();
		}
        if(devices.size() == 0){
          //What to do when no device found
        }
        else{
          System.out.println("Sökning klar, finns devices i listan...");
        }
    }

//****************************************************************************
//This function is invoked when services are found on a RemoteDevice
//****************************************************************************
    public void servicesDiscovered(int transId, ServiceRecord[] records){
        if(records.length == 0){

        }
        for(int i = 0; i < records.length; i++){
            ServiceRecord record = records[i];
            services.addElement(record);
        }
    }

  //****************************************************************************
  //This function is invoked when service discovery is completed on a RemoteDevice
  //****************************************************************************
    public void serviceSearchCompleted(int transId, int complete){
        if(complete == 3){ //SERVICE_SEARCH_ERROR
            System.out.println("\nSERVICE_SEARCH_ERROR\n");
        }
        if(complete == 1){ //SERVICE_SEARCH_COMPLETED
            System.out.println("\nSERVICE_SEARCH_COMPLETED\n");
        }
        if(complete == 2){ //SERVICE_SEARCH_TERMINATED
            System.out.println("\n SERVICE_SEARCH_TERMINATED\n");
        }
        if(complete == 4){ //SERVICE_SEARCH_NO_RECORDS
            System.out.println("\n SERVICE_SEARCH_NO_RECORDS\n");
        }
        if(complete == 6){ //SERVICE_SEARCH_DEVICE_NOT_REACHABLE
            System.out.println("\n SERVICE_SEARCH_DEVICE_NOT_REACHABLE\n");
        }
    }

    public Vector getDevices() {
        return devices;
    }

    public Vector getServices() {
        return services;
    }
}
