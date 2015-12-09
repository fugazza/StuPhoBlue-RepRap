/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuphobluereprap;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author ajgl
 */
public final class StuPhoBlueRepRapMIDlet extends MIDlet implements CommandListener {
    
    private boolean midletPaused = false;
    
    private Vector bluetoothDevices;

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand2;
    private Command exitCommand;
    private Command backCommand;
    private Command screenCommand;
    private Command okCommand;
    private Command exitCommand1;
    private Command itemCommand;
    private List List;
    private WaitScreen WaitScreen;
    private Alert Alert;
    private TextBox textBox;
    private SimpleCancellableTask task;
    private Image image1;
    private Image image2;
    private SimpleCancellableTask simpleCancellableTask;
//</editor-fold>//GEN-END:|fields|0|

    /**
     * The HelloMIDlet constructor.
     */
    public StuPhoBlueRepRapMIDlet() {
    }

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
//</editor-fold>//GEN-END:|methods|0|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initializes the application. It is called only once when the MIDlet is
     * started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {
//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
}//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {
//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
switchDisplayable(null, getTextBox());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here

    }//GEN-BEGIN:|3-startMIDlet|2|
//</editor-fold>//GEN-END:|3-startMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {
//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
}//GEN-BEGIN:|4-resumeMIDlet|2|
//</editor-fold>//GEN-END:|4-resumeMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code>
     * instance is taken from <code>getDisplay</code> method. This method is
     * used by all actions in the design for switching displayable.
     *
     * @param alert the Alert which is temporarily set to the display; if
     * <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {
//GEN-END:|5-switchDisplayable|0|5-preSwitch
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
     * Called by a system to indicated that a command has been invoked on a
     * particular displayable.
     *
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {
//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        getList();
if (displayable == List) {//GEN-BEGIN:|7-commandAction|1|58-preAction
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|1|58-preAction
                // write pre-action user code here
ListAction();//GEN-LINE:|7-commandAction|2|58-postAction
                // write post-action user code here
} else if (command == backCommand) {//GEN-LINE:|7-commandAction|3|80-preAction
                // write pre-action user code here
switchDisplayable(null, getTextBox());//GEN-LINE:|7-commandAction|4|80-postAction
                // write post-action user code here
} else if (command == exitCommand) {//GEN-LINE:|7-commandAction|5|62-preAction
                // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|6|62-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|7|74-preAction
} else if (displayable == WaitScreen) {
    if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|7|74-preAction
        // write pre-action user code here
switchDisplayable(getAlert(), getTextBox());//GEN-LINE:|7-commandAction|8|74-postAction
 // write post-action user code here
} else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|9|73-preAction
 // write pre-action user code here
switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|10|73-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|11|95-preAction
} else if (displayable == textBox) {
    if (command == exitCommand1) {//GEN-END:|7-commandAction|11|95-preAction
        // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|12|95-postAction
        // write post-action user code here
} else if (command == itemCommand) {//GEN-LINE:|7-commandAction|13|98-preAction
        // write pre-action user code here
switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|14|98-postAction
 // write post-action user code here
} else if (command == okCommand) {//GEN-LINE:|7-commandAction|15|92-preAction
        // write pre-action user code here
switchDisplayable(null, getWaitScreen());//GEN-LINE:|7-commandAction|16|92-postAction
        // write post-action user code here
}//GEN-BEGIN:|7-commandAction|17|7-postCommandAction
        }//GEN-END:|7-commandAction|17|7-postCommandAction
        // write post-action user code here
}//GEN-BEGIN:|7-commandAction|18|
//</editor-fold>//GEN-END:|7-commandAction|18|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand2 ">//GEN-BEGIN:|49-getter|0|49-preInit
    /**
     * Returns an initialized instance of exitCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand2() {
        if (exitCommand2 == null) {
//GEN-END:|49-getter|0|49-preInit
 // write pre-init user code here
exitCommand2 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|49-getter|1|49-postInit
 // write post-init user code here
}//GEN-BEGIN:|49-getter|2|
        return exitCommand2;
    }
//</editor-fold>//GEN-END:|49-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|61-getter|0|61-preInit
    /**
     * Returns an initialized instance of exitCommand component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {
//GEN-END:|61-getter|0|61-preInit
 // write pre-init user code here
exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|61-getter|1|61-postInit
 // write post-init user code here
}//GEN-BEGIN:|61-getter|2|
        return exitCommand;
    }
//</editor-fold>//GEN-END:|61-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: List ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initialized instance of List component.
     *
     * @return the initialized component instance
     */
    public List getList() {
        if (List == null) {
//GEN-END:|57-getter|0|57-preInit
 // write pre-init user code here
List = new List("List", Choice.IMPLICIT);//GEN-BEGIN:|57-getter|1|57-postInit
            List.addCommand(getExitCommand());
            List.addCommand(getBackCommand());
            List.setCommandListener(this);
            List.setSelectedFlags(new boolean[]{});//GEN-END:|57-getter|1|57-postInit
 // write post-init user code here
}//GEN-BEGIN:|57-getter|2|
        return List;
    }
//</editor-fold>//GEN-END:|57-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: ListAction ">//GEN-BEGIN:|57-action|0|57-preAction
    /**
     * Performs an action assigned to the selected list element in the List
     * component.
     */
    public void ListAction() {
//GEN-END:|57-action|0|57-preAction
 // enter pre-action user code here
String __selectedString = getList().getString(getList().getSelectedIndex());//GEN-LINE:|57-action|1|57-postAction
 // enter post-action user code here
}//GEN-BEGIN:|57-action|2|
//</editor-fold>//GEN-END:|57-action|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|79-getter|0|79-preInit
    /**
     * Returns an initialized instance of backCommand component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {
//GEN-END:|79-getter|0|79-preInit
 // write pre-init user code here
backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|79-getter|1|79-postInit
 // write post-init user code here
}//GEN-BEGIN:|79-getter|2|
        return backCommand;
    }
//</editor-fold>//GEN-END:|79-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: WaitScreen ">//GEN-BEGIN:|70-getter|0|70-preInit
    /**
     * Returns an initialized instance of WaitScreen component.
     *
     * @return the initialized component instance
     */
    public WaitScreen getWaitScreen() {
        if (WaitScreen == null) {
//GEN-END:|70-getter|0|70-preInit
 // write pre-init user code here
WaitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|70-getter|1|70-postInit
            WaitScreen.setCommandListener(this);
            WaitScreen.setImage(getImage2());
            WaitScreen.setText("Cekejte prosim");
            WaitScreen.setTask(getSimpleCancellableTask());//GEN-END:|70-getter|1|70-postInit
 // write post-init user code here
}//GEN-BEGIN:|70-getter|2|
        return WaitScreen;
    }
//</editor-fold>//GEN-END:|70-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Alert ">//GEN-BEGIN:|76-getter|0|76-preInit
    /**
     * Returns an initialized instance of Alert component.
     *
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (Alert == null) {
//GEN-END:|76-getter|0|76-preInit
 // write pre-init user code here
Alert = new Alert("Alert", "Vyhledani Bluetooth se nezdarilo", getImage1(), null);//GEN-BEGIN:|76-getter|1|76-postInit
            Alert.setTimeout(Alert.FOREVER);//GEN-END:|76-getter|1|76-postInit
 // write post-init user code here
}//GEN-BEGIN:|76-getter|2|
        return Alert;
    }
//</editor-fold>//GEN-END:|76-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|75-getter|0|75-preInit
    /**
     * Returns an initialized instance of task component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {
//GEN-END:|75-getter|0|75-preInit
 // write pre-init user code here
task = new SimpleCancellableTask();//GEN-BEGIN:|75-getter|1|75-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|75-getter|1|75-execute
// write task-execution user code here
}//GEN-BEGIN:|75-getter|2|75-postInit
            });//GEN-END:|75-getter|2|75-postInit
 // write post-init user code here
}//GEN-BEGIN:|75-getter|3|
        return task;
    }
//</editor-fold>//GEN-END:|75-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image1 ">//GEN-BEGIN:|77-getter|0|77-preInit
    /**
     * Returns an initialized instance of image1 component.
     *
     * @return the initialized component instance
     */
    public Image getImage1() {
        if (image1 == null) {
//GEN-END:|77-getter|0|77-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|77-getter|1|77-@java.io.IOException
                image1 = Image.createImage("/stuphobluereprap/alert.png");
            } catch (java.io.IOException e) {//GEN-END:|77-getter|1|77-@java.io.IOException
    e.printStackTrace();}//GEN-LINE:|77-getter|2|77-postInit
 // write post-init user code here
}//GEN-BEGIN:|77-getter|3|
        return image1;
    }
//</editor-fold>//GEN-END:|77-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image2 ">//GEN-BEGIN:|78-getter|0|78-preInit
    /**
     * Returns an initialized instance of image2 component.
     *
     * @return the initialized component instance
     */
    public Image getImage2() {
        if (image2 == null) {
//GEN-END:|78-getter|0|78-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|78-getter|1|78-@java.io.IOException
                image2 = Image.createImage("/stuphobluereprap/sandglass.png");
            } catch (java.io.IOException e) {//GEN-END:|78-getter|1|78-@java.io.IOException
    e.printStackTrace();}//GEN-LINE:|78-getter|2|78-postInit
 // write post-init user code here
}//GEN-BEGIN:|78-getter|3|
        return image2;
    }
//</editor-fold>//GEN-END:|78-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: simpleCancellableTask ">//GEN-BEGIN:|85-getter|0|85-preInit
    /**
     * Returns an initialized instance of simpleCancellableTask component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getSimpleCancellableTask() {
        if (simpleCancellableTask == null) {
//GEN-END:|85-getter|0|85-preInit
 // write pre-init user code here
simpleCancellableTask = new SimpleCancellableTask();//GEN-BEGIN:|85-getter|1|85-execute
            simpleCancellableTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|85-getter|1|85-execute
// write task-execution user code here
                    searchForBluetooth();
                }//GEN-BEGIN:|85-getter|2|85-postInit
            });//GEN-END:|85-getter|2|85-postInit
 // write post-init user code here
}//GEN-BEGIN:|85-getter|3|
        return simpleCancellableTask;
    }
//</editor-fold>//GEN-END:|85-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|88-getter|0|88-preInit
    /**
     * Returns an initialized instance of screenCommand component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {
//GEN-END:|88-getter|0|88-preInit
 // write pre-init user code here
screenCommand = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|88-getter|1|88-postInit
 // write post-init user code here
}//GEN-BEGIN:|88-getter|2|
        return screenCommand;
    }
//</editor-fold>//GEN-END:|88-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textBox ">//GEN-BEGIN:|86-getter|0|86-preInit
    /**
     * Returns an initialized instance of textBox component.
     *
     * @return the initialized component instance
     */
    public TextBox getTextBox() {
        if (textBox == null) {
//GEN-END:|86-getter|0|86-preInit
 // write pre-init user code here
textBox = new TextBox("Text Box", "Vita Vas aplikace RepRap", 100, TextField.ANY);//GEN-BEGIN:|86-getter|1|86-postInit
            textBox.addCommand(getOkCommand());
            textBox.addCommand(getExitCommand1());
            textBox.addCommand(getItemCommand());
            textBox.setCommandListener(this);//GEN-END:|86-getter|1|86-postInit
 // write post-init user code here
}//GEN-BEGIN:|86-getter|2|
        return textBox;
    }
//</editor-fold>//GEN-END:|86-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|91-getter|0|91-preInit
    /**
     * Returns an initialized instance of okCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {
//GEN-END:|91-getter|0|91-preInit
 // write pre-init user code here
okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|91-getter|1|91-postInit
 // write post-init user code here
}//GEN-BEGIN:|91-getter|2|
        return okCommand;
    }
//</editor-fold>//GEN-END:|91-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|94-getter|0|94-preInit
    /**
     * Returns an initialized instance of exitCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {
//GEN-END:|94-getter|0|94-preInit
 // write pre-init user code here
exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|94-getter|1|94-postInit
 // write post-init user code here
}//GEN-BEGIN:|94-getter|2|
        return exitCommand1;
    }
//</editor-fold>//GEN-END:|94-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: itemCommand ">//GEN-BEGIN:|97-getter|0|97-preInit
    /**
     * Returns an initialized instance of itemCommand component.
     *
     * @return the initialized component instance
     */
    public Command getItemCommand() {
        if (itemCommand == null) {
//GEN-END:|97-getter|0|97-preInit
 // write pre-init user code here
itemCommand = new Command("Item", Command.ITEM, 0);//GEN-LINE:|97-getter|1|97-postInit
 // write post-init user code here
}//GEN-BEGIN:|97-getter|2|
        return itemCommand;
    }
//</editor-fold>//GEN-END:|97-getter|2|

    /**
     * Returns a display instance.
     *
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
     * Called when MIDlet is started. Checks whether the MIDlet have been
     * already started and initialize/starts or resumes the MIDlet.
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
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     *
     * @param unconditional if true, then the MIDlet has to be unconditionally
     * terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }
    
    private void searchForBluetooth() throws IOException {
        List l = getList();
        l.deleteAll();
        BluetoothDeviceDiscovery.main();
        bluetoothDevices = BluetoothDeviceDiscovery.vecDevices;
        if (bluetoothDevices != null && bluetoothDevices.size() > 0) {
           for(int i=0; i<bluetoothDevices.size(); i++ ) {
               List.append(((RemoteDevice) bluetoothDevices.elementAt(i)).getBluetoothAddress(), null);
           }
       } else {
           List.append("No Bluetooth found", null);
       }
    }
}
