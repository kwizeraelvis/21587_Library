/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.utils;

/**
 *
 * @author elvis
 */
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
