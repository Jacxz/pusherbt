/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.GUI;

import org.kalmeo.kuix.core.Kuix;
import org.kalmeo.kuix.core.model.DataProvider;
import org.kalmeo.kuix.widget.List;
import org.kalmeo.kuix.widget.Screen;
import org.kalmeo.kuix.widget.Widget;
import org.kalmeo.util.frame.Frame;
import pusherblue.CORE.Core;
/**
 *
 * @author Jacxz
 */
public class UserListFrame implements Frame {

    protected Screen screen;
    protected DataProvider userProvider = new DataProvider();
    private Core logic;

    UserListFrame(Core logic) {
        this.logic = logic;
    }

    public boolean onMessage(Object identifier, Object[] arguments) {
        if ("PM".equals(identifier)) {
            Widget reciever = screen.getWidget("UserList").getChild();
            String id = null;
            while(true){
                if(reciever.isFocused()){
                    id = reciever.getId();
                    break;
                }
                reciever = reciever.next;
            }
            Kuix.getFrameHandler().pushFrame(new SendPmFrame(id, logic));
            return false;
        }
        if ("Update".equals(identifier)) {
            ((List)screen.getWidget("UserList")).removeAllItems();
            ((List)screen.getWidget("UserList")).cleanUpChildren();
            userProvider.removeAllItems("users");
            String[] users = logic.listUsers();
            for (int i = 0 ; i < users.length ; i++)
                userProvider.addItem("users", new UserDataProvider(users[i]));
        
            return false;
        }
        if ("Inbox".equals(identifier)) {
            return false;
        }
        if ("Chat".equals(identifier))
            return false;
        if ("File".equals(identifier))
            return false;
        if ("Options".equals(identifier))
            return false;
        if ("Exit".equals(identifier)) {
            GUI.getDefault().destroyImpl();
            return false;
        }
        if ("ReadPM".equals(identifier)) {
            System.out.println("UserListFrame.ReadPM To: " + (String)arguments[0] +
                                " From: " + (String)arguments[1]);
            Kuix.getFrameHandler().pushFrame(new ReceivePmFrame((String)arguments[0],
                                            (String)arguments[1], logic));
            return false;
        }
        return true;
    }

    public void onAdded() {
        screen = Kuix.loadScreen("GUI.xml", userProvider);
        screen.setCurrent();
        ((List)screen.getWidget("UserList")).removeAllItems();
        ((List)screen.getWidget("UserList")).cleanUpChildren();
//        userProvider.removeAllItems("users");
//        String[] users = logic.listUsers();
//        for (int i = 0 ; i < users.length ; i++)
//            userProvider.addItem("users", new UserDataProvider(users[i]));
    }

    public void onRemoved() {
        screen.cleanUp();
        screen = null;
    }
}