package com.elvis.swingapp.librarysystem.utils;

public class BookCheckoutUnsupportedAction extends Exception {

    /**
     * Creates a new instance of <code>BookCheckoutUnsupportedAction</code>
     * without detail message.
     */
    public BookCheckoutUnsupportedAction() {
    }

    /**
     * Constructs an instance of <code>BookCheckoutUnsupportedAction</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public BookCheckoutUnsupportedAction(String msg) {
        super(msg);
    }
}
