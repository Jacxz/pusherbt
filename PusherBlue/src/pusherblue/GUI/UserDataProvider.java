/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pusherblue.GUI;

import org.kalmeo.kuix.core.model.DataProvider;

/**
 *
 * @author Jacxz
 */
public class UserDataProvider extends DataProvider{

    private String userName;

    public UserDataProvider(String userName) {
        this.userName = userName;
        dispatchUpdateEvent("userName");
    }

    protected Object getUserDefinedValue(String property) {
        if ("userName".equals(property)) {
            return this.userName;
        }
        return null;
    }
}
