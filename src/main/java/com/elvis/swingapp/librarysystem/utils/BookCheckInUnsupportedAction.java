package com.elvis.swingapp.librarysystem.utils;

public class BookCheckInUnsupportedAction extends Exception {

    /**
     * Creates a new instance of <code>BookCheckInUnsupportedAction</code>
     * without detail message.
     */
    public BookCheckInUnsupportedAction() {
    }

    /**
     * Constructs an instance of <code>BookCheckInUnsupportedAction</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public BookCheckInUnsupportedAction(String msg) {
        super(msg);
    }
}
