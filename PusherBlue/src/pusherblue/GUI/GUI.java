/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pusherblue.GUI;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SplashScreen;
import org.netbeans.microedition.lcdui.pda.FileBrowser;
import pusherblue.CORE.Core;
import pusherblue.DATA.PM;

/**
 * @author Niklas
 */
public class GUI extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    private Core logic;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command updateCommand;
    private Command writePMCommand;
    private Command cancelCommand;
    private Command sendPmCommand;
    private Command optionsCommand;
    private Command saveOptionsCommand;
    private Command sendFile;
    private SplashScreen splashScreen;
    private List list;
    private Form sendPM;
    private StringItem pmTo;
    private TextField sendText;
    private Form options;
    private TextField userName;
    private ChoiceGroup color;
    private TextField downloads;
    private ChoiceGroup size;
    private ChoiceGroup font;
    private FileBrowser fileBrowser;
    private Form readPM;
    private StringItem pmFrom;
    private TextField readText;
    private Font listFont;
    private Image splash;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The GUI constructor.
     */
    public GUI() {
        logic = new Core(this);
    }
   
    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getSplashScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
    // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
    // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == fileBrowser) {//GEN-BEGIN:|7-commandAction|1|95-preAction
            if (command == FileBrowser.SELECT_FILE_COMMAND) {//GEN-END:|7-commandAction|1|95-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|2|95-postAction
                // write post-action user code here
            } else if (command == cancelCommand) {//GEN-LINE:|7-commandAction|3|99-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|4|99-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|31-preAction
        } else if (displayable == list) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|5|31-preAction
                // write pre-action user code here
                listAction();//GEN-LINE:|7-commandAction|6|31-postAction
            // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|7|42-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|8|42-postAction
            // write post-action user code here
            } else if (command == optionsCommand) {//GEN-LINE:|7-commandAction|9|86-preAction
                // write pre-action user code here
                switchDisplayable(null, getOptions());//GEN-LINE:|7-commandAction|10|86-postAction
            // write post-action user code here
            } else if (command == sendFile) {//GEN-LINE:|7-commandAction|11|97-preAction
                // write pre-action user code here
                switchDisplayable(null, getFileBrowser());//GEN-LINE:|7-commandAction|12|97-postAction
            // write post-action user code here
            } else if (command == updateCommand) {//GEN-LINE:|7-commandAction|13|51-preAction
                // write pre-action user code here
                listUsers(logic.listUsers());
//GEN-LINE:|7-commandAction|14|51-postAction
            // write post-action user code here
            } else if (command == writePMCommand) {//GEN-LINE:|7-commandAction|15|53-preAction
                // write pre-action user code here
                switchDisplayable(null, getSendPM());//GEN-LINE:|7-commandAction|16|53-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|89-preAction
        } else if (displayable == options) {
            if (command == cancelCommand) {//GEN-END:|7-commandAction|17|89-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|18|89-postAction
            // write post-action user code here
            } else if (command == saveOptionsCommand) {//GEN-LINE:|7-commandAction|19|91-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|20|91-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|21|102-preAction
        } else if (displayable == readPM) {
            if (command == cancelCommand) {//GEN-END:|7-commandAction|21|102-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|22|102-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|23|71-preAction
        } else if (displayable == sendPM) {
            if (command == cancelCommand) {//GEN-END:|7-commandAction|23|71-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|24|71-postAction
            // write post-action user code here
            } else if (command == sendPmCommand) {//GEN-LINE:|7-commandAction|25|74-preAction
                // write pre-action user code here
                logic.sendData(new PM(logic.getFriendlyName(), ((StringItem) sendPM.get(0)).getText(),
                        ((TextField) sendPM.get(1)).getString()));
//GEN-LINE:|7-commandAction|26|74-postAction
                // write post-action user code here
                switchDisplayable(null, getList());
            }//GEN-BEGIN:|7-commandAction|27|24-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|27|24-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|28|24-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|29|7-postCommandAction
        }//GEN-END:|7-commandAction|29|7-postCommandAction
    // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|30|
    //</editor-fold>//GEN-END:|7-commandAction|30|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
        // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of splashScreen component.
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|22-getter|1|22-postInit
            splashScreen.setTitle("Pusher Blue Splash");
            splashScreen.setCommandListener(this);
            splashScreen.setFullScreenMode(true);
            splashScreen.setImage(getSplash());
            splashScreen.setTimeout(100);//GEN-END:|22-getter|1|22-postInit
        // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return splashScreen;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listFont ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of listFont component.
     * @return the initialized component instance
     */
    public Font getListFont() {
        if (listFont == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            listFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);//GEN-LINE:|27-getter|1|27-postInit
        // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return listFont;
    }
    //</editor-fold>//GEN-END:|27-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: list ">//GEN-BEGIN:|29-getter|0|29-preInit
    /**
     * Returns an initiliazed instance of list component.
     * @return the initialized component instance
     */
    public List getList() {
        if (list == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
            list = new List("Online Users", Choice.IMPLICIT);//GEN-BEGIN:|29-getter|1|29-postInit
            list.addCommand(getExitCommand());
            list.addCommand(getUpdateCommand());
            list.addCommand(getWritePMCommand());
            list.addCommand(getOptionsCommand());
            list.addCommand(getSendFile());
            list.setCommandListener(this);
            list.setSelectCommand(null);
            list.setSelectedFlags(new boolean[] {  });//GEN-END:|29-getter|1|29-postInit
            // write post-init user code here
            String users[] = {};
            listUsers(users);
        //listUsers(logic.listUsers());
        }//GEN-BEGIN:|29-getter|2|
        return list;
    }
    //</editor-fold>//GEN-END:|29-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listAction ">//GEN-BEGIN:|29-action|0|29-preAction
    /**
     * Performs an action assigned to the selected list element in the list component.
     */
    public void listAction() {//GEN-END:|29-action|0|29-preAction
        // enter pre-action user code here
        String __selectedString = getList().getString(getList().getSelectedIndex());//GEN-LINE:|29-action|1|29-postAction
    // enter post-action user code here
    }//GEN-BEGIN:|29-action|2|
    //</editor-fold>//GEN-END:|29-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: updateCommand ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initiliazed instance of updateCommand component.
     * @return the initialized component instance
     */
    public Command getUpdateCommand() {
        if (updateCommand == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            updateCommand = new Command("Update", Command.SCREEN, 0);//GEN-LINE:|50-getter|1|50-postInit
        // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return updateCommand;
    }
    //</editor-fold>//GEN-END:|50-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: writePMCommand ">//GEN-BEGIN:|52-getter|0|52-preInit
    /**
     * Returns an initiliazed instance of writePMCommand component.
     * @return the initialized component instance
     */
    public Command getWritePMCommand() {
        if (writePMCommand == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            writePMCommand = new Command("Send Pm", Command.ITEM, 0);//GEN-LINE:|52-getter|1|52-postInit
        // write post-init user code here
        }//GEN-BEGIN:|52-getter|2|
        return writePMCommand;
    }
    //</editor-fold>//GEN-END:|52-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splash ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initiliazed instance of splash component.
     * @return the initialized component instance
     */
    public Image getSplash() {
        if (splash == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|56-getter|1|56-@java.io.IOException
                splash = Image.createImage("/splash.png");
            } catch (java.io.IOException e) {//GEN-END:|56-getter|1|56-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|56-getter|2|56-postInit
        // write post-init user code here
        }//GEN-BEGIN:|56-getter|3|
        return splash;
    }
    //</editor-fold>//GEN-END:|56-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendPM ">//GEN-BEGIN:|62-getter|0|62-preInit
    /**
     * Returns an initiliazed instance of sendPM component.
     * @return the initialized component instance
     */
    public Form getSendPM() {
        if (sendPM == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
            sendPM = new Form("Send PM", new Item[] { getPmTo(), getSendText() });//GEN-BEGIN:|62-getter|1|62-postInit
            sendPM.addCommand(getCancelCommand());
            sendPM.addCommand(getSendPmCommand());
            sendPM.setCommandListener(this);//GEN-END:|62-getter|1|62-postInit
        // write post-init user code here
        }//GEN-BEGIN:|62-getter|2|
        return sendPM;
    }
    //</editor-fold>//GEN-END:|62-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: pmTo ">//GEN-BEGIN:|68-getter|0|68-preInit
    /**
     * Returns an initiliazed instance of pmTo component.
     * @return the initialized component instance
     */
    public StringItem getPmTo() {
        if (pmTo == null) {//GEN-END:|68-getter|0|68-preInit
            // write pre-init user code here
            pmTo = new StringItem("To:", null);//GEN-LINE:|68-getter|1|68-postInit
            // write post-init user code here
            pmTo.setText(list.getString(list.getSelectedIndex()));
        }//GEN-BEGIN:|68-getter|2|
        return pmTo;
    }
    //</editor-fold>//GEN-END:|68-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendText ">//GEN-BEGIN:|69-getter|0|69-preInit
    /**
     * Returns an initiliazed instance of sendText component.
     * @return the initialized component instance
     */
    public TextField getSendText() {
        if (sendText == null) {//GEN-END:|69-getter|0|69-preInit
            // write pre-init user code here
            sendText = new TextField("Message:", null, 150, TextField.ANY);//GEN-LINE:|69-getter|1|69-postInit
        // write post-init user code here
        }//GEN-BEGIN:|69-getter|2|
        return sendText;
    }
    //</editor-fold>//GEN-END:|69-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|70-getter|0|70-preInit
    /**
     * Returns an initiliazed instance of cancelCommand component.
     * @return the initialized component instance
     */
    public Command getCancelCommand() {
        if (cancelCommand == null) {//GEN-END:|70-getter|0|70-preInit
            // write pre-init user code here
            cancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|70-getter|1|70-postInit
        // write post-init user code here
        }//GEN-BEGIN:|70-getter|2|
        return cancelCommand;
    }
    //</editor-fold>//GEN-END:|70-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendPmCommand ">//GEN-BEGIN:|73-getter|0|73-preInit
    /**
     * Returns an initiliazed instance of sendPmCommand component.
     * @return the initialized component instance
     */
    public Command getSendPmCommand() {
        if (sendPmCommand == null) {//GEN-END:|73-getter|0|73-preInit
            // write pre-init user code here
            sendPmCommand = new Command("Send", Command.OK, 0);//GEN-LINE:|73-getter|1|73-postInit
        // write post-init user code here
        }//GEN-BEGIN:|73-getter|2|
        return sendPmCommand;
    }
    //</editor-fold>//GEN-END:|73-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: options ">//GEN-BEGIN:|75-getter|0|75-preInit
    /**
     * Returns an initiliazed instance of options component.
     * @return the initialized component instance
     */
    public Form getOptions() {
        if (options == null) {//GEN-END:|75-getter|0|75-preInit
            // write pre-init user code here
            options = new Form("Options", new Item[] { getUserName(), getFont(), getSize(), getColor(), getDownloads() });//GEN-BEGIN:|75-getter|1|75-postInit
            options.addCommand(getCancelCommand());
            options.addCommand(getSaveOptionsCommand());
            options.setCommandListener(this);//GEN-END:|75-getter|1|75-postInit
        // write post-init user code here
        }//GEN-BEGIN:|75-getter|2|
        return options;
    }
    //</editor-fold>//GEN-END:|75-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: userName ">//GEN-BEGIN:|77-getter|0|77-preInit
    /**
     * Returns an initiliazed instance of userName component.
     * @return the initialized component instance
     */
    public TextField getUserName() {
        if (userName == null) {//GEN-END:|77-getter|0|77-preInit
            // write pre-init user code here
            userName = new TextField("Name:", null, 32, TextField.ANY);//GEN-BEGIN:|77-getter|1|77-postInit
            userName.setLayout(ImageItem.LAYOUT_LEFT | ImageItem.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:|77-getter|1|77-postInit
        // write post-init user code here
        }//GEN-BEGIN:|77-getter|2|
        return userName;
    }
    //</editor-fold>//GEN-END:|77-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: downloads ">//GEN-BEGIN:|81-getter|0|81-preInit
    /**
     * Returns an initiliazed instance of downloads component.
     * @return the initialized component instance
     */
    public TextField getDownloads() {
        if (downloads == null) {//GEN-END:|81-getter|0|81-preInit
            // write pre-init user code here
            downloads = new TextField("Downloads", null, 32, TextField.ANY);//GEN-LINE:|81-getter|1|81-postInit
        // write post-init user code here
        }//GEN-BEGIN:|81-getter|2|
        return downloads;
    }
    //</editor-fold>//GEN-END:|81-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: font ">//GEN-BEGIN:|82-getter|0|82-preInit
    /**
     * Returns an initiliazed instance of font component.
     * @return the initialized component instance
     */
    public ChoiceGroup getFont() {
        if (font == null) {//GEN-END:|82-getter|0|82-preInit
            // write pre-init user code here
            font = new ChoiceGroup("Font", Choice.MULTIPLE);//GEN-LINE:|82-getter|1|82-postInit
        // write post-init user code here
        }//GEN-BEGIN:|82-getter|2|
        return font;
    }
    //</editor-fold>//GEN-END:|82-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: size ">//GEN-BEGIN:|83-getter|0|83-preInit
    /**
     * Returns an initiliazed instance of size component.
     * @return the initialized component instance
     */
    public ChoiceGroup getSize() {
        if (size == null) {//GEN-END:|83-getter|0|83-preInit
            // write pre-init user code here
            size = new ChoiceGroup("Size", Choice.POPUP);//GEN-LINE:|83-getter|1|83-postInit
            // write post-init user code here
            size.insert(0, "10", null);
            size.insert(1, "12", null);
        }//GEN-BEGIN:|83-getter|2|
        return size;
    }
    //</editor-fold>//GEN-END:|83-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: color ">//GEN-BEGIN:|84-getter|0|84-preInit
    /**
     * Returns an initiliazed instance of color component.
     * @return the initialized component instance
     */
    public ChoiceGroup getColor() {
        if (color == null) {//GEN-END:|84-getter|0|84-preInit
            // write pre-init user code here
            color = new ChoiceGroup("Color", Choice.MULTIPLE);//GEN-LINE:|84-getter|1|84-postInit
        // write post-init user code here
        }//GEN-BEGIN:|84-getter|2|
        return color;
    }
    //</editor-fold>//GEN-END:|84-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: optionsCommand ">//GEN-BEGIN:|85-getter|0|85-preInit
    /**
     * Returns an initiliazed instance of optionsCommand component.
     * @return the initialized component instance
     */
    public Command getOptionsCommand() {
        if (optionsCommand == null) {//GEN-END:|85-getter|0|85-preInit
            // write pre-init user code here
            optionsCommand = new Command("Options", Command.SCREEN, 0);//GEN-LINE:|85-getter|1|85-postInit
        // write post-init user code here
        }//GEN-BEGIN:|85-getter|2|
        return optionsCommand;
    }
    //</editor-fold>//GEN-END:|85-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: saveOptionsCommand ">//GEN-BEGIN:|90-getter|0|90-preInit
    /**
     * Returns an initiliazed instance of saveOptionsCommand component.
     * @return the initialized component instance
     */
    public Command getSaveOptionsCommand() {
        if (saveOptionsCommand == null) {//GEN-END:|90-getter|0|90-preInit
            // write pre-init user code here
            saveOptionsCommand = new Command("Save", Command.OK, 0);//GEN-LINE:|90-getter|1|90-postInit
        // write post-init user code here
        }//GEN-BEGIN:|90-getter|2|
        return saveOptionsCommand;
    }
    //</editor-fold>//GEN-END:|90-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendFile ">//GEN-BEGIN:|96-getter|0|96-preInit
    /**
     * Returns an initiliazed instance of sendFile component.
     * @return the initialized component instance
     */
    public Command getSendFile() {
        if (sendFile == null) {//GEN-END:|96-getter|0|96-preInit
            // write pre-init user code here
            sendFile = new Command("Send File", Command.SCREEN, 1);//GEN-LINE:|96-getter|1|96-postInit
        // write post-init user code here
        }//GEN-BEGIN:|96-getter|2|
        return sendFile;
    }
    //</editor-fold>//GEN-END:|96-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fileBrowser ">//GEN-BEGIN:|93-getter|0|93-preInit
    /**
     * Returns an initiliazed instance of fileBrowser component.
     * @return the initialized component instance
     */
    public FileBrowser getFileBrowser() {
        if (fileBrowser == null) {//GEN-END:|93-getter|0|93-preInit
            // write pre-init user code here
            fileBrowser = new FileBrowser(getDisplay());//GEN-BEGIN:|93-getter|1|93-postInit
            fileBrowser.setTitle("fileBrowser");
            fileBrowser.setCommandListener(this);
            fileBrowser.addCommand(FileBrowser.SELECT_FILE_COMMAND);
            fileBrowser.addCommand(getCancelCommand());//GEN-END:|93-getter|1|93-postInit
        // write post-init user code here
        }//GEN-BEGIN:|93-getter|2|
        return fileBrowser;
    }
    //</editor-fold>//GEN-END:|93-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: readPM ">//GEN-BEGIN:|101-getter|0|101-preInit
    /**
     * Returns an initiliazed instance of readPM component.
     * @return the initialized component instance
     */
    public Form getReadPM() {
        if (readPM == null) {//GEN-END:|101-getter|0|101-preInit
            // write pre-init user code here
            readPM = new Form("readPM", new Item[] { getPmFrom(), getReadText() });//GEN-BEGIN:|101-getter|1|101-postInit
            readPM.addCommand(getCancelCommand());
            readPM.setCommandListener(this);//GEN-END:|101-getter|1|101-postInit

        // write post-init user code here
        }//GEN-BEGIN:|101-getter|2|
        return readPM;
    }
    //</editor-fold>//GEN-END:|101-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: pmFrom ">//GEN-BEGIN:|104-getter|0|104-preInit
    /**
     * Returns an initiliazed instance of pmFrom component.
     * @return the initialized component instance
     */
    public StringItem getPmFrom() {
        if (pmFrom == null) {//GEN-END:|104-getter|0|104-preInit
            // write pre-init user code here
            pmFrom = new StringItem("From:", null);//GEN-LINE:|104-getter|1|104-postInit

        // write post-init user code here
        }//GEN-BEGIN:|104-getter|2|
        return pmFrom;
    }
    //</editor-fold>//GEN-END:|104-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: readText ">//GEN-BEGIN:|105-getter|0|105-preInit
    /**
     * Returns an initiliazed instance of readText component.
     * @return the initialized component instance
     */
    public TextField getReadText() {
        if (readText == null) {//GEN-END:|105-getter|0|105-preInit
            // write pre-init user code here
            readText = new TextField("Message:", null, 32, TextField.ANY);//GEN-LINE:|105-getter|1|105-postInit
        // write post-init user code here
        }//GEN-BEGIN:|105-getter|2|
        return readText;
    }
    //</editor-fold>//GEN-END:|105-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * ListUsers
     * populates the list screen with users
     */
    private void listUsers(String[] users) {
        logic.listUsers();
        if (users.length > 0) {
            list.deleteAll();
            for (int i = 0; i < users.length; i++) {
                list.insert(i, users[i], null);
            }
            for (int i = 0; i < users.length; i++) {
                list.setFont(i, getListFont());
            }
        }
    }

    /**
     * recievePM
     * asks user to display PM or not.
     */
    public void recievePM() {
    }

    /**
     * Called to display PM.
     */
    public void displayPM(PM pm) {
        String from = pm.getFrom();
        String msg = pm.getMsg();
        System.out.println("GUI.displayPM " + from + msg);


        Form rpm = getReadPM();
        ((StringItem) rpm.get(0)).setText(from);
        ((TextField) rpm.get(1)).setString(msg);
        System.out.println(((TextField) rpm.get(1)).getString());
        //((StringItem)readPM.get(0)).setText(from);
        //((TextField)readPM.get(1)).setString(msg);
        switchDisplayable(null, rpm);
    //System.out.println("GUI gets: " + from + msg);
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }
}
