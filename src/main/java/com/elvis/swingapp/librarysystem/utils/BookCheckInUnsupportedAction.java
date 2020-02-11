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
