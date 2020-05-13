package com.elvis.swingapp.librarysystem.utils;

/**
 *
 * @author elvis
 */
public class InvalidLoginAction extends Exception {

    /**
     * Creates a new instance of <code>InvalidLoginAction</code> without detail
     * message.
     */
    public InvalidLoginAction() {
    }

    /**
     * Constructs an instance of <code>InvalidLoginAction</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidLoginAction(String msg) {
        super(msg);
    }
}
