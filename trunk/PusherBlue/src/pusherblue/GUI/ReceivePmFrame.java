/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pusherblue.GUI;

import org.kalmeo.kuix.core.Kuix;
import org.kalmeo.kuix.widget.Screen;
import org.kalmeo.kuix.widget.TextArea;
import org.kalmeo.util.frame.Frame;
import pusherblue.CORE.Core;

/**
 *
 * @author Jacxz
 */
public class ReceivePmFrame implements Frame{

    protected Screen screen;
    private String from;
    private String msg;
    private Core logic;

    ReceivePmFrame(String from, String msg, Core logic) {
        this.from = from;
        this.msg = msg;
        this.logic = logic;
    }

    public boolean onMessage(Object identifier, Object[] arguments) {
        if ("Back".equals(identifier)) {
            Kuix.getFrameHandler().removeFrame(this);
			Kuix.getFrameHandler().getTopFrame().onAdded();
            return false;
        }
        if ("Reply".equals(identifier)) {
            System.out.println(((TextArea)screen.getWidget("From")).getText() + ((TextArea)screen.getWidget("Msg")).getText());
            Kuix.getFrameHandler().pushFrame(new SendPmFrame(((TextArea)screen.getWidget("From")).getText(), null));
            return false;
        }
        return true;
    }

    public void onAdded() {
        screen = Kuix.loadScreen("receivePmFrame.xml", null);
        ((TextArea) screen.getWidget("From")).setText(from);
        ((TextArea) screen.getWidget("Msg")).setText(msg);
        screen.setCurrent();
    }

    public void onRemoved() {
        screen.cleanUp();
        screen = null;
    }
}
