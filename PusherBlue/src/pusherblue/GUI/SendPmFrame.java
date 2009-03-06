/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pusherblue.GUI;

import org.kalmeo.kuix.core.Kuix;
import org.kalmeo.kuix.widget.Screen;
import org.kalmeo.kuix.widget.Text;
import org.kalmeo.kuix.widget.TextField;
import org.kalmeo.util.frame.Frame;
import pusherblue.CORE.Core;
import pusherblue.DATA.PM;

/**
 *
 * @author Jacxz
 */
public class SendPmFrame implements Frame{

    protected Screen screen;
    private String to;
    private Core logic;

    SendPmFrame(String to, Core logic) {
        this.to = to;
        this.logic = logic;
        System.out.println(to);
    }

    public boolean onMessage(Object identifier, Object[] arguments) {
        if ("Back".equals(identifier)) {
            Kuix.getFrameHandler().removeFrame(this);
			Kuix.getFrameHandler().getTopFrame().onAdded();
            return false;
        }
        if ("SendPM".equals(identifier)) {
            /*System.out.println("To: " +
                    ((Text)screen.getWidget("To")).getText().substring(4) +
                    " Msg: " + ((TextField)screen.getWidget("Msg")).getText());*/
            logic.sendData(new PM(logic.getFriendlyName(),
                    ((Text)screen.getWidget("To")).getText().substring(4),
                    ((TextField)screen.getWidget("Msg")).getText()));
            Kuix.getFrameHandler().removeFrame(this);
			Kuix.getFrameHandler().getTopFrame().onAdded();
            return false;
        }
        return true;
    }

    public void onAdded() {        
        screen = Kuix.loadScreen("sendPmFrame.xml", null);
        ((Text) screen.getWidget("To")).setText("To: " + to);
        screen.setCurrent();
    }

    public void onRemoved() {
        screen.cleanUp();
        screen = null;
    }
}
