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
public class RepRapClientEngine implements RepRapListener, PropertyChangeListener, CommandListener, Runnable {
    
    private MIDlet parent;
    private List filesOnSDcardList;
    private TextBox temperatureTextBox;
    private RepRapScreen repRapScreen;
    private ConsoleScreen consoleScreen;
    private BTSerialPort serialPort;
    private boolean expectSDfilelist = false;
    private boolean expectTemperatureAnswer = false;
    private boolean haveSDfilelist = false;
    private boolean extruderTemperature;
    private String cmd = "";
    private boolean hideExpectedTemperatureAnswers = true;
    private boolean convertFilenamesToLowercase = true;

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
            sendCommand("G1 Z1 F60"+"\r\n");
        } else {
            sendCommand("G1 Z-1 F60"+"\r\n");
        }
    }

    public void repRapHome() {
        sendCommand("G28"+"\r\n");        
    }

    public void repRapSetExtruderTemperature() {
        extruderTemperature = true;
        temperatureTextBox.setTitle("Extruder temp");
        temperatureTextBox.setString("");
        Display.getDisplay(parent).setCurrent(temperatureTextBox);
    }

    public void repRapSetBedTemperature() {
        extruderTemperature = false;
        temperatureTextBox.setTitle("Bed temp");
        temperatureTextBox.setString("");
        Display.getDisplay(parent).setCurrent(temperatureTextBox);
    }

    public void repRapReadSDcard() {
        sendCommand("M20"+"\r\n");
        expectSDfilelist = true;
        filesOnSDcardList.deleteAll();
    }

    public void propertyChange(String propertyName, int oldValue, int newValue) {
        //System.out.println(propertyName);
        if (propertyName.compareTo("character read") == 0) {
            //System.out.println("char received: " + (char) newValue);
            //consoleScreen.writeChar((char) newValue);
            
            char c = (char) newValue;
            if ((c=='\n' || c=='\r') && cmd.length() > 0) {
                boolean writeCommandToConsole = true;
                int Tpos = cmd.indexOf("T:");
                int Bpos = cmd.indexOf("B:");
                //System.out.println("Tpos = " + Tpos + "; Bpos = " + Bpos);
                if (Tpos > 0 && Bpos >0) {
                    int TposEnd = cmd.indexOf(" ",Tpos+2);
                    if (TposEnd < 0) {
                        TposEnd = cmd.length();
                    }
                    int BposEnd = cmd.indexOf(" ",Bpos+2);
                    if (BposEnd < 0) {
                        BposEnd = cmd.length();
                    }
                    int Ttemp = (int) Double.parseDouble(cmd.substring(Tpos+2, TposEnd));
                    int Btemp = (int) Double.parseDouble(cmd.substring(Bpos+2, BposEnd));
                    
                    if (repRapScreen!= null) {
                        repRapScreen.addTemp(new int[] {Ttemp}, new int[] {Btemp});                        
                    }
                    
                    if (expectTemperatureAnswer) {
                        expectTemperatureAnswer = false;
                        if (hideExpectedTemperatureAnswers) {
                            writeCommandToConsole = false;
                        }
                    }
                }
                
                if (expectSDfilelist) {
                    if (cmd.startsWith("ok")) {
                        haveSDfilelist = true;
                        expectSDfilelist = false;
                    } else if (!cmd.startsWith("Begin file list") && !cmd.startsWith("End file list")) {
                        filesOnSDcardList.append(cmd, null);
                    }
                }
                if (haveSDfilelist) {
                    Display.getDisplay(parent).setCurrent(filesOnSDcardList);
                    haveSDfilelist = false;
                }
                
                if (writeCommandToConsole && consoleScreen!=null) {
                    consoleScreen.write(cmd + "\n");
                }
                cmd = "";
            } else {
                cmd += c;
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
            if (consoleScreen != null && (!command.startsWith("M105") || !hideExpectedTemperatureAnswers)) {
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
                int extTemp = Integer.parseInt(temperatureTextBox.getString());
                sendCommand("M104 S"+ extTemp +"\r\n");
                repRapScreen.setExtruderTemperatureRequest(extTemp);
            } else {
                int bedTemp = Integer.parseInt(temperatureTextBox.getString());
                sendCommand("M140 S"+ bedTemp +"\r\n");
                repRapScreen.setBedTemperatureRequest(bedTemp);
            }
        }
        Display.getDisplay(parent).setCurrent(repRapScreen);
    }

    public void setHideExpectedTemperatureAnswers(boolean hideExpectedTemperatureAnswers) {
        this.hideExpectedTemperatureAnswers = hideExpectedTemperatureAnswers;
    }

    public void setConvertFilenamesToLowercase(boolean convertFilenamesToLowercase) {
        this.convertFilenamesToLowercase = convertFilenamesToLowercase;
    }

    public boolean getConvertFilenamesToLowercase() {
        return convertFilenamesToLowercase;
    }

    public void run() {
        while (true) {
            try {
                sendCommand("M105"+"\r\n");
                expectTemperatureAnswer = true;
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}
