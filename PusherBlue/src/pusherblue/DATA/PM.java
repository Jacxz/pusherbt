/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pusherblue.DATA;

/**
 *
 * @author Niklas
 */
public class PM extends  Data{
    private String msg;
    private String from;
    private String to;
    public PM(String from, String to, String msg){
        this.msg = msg;
        this.from = from;
        this.to = to;

    }

    public String getMsg() {
        return msg;
    }
}
