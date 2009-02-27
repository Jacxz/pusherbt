/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pusherblue.CORE;
/**
 *Class to store infomation about a user
 * @author Olle & Henrik
 */
public class User {

    private String address;
    private String name;

    /**
     * Constructor for User
     * @param name
     * @param address BlueTooth address
     */
    public User(String name, String address) {
        this.address = address;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public String getAddress(){

        return address;
    }

}
