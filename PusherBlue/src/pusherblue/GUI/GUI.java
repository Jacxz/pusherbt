/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pusherblue.GUI;

import org.kalmeo.kuix.core.Kuix;
import org.kalmeo.kuix.core.KuixConstants;
import org.kalmeo.kuix.core.KuixMIDlet;
import org.kalmeo.kuix.widget.Desktop;
import pusherblue.CORE.Core;
import pusherblue.DATA.PM;

/**
 *
 * @author Jacxz
 */
public class GUI extends KuixMIDlet{

    private Core logic;

    public void initDesktopStyles() {
        Kuix.loadCss("GUI.css");
    }

    public void initDesktopContent(Desktop arg0) {
        logic = new Core(this);
        Kuix.getFrameHandler().pushFrame(new UserListFrame(logic));
    }

    public void displayPM(PM pm) {
        Kuix.alert("You just received a PM, read now?",
                KuixConstants.ALERT_YES | KuixConstants.ALERT_NO,
                "ReadPM(" + pm.getFrom() + "," + pm.getMsg() +")", null);
    }
}