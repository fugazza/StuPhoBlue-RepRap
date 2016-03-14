/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuphobluereprap;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.custom.ConsoleScreen;
import org.netbeans.microedition.lcdui.custom.RepRapScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author ajgl
 */
public final class StuPhoBlueRepRapMIDlet extends MIDlet implements CommandListener, PropertyChangeListener {
    
    private boolean midletPaused = false;
    
    private final BTSerialPort bluetoothSerialPort = new BTSerialPort();
    private Displayable lastScreen;
    private final RepRapClientEngine repRapEngine = new RepRapClientEngine();
;

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private Command continueCommand;
    private Command exitCommand;
    private Command newSearchCommand;
    private Command connectCommand;
    private Command screenCommand1;
    private Command chooseAnotherCommand;
    private Command consoleScreenCommand;
    private Command printerScreenCommand;
    private Command composeScreenCommand;
    private Command okCommand;
    private Command cancelCommand;
    private Command continueCommand1;
    private Command backCommand;
    private Command printCommand;
    private Command backCommand1;
    private Alert alert2;
    private List deviceList;
    private WaitScreen DeviceDiscoveryWaitScreen;
    private Alert DiscoveryFailedAlert;
    private WaitScreen connectWaitScreen;
    private Alert connectionFailedAlert;
    private ConsoleScreen consoleScreen;
    private RepRapScreen repRapScreen;
    private TextBox composeTextBox;
    private Alert sendFailedAlert;
    private List filesOnSDcardList;
    private Image image1;
    private Image image2;
    private SimpleCancellableTask deviceDiscoveryTask;
    private SimpleCancellableTask bluetoothConnectionTask;
//</editor-fold>//GEN-END:|fields|0|

    /**
     * The HelloMIDlet constructor.
     */
    public StuPhoBlueRepRapMIDlet() {
    }

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    /**
     * Switches a display to previous displayable of the current displayable.
     * The <code>display</code> instance is obtain from the
     * <code>getDisplay</code> method.
     */
    private void switchToPreviousDisplayable() {
        Displayable __currentDisplayable = getDisplay().getCurrent();
        if (__currentDisplayable != null) {
            Displayable __nextDisplayable = (Displayable) __previousDisplayables.get(__currentDisplayable);
            if (__nextDisplayable != null) {
                switchDisplayable(null, __nextDisplayable);
            }
        }
    }
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
        bluetoothSerialPort.addPropertyListener(this);
        repRapEngine.setConsoleScreen(getConsoleScreen());
        repRapEngine.setFilesOnSDcardList(getFilesOnSDcardList());
        repRapEngine.setParent(this);
        repRapEngine.setSerialPort(bluetoothSerialPort);
        repRapEngine.setRepRapScreen(getRepRapScreen());
        repRapScreen.addRepRapListener(repRapEngine);
    }//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {
//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
switchDisplayable(null, getDeviceDiscoveryWaitScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
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
        Displayable __currentDisplayable = display.getCurrent();
        if (__currentDisplayable != null && nextDisplayable != null) {
            __previousDisplayables.put(nextDisplayable, __currentDisplayable);
        }
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
        getDeviceList();
        if (displayable == DeviceDiscoveryWaitScreen) {//GEN-BEGIN:|7-commandAction|1|74-preAction
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|1|74-preAction
        // write pre-action user code here
switchDisplayable(getDiscoveryFailedAlert(), getDeviceDiscoveryWaitScreen());//GEN-LINE:|7-commandAction|2|74-postAction
 // write post-action user code here
} else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|3|73-preAction
 // write pre-action user code here
switchDisplayable(null, getDeviceList());//GEN-LINE:|7-commandAction|4|73-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|5|197-preAction
        } else if (displayable == DiscoveryFailedAlert) {
            if (command == chooseAnotherCommand) {//GEN-END:|7-commandAction|5|197-preAction
                // write pre-action user code here
switchDisplayable(null, getDeviceDiscoveryWaitScreen());//GEN-LINE:|7-commandAction|6|197-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|7|193-preAction
        } else if (displayable == alert2) {
            if (command == chooseAnotherCommand) {//GEN-END:|7-commandAction|7|193-preAction
                // write pre-action user code here
switchDisplayable(null, getDeviceDiscoveryWaitScreen());//GEN-LINE:|7-commandAction|8|193-postAction
                // write post-action user code here
} else if (command == continueCommand) {//GEN-LINE:|7-commandAction|9|159-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|10|159-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|11|210-preAction
        } else if (displayable == composeTextBox) {
            if (command == cancelCommand) {//GEN-END:|7-commandAction|11|210-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|12|210-postAction
                // write post-action user code here
} else if (command == okCommand) {//GEN-LINE:|7-commandAction|13|208-preAction
                // write pre-action user code here
sendMethod();//GEN-LINE:|7-commandAction|14|208-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|15|118-preAction
        } else if (displayable == connectWaitScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|15|118-preAction
                // write pre-action user code here
switchDisplayable(getConnectionFailedAlert(), getConnectWaitScreen());//GEN-LINE:|7-commandAction|16|118-postAction
 // write post-action user code here
} else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|17|117-preAction
 // write pre-action user code here
startBluetoothSerialMethod();//GEN-LINE:|7-commandAction|18|117-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|19|125-preAction
        } else if (displayable == connectionFailedAlert) {
            if (command == chooseAnotherCommand) {//GEN-END:|7-commandAction|19|125-preAction
                // write pre-action user code here
switchDisplayable(null, getDeviceDiscoveryWaitScreen());//GEN-LINE:|7-commandAction|20|125-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|21|205-preAction
        } else if (displayable == consoleScreen) {
            if (command == composeScreenCommand) {//GEN-END:|7-commandAction|21|205-preAction
                // write pre-action user code here
switchDisplayable(null, getComposeTextBox());//GEN-LINE:|7-commandAction|22|205-postAction
                // write post-action user code here
} else if (command == exitCommand) {//GEN-LINE:|7-commandAction|23|201-preAction
                // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|24|201-postAction
                // write post-action user code here
} else if (command == printerScreenCommand) {//GEN-LINE:|7-commandAction|25|191-preAction
                // write pre-action user code here
switchDisplayable(null, getRepRapScreen());//GEN-LINE:|7-commandAction|26|191-postAction
                // write post-action user code here
                lastScreen = repRapScreen;
            }//GEN-BEGIN:|7-commandAction|27|58-preAction
        } else if (displayable == deviceList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|27|58-preAction
                // write pre-action user code here
deviceListAction();//GEN-LINE:|7-commandAction|28|58-postAction
                // write post-action user code here
} else if (command == chooseAnotherCommand) {//GEN-LINE:|7-commandAction|29|195-preAction
                // write pre-action user code here
switchDisplayable(null, getDeviceDiscoveryWaitScreen());//GEN-LINE:|7-commandAction|30|195-postAction
                // write post-action user code here
} else if (command == connectCommand) {//GEN-LINE:|7-commandAction|31|110-preAction
                // write pre-action user code here
switchDisplayable(null, getConnectWaitScreen());//GEN-LINE:|7-commandAction|32|110-postAction
                // write post-action user code here
} else if (command == exitCommand) {//GEN-LINE:|7-commandAction|33|62-preAction
                // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|34|62-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|35|232-preAction
        } else if (displayable == filesOnSDcardList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|35|232-preAction
                // write pre-action user code here
filesOnSDcardListAction();//GEN-LINE:|7-commandAction|36|232-postAction
                // write post-action user code here
} else if (command == backCommand) {//GEN-LINE:|7-commandAction|37|238-preAction
                // write pre-action user code here
switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|38|238-postAction
                // write post-action user code here
} else if (command == printCommand) {//GEN-LINE:|7-commandAction|39|235-preAction
                // write pre-action user code here
printFileMethod();//GEN-LINE:|7-commandAction|40|235-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|41|211-preAction
        } else if (displayable == repRapScreen) {
            if (command == composeScreenCommand) {//GEN-END:|7-commandAction|41|211-preAction
 // write pre-action user code here
switchDisplayable(null, getComposeTextBox());//GEN-LINE:|7-commandAction|42|211-postAction
 // write post-action user code here
} else if (command == consoleScreenCommand) {//GEN-LINE:|7-commandAction|43|188-preAction
 // write pre-action user code here
switchDisplayable(null, getConsoleScreen());//GEN-LINE:|7-commandAction|44|188-postAction
 // write post-action user code here
 lastScreen = consoleScreen;
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|45|199-preAction
 // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|46|199-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|47|228-preAction
        } else if (displayable == sendFailedAlert) {
            if (command == backCommand) {//GEN-END:|7-commandAction|47|228-preAction
                // write pre-action user code here
switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|48|228-postAction
                // write post-action user code here
} else if (command == continueCommand1) {//GEN-LINE:|7-commandAction|49|226-preAction
                // write pre-action user code here
returnToScreenMethod();//GEN-LINE:|7-commandAction|50|226-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|51|7-postCommandAction
        }//GEN-END:|7-commandAction|51|7-postCommandAction
        // write post-action user code here
}//GEN-BEGIN:|7-commandAction|52|
//</editor-fold>//GEN-END:|7-commandAction|52|







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
exitCommand = new Command("Exit", Command.EXIT, 2);//GEN-LINE:|61-getter|1|61-postInit
 // write post-init user code here
}//GEN-BEGIN:|61-getter|2|
        return exitCommand;
    }
//</editor-fold>//GEN-END:|61-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: deviceList ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initialized instance of deviceList component.
     *
     * @return the initialized component instance
     */
    public List getDeviceList() {
        if (deviceList == null) {
//GEN-END:|57-getter|0|57-preInit
 // write pre-init user code here
deviceList = new List("Bluetooth devices", Choice.IMPLICIT);//GEN-BEGIN:|57-getter|1|57-postInit
            deviceList.addCommand(getConnectCommand());
            deviceList.addCommand(getExitCommand());
            deviceList.addCommand(getChooseAnotherCommand());
            deviceList.setCommandListener(this);
            deviceList.setSelectCommand(null);
            deviceList.setSelectedFlags(new boolean[]{});//GEN-END:|57-getter|1|57-postInit
 // write post-init user code here
}//GEN-BEGIN:|57-getter|2|
        return deviceList;
    }
//</editor-fold>//GEN-END:|57-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: deviceListAction ">//GEN-BEGIN:|57-action|0|57-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * deviceList component.
     */
    public void deviceListAction() {
//GEN-END:|57-action|0|57-preAction
 // enter pre-action user code here
String __selectedString = getDeviceList().getString(getDeviceList().getSelectedIndex());//GEN-LINE:|57-action|1|57-postAction
 // enter post-action user code here
}//GEN-BEGIN:|57-action|2|
//</editor-fold>//GEN-END:|57-action|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: newSearchCommand ">//GEN-BEGIN:|79-getter|0|79-preInit
    /**
     * Returns an initialized instance of newSearchCommand component.
     *
     * @return the initialized component instance
     */
    public Command getNewSearchCommand() {
        if (newSearchCommand == null) {
//GEN-END:|79-getter|0|79-preInit
 // write pre-init user code here
newSearchCommand = new Command("New search", Command.SCREEN, 1);//GEN-LINE:|79-getter|1|79-postInit
 // write post-init user code here
}//GEN-BEGIN:|79-getter|2|
        return newSearchCommand;
    }
//</editor-fold>//GEN-END:|79-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: DeviceDiscoveryWaitScreen ">//GEN-BEGIN:|70-getter|0|70-preInit
    /**
     * Returns an initialized instance of DeviceDiscoveryWaitScreen component.
     *
     * @return the initialized component instance
     */
    public WaitScreen getDeviceDiscoveryWaitScreen() {
        if (DeviceDiscoveryWaitScreen == null) {
//GEN-END:|70-getter|0|70-preInit
 // write pre-init user code here
DeviceDiscoveryWaitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|70-getter|1|70-postInit
            DeviceDiscoveryWaitScreen.setTitle("Bluetooth search");
            DeviceDiscoveryWaitScreen.setCommandListener(this);
            DeviceDiscoveryWaitScreen.setImage(getImage2());
            DeviceDiscoveryWaitScreen.setText("Searching for bluetooth devices");
            DeviceDiscoveryWaitScreen.setTask(getDeviceDiscoveryTask());//GEN-END:|70-getter|1|70-postInit
 // write post-init user code here
}//GEN-BEGIN:|70-getter|2|
        return DeviceDiscoveryWaitScreen;
    }
//</editor-fold>//GEN-END:|70-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: DiscoveryFailedAlert ">//GEN-BEGIN:|76-getter|0|76-preInit
    /**
     * Returns an initialized instance of DiscoveryFailedAlert component.
     *
     * @return the initialized component instance
     */
    public Alert getDiscoveryFailedAlert() {
        if (DiscoveryFailedAlert == null) {
//GEN-END:|76-getter|0|76-preInit
 // write pre-init user code here
DiscoveryFailedAlert = new Alert("No device found", "No bluetooth device was found", getImage1(), AlertType.ERROR);//GEN-BEGIN:|76-getter|1|76-postInit
            DiscoveryFailedAlert.addCommand(getChooseAnotherCommand());
            DiscoveryFailedAlert.setCommandListener(this);
            DiscoveryFailedAlert.setTimeout(Alert.FOREVER);//GEN-END:|76-getter|1|76-postInit
 // write post-init user code here
            DiscoveryFailedAlert.addCommand(getExitCommand());
        }//GEN-BEGIN:|76-getter|2|
        return DiscoveryFailedAlert;
    }
//</editor-fold>//GEN-END:|76-getter|2|



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
            }//GEN-LINE:|77-getter|2|77-postInit
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
            }//GEN-LINE:|78-getter|2|78-postInit
 // write post-init user code here
}//GEN-BEGIN:|78-getter|3|
        return image2;
    }
//</editor-fold>//GEN-END:|78-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: deviceDiscoveryTask ">//GEN-BEGIN:|85-getter|0|85-preInit
    /**
     * Returns an initialized instance of deviceDiscoveryTask component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getDeviceDiscoveryTask() {
        if (deviceDiscoveryTask == null) {
//GEN-END:|85-getter|0|85-preInit
 // write pre-init user code here
deviceDiscoveryTask = new SimpleCancellableTask();//GEN-BEGIN:|85-getter|1|85-execute
            deviceDiscoveryTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|85-getter|1|85-execute
// write task-execution user code here
                    searchForBluetooth();
                }//GEN-BEGIN:|85-getter|2|85-postInit
            });//GEN-END:|85-getter|2|85-postInit
 // write post-init user code here
}//GEN-BEGIN:|85-getter|3|
        return deviceDiscoveryTask;
    }
//</editor-fold>//GEN-END:|85-getter|3|













//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand1 ">//GEN-BEGIN:|106-getter|0|106-preInit
    /**
     * Returns an initialized instance of screenCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand1() {
        if (screenCommand1 == null) {
//GEN-END:|106-getter|0|106-preInit
 // write pre-init user code here
screenCommand1 = new Command("Try again", Command.SCREEN, 0);//GEN-LINE:|106-getter|1|106-postInit
 // write post-init user code here
}//GEN-BEGIN:|106-getter|2|
        return screenCommand1;
    }
//</editor-fold>//GEN-END:|106-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectCommand ">//GEN-BEGIN:|109-getter|0|109-preInit
    /**
     * Returns an initialized instance of connectCommand component.
     *
     * @return the initialized component instance
     */
    public Command getConnectCommand() {
        if (connectCommand == null) {
//GEN-END:|109-getter|0|109-preInit
 // write pre-init user code here
connectCommand = new Command("Connect", Command.SCREEN, 0);//GEN-LINE:|109-getter|1|109-postInit
 // write post-init user code here
}//GEN-BEGIN:|109-getter|2|
        return connectCommand;
    }
//</editor-fold>//GEN-END:|109-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectWaitScreen ">//GEN-BEGIN:|116-getter|0|116-preInit
    /**
     * Returns an initialized instance of connectWaitScreen component.
     *
     * @return the initialized component instance
     */
    public WaitScreen getConnectWaitScreen() {
        if (connectWaitScreen == null) {
//GEN-END:|116-getter|0|116-preInit
 // write pre-init user code here
connectWaitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|116-getter|1|116-postInit
            connectWaitScreen.setTitle("Connecting...");
            connectWaitScreen.setCommandListener(this);
            connectWaitScreen.setImage(getImage1());
            connectWaitScreen.setText("Connecting to selected bluetooth device");
            connectWaitScreen.setTask(getBluetoothConnectionTask());//GEN-END:|116-getter|1|116-postInit
 // write post-init user code here
}//GEN-BEGIN:|116-getter|2|
        return connectWaitScreen;
    }
//</editor-fold>//GEN-END:|116-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: bluetoothConnectionTask ">//GEN-BEGIN:|121-getter|0|121-preInit
    /**
     * Returns an initialized instance of bluetoothConnectionTask component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getBluetoothConnectionTask() {
        if (bluetoothConnectionTask == null) {
//GEN-END:|121-getter|0|121-preInit
 // write pre-init user code here
bluetoothConnectionTask = new SimpleCancellableTask();//GEN-BEGIN:|121-getter|1|121-execute
            bluetoothConnectionTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|121-getter|1|121-execute
// write task-execution user code here
                connectToSelectedBluetoothDevice();
                }//GEN-BEGIN:|121-getter|2|121-postInit
            });//GEN-END:|121-getter|2|121-postInit
 // write post-init user code here
}//GEN-BEGIN:|121-getter|3|
        return bluetoothConnectionTask;
    }
//</editor-fold>//GEN-END:|121-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: chooseAnotherCommand ">//GEN-BEGIN:|124-getter|0|124-preInit
    /**
     * Returns an initialized instance of chooseAnotherCommand component.
     *
     * @return the initialized component instance
     */
    public Command getChooseAnotherCommand() {
        if (chooseAnotherCommand == null) {
//GEN-END:|124-getter|0|124-preInit
 // write pre-init user code here
chooseAnotherCommand = new Command("Choose another bluetooth", Command.SCREEN, 0);//GEN-LINE:|124-getter|1|124-postInit
 // write post-init user code here
}//GEN-BEGIN:|124-getter|2|
        return chooseAnotherCommand;
    }
//</editor-fold>//GEN-END:|124-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectionFailedAlert ">//GEN-BEGIN:|122-getter|0|122-preInit
    /**
     * Returns an initialized instance of connectionFailedAlert component.
     *
     * @return the initialized component instance
     */
    public Alert getConnectionFailedAlert() {
        if (connectionFailedAlert == null) {
//GEN-END:|122-getter|0|122-preInit
 // write pre-init user code here
connectionFailedAlert = new Alert("Connection error", "Connection to selected bluetooth device failed", null, AlertType.ERROR);//GEN-BEGIN:|122-getter|1|122-postInit
            connectionFailedAlert.addCommand(getChooseAnotherCommand());
            connectionFailedAlert.setCommandListener(this);
            connectionFailedAlert.setTimeout(Alert.FOREVER);//GEN-END:|122-getter|1|122-postInit
 // write post-init user code here
            connectionFailedAlert.addCommand(getExitCommand());
        }//GEN-BEGIN:|122-getter|2|
        return connectionFailedAlert;
    }
//</editor-fold>//GEN-END:|122-getter|2|







//<editor-fold defaultstate="collapsed" desc=" Generated Method: testPrinter ">//GEN-BEGIN:|148-if|0|148-preIf
    /**
     * Performs an action assigned to the testPrinter if-point.
     */
    public void testPrinter() {
//GEN-END:|148-if|0|148-preIf
 // enter pre-if user code here
 boolean printerAnswersCorrectly = true;
        if (printerAnswersCorrectly) {//GEN-LINE:|148-if|1|149-preAction
 // write pre-action user code here
switchDisplayable(null, getRepRapScreen());//GEN-LINE:|148-if|2|149-postAction
 // write post-action user code here
 lastScreen = repRapScreen;
        } else {//GEN-LINE:|148-if|3|150-preAction
 // write pre-action user code here
switchDisplayable(null, getAlert2());//GEN-LINE:|148-if|4|150-postAction
 // write post-action user code here
}//GEN-LINE:|148-if|5|148-postIf
 // enter post-if user code here
}//GEN-BEGIN:|148-if|6|
//</editor-fold>//GEN-END:|148-if|6|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: continueCommand ">//GEN-BEGIN:|158-getter|0|158-preInit
    /**
     * Returns an initialized instance of continueCommand component.
     *
     * @return the initialized component instance
     */
    public Command getContinueCommand() {
        if (continueCommand == null) {
//GEN-END:|158-getter|0|158-preInit
 // write pre-init user code here
continueCommand = new Command("Continue anyway", Command.OK, 0);//GEN-LINE:|158-getter|1|158-postInit
 // write post-init user code here
}//GEN-BEGIN:|158-getter|2|
        return continueCommand;
    }
//</editor-fold>//GEN-END:|158-getter|2|







//<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert2 ">//GEN-BEGIN:|156-getter|0|156-preInit
    /**
     * Returns an initialized instance of alert2 component.
     *
     * @return the initialized component instance
     */
    public Alert getAlert2() {
        if (alert2 == null) {
//GEN-END:|156-getter|0|156-preInit
 // write pre-init user code here
alert2 = new Alert("Tiskarna nedpovida", "Tiskarna se nehlasi korektne", null, null);//GEN-BEGIN:|156-getter|1|156-postInit
            alert2.addCommand(getContinueCommand());
            alert2.addCommand(getChooseAnotherCommand());
            alert2.setCommandListener(this);
            alert2.setTimeout(Alert.FOREVER);//GEN-END:|156-getter|1|156-postInit
 // write post-init user code here
            alert2.addCommand(getExitCommand());
        }//GEN-BEGIN:|156-getter|2|
        return alert2;
    }
//</editor-fold>//GEN-END:|156-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startBluetoothSerialMethod ">//GEN-BEGIN:|179-entry|0|180-preAction
    /**
     * Performs an action assigned to the startBluetoothSerialMethod
     * entry-point.
     */
    public void startBluetoothSerialMethod() {
//GEN-END:|179-entry|0|180-preAction
 // write pre-action user code here
 // start to listen for incoming conneciton
 new Thread(bluetoothSerialPort).start();
 System.out.println("bluetooth serial port started");
        testPrinter();//GEN-LINE:|179-entry|1|180-postAction
 // write post-action user code here
}//GEN-BEGIN:|179-entry|2|
//</editor-fold>//GEN-END:|179-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: consoleScreen ">//GEN-BEGIN:|184-getter|0|184-preInit
    /**
     * Returns an initialized instance of consoleScreen component.
     *
     * @return the initialized component instance
     */
    public ConsoleScreen getConsoleScreen() {
        if (consoleScreen == null) {
//GEN-END:|184-getter|0|184-preInit
 // write pre-init user code here
            consoleScreen = new ConsoleScreen(getDisplay());
            consoleScreen.addCommand(getPrinterScreenCommand());
            consoleScreen.addCommand(getExitCommand());
            consoleScreen.addCommand(getComposeScreenCommand());
            consoleScreen.setCommandListener(this);
//GEN-LINE:|184-getter|1|184-postInit
 // write post-init user code here
}//GEN-BEGIN:|184-getter|2|
        return consoleScreen;
    }
//</editor-fold>//GEN-END:|184-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: repRapScreen ">//GEN-BEGIN:|185-getter|0|185-preInit
    /**
     * Returns an initialized instance of repRapScreen component.
     *
     * @return the initialized component instance
     */
    public RepRapScreen getRepRapScreen() {
        if (repRapScreen == null) {
//GEN-END:|185-getter|0|185-preInit
 // write pre-init user code here
             repRapScreen = new RepRapScreen(getDisplay());
            repRapScreen.addCommand(getConsoleScreenCommand());
            repRapScreen.addCommand(getExitCommand());
            repRapScreen.addCommand(getComposeScreenCommand());
            repRapScreen.setCommandListener(this);
//GEN-LINE:|185-getter|1|185-postInit
 // write post-init user code here
}//GEN-BEGIN:|185-getter|2|
        return repRapScreen;
    }
//</editor-fold>//GEN-END:|185-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: consoleScreenCommand ">//GEN-BEGIN:|187-getter|0|187-preInit
    /**
     * Returns an initialized instance of consoleScreenCommand component.
     *
     * @return the initialized component instance
     */
    public Command getConsoleScreenCommand() {
        if (consoleScreenCommand == null) {
//GEN-END:|187-getter|0|187-preInit
 // write pre-init user code here
consoleScreenCommand = new Command("Console", Command.SCREEN, 0);//GEN-LINE:|187-getter|1|187-postInit
 // write post-init user code here
}//GEN-BEGIN:|187-getter|2|
        return consoleScreenCommand;
    }
//</editor-fold>//GEN-END:|187-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: printerScreenCommand ">//GEN-BEGIN:|190-getter|0|190-preInit
    /**
     * Returns an initialized instance of printerScreenCommand component.
     *
     * @return the initialized component instance
     */
    public Command getPrinterScreenCommand() {
        if (printerScreenCommand == null) {
//GEN-END:|190-getter|0|190-preInit
 // write pre-init user code here
printerScreenCommand = new Command("Printer", Command.SCREEN, 0);//GEN-LINE:|190-getter|1|190-postInit
 // write post-init user code here
}//GEN-BEGIN:|190-getter|2|
        return printerScreenCommand;
    }
//</editor-fold>//GEN-END:|190-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: returnToScreenMethod ">//GEN-BEGIN:|213-if|0|213-preIf
    /**
     * Performs an action assigned to the returnToScreenMethod if-point.
     */
    public void returnToScreenMethod() {
//GEN-END:|213-if|0|213-preIf
 // enter pre-if user code here
if (lastScreen == repRapScreen) {//GEN-LINE:|213-if|1|214-preAction
 // write pre-action user code here
switchDisplayable(null, getRepRapScreen());//GEN-LINE:|213-if|2|214-postAction
 // write post-action user code here
} else {//GEN-LINE:|213-if|3|215-preAction
 // write pre-action user code here
switchDisplayable(null, getConsoleScreen());//GEN-LINE:|213-if|4|215-postAction
 // write post-action user code here
}//GEN-LINE:|213-if|5|213-postIf
 // enter post-if user code here
}//GEN-BEGIN:|213-if|6|
//</editor-fold>//GEN-END:|213-if|6|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: composeScreenCommand ">//GEN-BEGIN:|204-getter|0|204-preInit
    /**
     * Returns an initialized instance of composeScreenCommand component.
     *
     * @return the initialized component instance
     */
    public Command getComposeScreenCommand() {
        if (composeScreenCommand == null) {
//GEN-END:|204-getter|0|204-preInit
 // write pre-init user code here
composeScreenCommand = new Command("Compose", Command.SCREEN, 0);//GEN-LINE:|204-getter|1|204-postInit
 // write post-init user code here
}//GEN-BEGIN:|204-getter|2|
        return composeScreenCommand;
    }
//</editor-fold>//GEN-END:|204-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|207-getter|0|207-preInit
    /**
     * Returns an initialized instance of okCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {
//GEN-END:|207-getter|0|207-preInit
 // write pre-init user code here
okCommand = new Command("Send", Command.OK, 0);//GEN-LINE:|207-getter|1|207-postInit
 // write post-init user code here
}//GEN-BEGIN:|207-getter|2|
        return okCommand;
    }
//</editor-fold>//GEN-END:|207-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|209-getter|0|209-preInit
    /**
     * Returns an initialized instance of cancelCommand component.
     *
     * @return the initialized component instance
     */
    public Command getCancelCommand() {
        if (cancelCommand == null) {
//GEN-END:|209-getter|0|209-preInit
 // write pre-init user code here
cancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|209-getter|1|209-postInit
 // write post-init user code here
}//GEN-BEGIN:|209-getter|2|
        return cancelCommand;
    }
//</editor-fold>//GEN-END:|209-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: composeTextBox ">//GEN-BEGIN:|203-getter|0|203-preInit
    /**
     * Returns an initialized instance of composeTextBox component.
     *
     * @return the initialized component instance
     */
    public TextBox getComposeTextBox() {
        if (composeTextBox == null) {
//GEN-END:|203-getter|0|203-preInit
 // write pre-init user code here
composeTextBox = new TextBox("textBox", null, 100, TextField.ANY);//GEN-BEGIN:|203-getter|1|203-postInit
            composeTextBox.addCommand(getOkCommand());
            composeTextBox.addCommand(getCancelCommand());
            composeTextBox.setCommandListener(this);//GEN-END:|203-getter|1|203-postInit
 // write post-init user code here
}//GEN-BEGIN:|203-getter|2|
        return composeTextBox;
    }
//</editor-fold>//GEN-END:|203-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: sendMethod ">//GEN-BEGIN:|218-if|0|218-preIf
    /**
     * Performs an action assigned to the sendMethod if-point.
     */
    public void sendMethod() {
//GEN-END:|218-if|0|218-preIf
 // enter pre-if user code here
     boolean sendSuccessfull = true;
    String textToSend = composeTextBox.getString() + "\r\n";
    try {
    bluetoothSerialPort.write(textToSend);
    consoleScreen.write(textToSend);
    } catch (IOException ex) {
        sendSuccessfull = false;
    }
        if (sendSuccessfull) {//GEN-LINE:|218-if|1|219-preAction
 // write pre-action user code here
returnToScreenMethod();//GEN-LINE:|218-if|2|219-postAction
 // write post-action user code here
} else {//GEN-LINE:|218-if|3|220-preAction
 // write pre-action user code here
switchDisplayable(null, getSendFailedAlert());//GEN-LINE:|218-if|4|220-postAction
 // write post-action user code here
}//GEN-LINE:|218-if|5|218-postIf
 // enter post-if user code here
}//GEN-BEGIN:|218-if|6|
//</editor-fold>//GEN-END:|218-if|6|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: continueCommand1 ">//GEN-BEGIN:|225-getter|0|225-preInit
    /**
     * Returns an initialized instance of continueCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getContinueCommand1() {
        if (continueCommand1 == null) {
//GEN-END:|225-getter|0|225-preInit
 // write pre-init user code here
continueCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|225-getter|1|225-postInit
 // write post-init user code here
}//GEN-BEGIN:|225-getter|2|
        return continueCommand1;
    }
//</editor-fold>//GEN-END:|225-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|227-getter|0|227-preInit
    /**
     * Returns an initialized instance of backCommand component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {
//GEN-END:|227-getter|0|227-preInit
 // write pre-init user code here
backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|227-getter|1|227-postInit
 // write post-init user code here
}//GEN-BEGIN:|227-getter|2|
        return backCommand;
    }
//</editor-fold>//GEN-END:|227-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendFailedAlert ">//GEN-BEGIN:|222-getter|0|222-preInit
    /**
     * Returns an initialized instance of sendFailedAlert component.
     *
     * @return the initialized component instance
     */
    public Alert getSendFailedAlert() {
        if (sendFailedAlert == null) {
//GEN-END:|222-getter|0|222-preInit
 // write pre-init user code here
sendFailedAlert = new Alert("alert");//GEN-BEGIN:|222-getter|1|222-postInit
            sendFailedAlert.addCommand(getContinueCommand1());
            sendFailedAlert.addCommand(getBackCommand());
            sendFailedAlert.setCommandListener(this);
            sendFailedAlert.setTimeout(Alert.FOREVER);//GEN-END:|222-getter|1|222-postInit
 // write post-init user code here
}//GEN-BEGIN:|222-getter|2|
        return sendFailedAlert;
    }
//</editor-fold>//GEN-END:|222-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: printFileMethod ">//GEN-BEGIN:|240-entry|0|241-preAction
    /**
     * Performs an action assigned to the printFileMethod entry-point.
     */
    public void printFileMethod() {
//GEN-END:|240-entry|0|241-preAction
 // write pre-action user code here
    String textToSend = "M23 " + filesOnSDcardList.getString(filesOnSDcardList.getSelectedIndex()) + "\r\n"
            + "M24" + "\r\n";
    try {
        bluetoothSerialPort.write(textToSend);
        consoleScreen.write(textToSend);
    } catch (IOException ex) {
        ex.printStackTrace();
    }
 
        switchDisplayable(null, getRepRapScreen());//GEN-LINE:|240-entry|1|241-postAction
 // write post-action user code here
}//GEN-BEGIN:|240-entry|2|
//</editor-fold>//GEN-END:|240-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: printCommand ">//GEN-BEGIN:|234-getter|0|234-preInit
    /**
     * Returns an initialized instance of printCommand component.
     *
     * @return the initialized component instance
     */
    public Command getPrintCommand() {
        if (printCommand == null) {
//GEN-END:|234-getter|0|234-preInit
 // write pre-init user code here
printCommand = new Command("Print", Command.OK, 0);//GEN-LINE:|234-getter|1|234-postInit
 // write post-init user code here
}//GEN-BEGIN:|234-getter|2|
        return printCommand;
    }
//</editor-fold>//GEN-END:|234-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|236-getter|0|236-preInit
    /**
     * Returns an initialized instance of backCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {
//GEN-END:|236-getter|0|236-preInit
 // write pre-init user code here
backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|236-getter|1|236-postInit
 // write post-init user code here
}//GEN-BEGIN:|236-getter|2|
        return backCommand1;
    }
//</editor-fold>//GEN-END:|236-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: filesOnSDcardList ">//GEN-BEGIN:|231-getter|0|231-preInit
    /**
     * Returns an initialized instance of filesOnSDcardList component.
     *
     * @return the initialized component instance
     */
    public List getFilesOnSDcardList() {
        if (filesOnSDcardList == null) {
//GEN-END:|231-getter|0|231-preInit
 // write pre-init user code here
filesOnSDcardList = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|231-getter|1|231-postInit
            filesOnSDcardList.addCommand(getPrintCommand());
            filesOnSDcardList.addCommand(getBackCommand());
            filesOnSDcardList.setCommandListener(this);//GEN-END:|231-getter|1|231-postInit
 // write post-init user code here
}//GEN-BEGIN:|231-getter|2|
        return filesOnSDcardList;
    }
//</editor-fold>//GEN-END:|231-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: filesOnSDcardListAction ">//GEN-BEGIN:|231-action|0|231-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * filesOnSDcardList component.
     */
    public void filesOnSDcardListAction() {
//GEN-END:|231-action|0|231-preAction
 // enter pre-action user code here
String __selectedString = getFilesOnSDcardList().getString(getFilesOnSDcardList().getSelectedIndex());//GEN-LINE:|231-action|1|231-postAction
 // enter post-action user code here
}//GEN-BEGIN:|231-action|2|
//</editor-fold>//GEN-END:|231-action|2|









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
    
    private void searchForBluetooth() throws NoBluetoothFoundException, IOException {
        Vector devices = BTSerialPort.discoverDevices();
        
        if (devices != null && devices.size() > 0) {
            int deviceCount = devices.size();
            System.out.println("Count of bluetooth devices found: " + deviceCount);
            List devList = getDeviceList();
            devList.deleteAll();
            for (int i = 0; i < deviceCount; i++) {
                try {
                RemoteDevice remoteDevice = (RemoteDevice) devices.elementAt(i);
                devList.append(remoteDevice.getBluetoothAddress(), null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            throw new NoBluetoothFoundException("No bluetooth device found");
        }
    }
    
    private void connectToSelectedBluetoothDevice() throws IOException {        
        String MAC = deviceList.getString(deviceList.getSelectedIndex());
        bluetoothSerialPort.Connect(MAC);
    }

    public void propertyChange(String propertyName, int oldValue, int newValue) {
        //System.out.println(propertyName);
        if (propertyName.compareTo("character read") == 0) {
            //System.out.println("char received: " + (char) newValue);
            ConsoleScreen c = getConsoleScreen();
            c.writeChar((char) newValue);
        } else if (propertyName.compareTo("disconnected") == 0) {
            //disconnectMethod();
        }
    }
}
