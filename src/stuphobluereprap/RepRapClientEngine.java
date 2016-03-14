/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuphobluereprap;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import org.netbeans.microedition.lcdui.custom.ConsoleScreen;
import org.netbeans.microedition.lcdui.custom.RepRapListener;
import org.netbeans.microedition.lcdui.custom.RepRapScreen;

/**
 *
 * @author vlada
 */
public class RepRapClientEngine implements RepRapListener, PropertyChangeListener, CommandListener {
    
    private MIDlet parent;
    private List filesOnSDcardList;
    private TextBox temperatureTextBox;
    private RepRapScreen repRapScreen;
    private ConsoleScreen consoleScreen;
    private BTSerialPort serialPort;
    private boolean expectSDfilelist = false;
    private boolean haveSDfilelist = false;
    private boolean extruderTemperature;

    public RepRapClientEngine() {
        temperatureTextBox = new TextBox("Temperature", null, 3, TextField.NUMERIC);                                      
        temperatureTextBox.addCommand(new Command("OK",Command.OK,0));
        temperatureTextBox.addCommand(new Command("Back",Command.BACK,0));
        temperatureTextBox.setCommandListener(this); 
    }

    public void repRapMoveX(byte direction) {
        sendCommand("G91"+"\r\n");
        if (direction>0) {
            sendCommand("G1 X10 F1200"+"\r\n");
        } else {
            sendCommand("G1 X-10 F1200"+"\r\n");
        }
    }

    public void repRapMoveY(byte direction) {
        sendCommand("G91"+"\r\n");
        if (direction>0) {
            sendCommand("G1 Y10 F1200"+"\r\n");
        } else {
            sendCommand("G1 Y-10 F1200"+"\r\n");
        }
    }

    public void repRapMoveZ(byte direction) {
        sendCommand("G91"+"\r\n");
        if (direction>0) {
            sendCommand("G1 Z1 F30"+"\r\n");
        } else {
            sendCommand("G1 Z-1 F30"+"\r\n");
        }
    }

    public void repRapHome() {
        sendCommand("G28"+"\r\n");        
    }

    public void repRapSetExtruderTemperature() {
        extruderTemperature = true;
        temperatureTextBox.setTitle("Extruder temp");
        Display.getDisplay(parent).setCurrent(temperatureTextBox);
    }

    public void repRapSetBedTemperature() {
        extruderTemperature = false;
        temperatureTextBox.setTitle("Bed temp");
        Display.getDisplay(parent).setCurrent(temperatureTextBox);
    }

    public void repRapReadSDcard() {
        sendCommand("M20"+"\r\n");
        expectSDfilelist = true;
    }

    public void propertyChange(String propertyName, int oldValue, int newValue) {
        //System.out.println(propertyName);
        if (propertyName.compareTo("character read") == 0) {
            //System.out.println("char received: " + (char) newValue);
            //consoleScreen.writeChar((char) newValue);
            if (expectSDfilelist) {
                
            }
            if (haveSDfilelist) {
                Display.getDisplay(parent).setCurrent(filesOnSDcardList);
            }
        } else if (propertyName.compareTo("disconnected") == 0) {
            //disconnectMethod();
        }
    }

    public void setFilesOnSDcardList(List filesOnSDcardList) {
        this.filesOnSDcardList = filesOnSDcardList;
    }

    public void setRepRapScreen(RepRapScreen repRapScreen) {
        this.repRapScreen = repRapScreen;
    }

    public void setSerialPort(BTSerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public void setConsoleScreen(ConsoleScreen consoleScreen) {
        this.consoleScreen = consoleScreen;
    }
 
    private void sendCommand(String command) {
        try {
            if (serialPort != null) {
                serialPort.write(command);
            }
            if (consoleScreen != null) {
                consoleScreen.write(command);
            }            
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }

    public void setParent(MIDlet parent) {
        this.parent = parent;
    }

    public void commandAction(Command c, Displayable d) {
        if (c.getCommandType() == Command.OK) {
            if (extruderTemperature) {
                sendCommand("M104 S"+ temperatureTextBox.getString() +"\r\n");
            } else {
                sendCommand("M140 S"+ temperatureTextBox.getString() +"\r\n");
            }
        }
        Display.getDisplay(parent).setCurrent(repRapScreen);
    }
}