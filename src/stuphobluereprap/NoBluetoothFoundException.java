/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuphobluereprap;

/**
 *
 * @author vlada
 */
public class NoBluetoothFoundException extends Exception {

    /**
     * Creates a new instance of <code>NoBluetoothFoundException</code> without
     * detail message.
     */
    public NoBluetoothFoundException() {
    }

    /**
     * Constructs an instance of <code>NoBluetoothFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoBluetoothFoundException(String msg) {
        super(msg);
    }
}
