package main;

import java.io.*;

class IntLit extends Node {

    private int intVal;

    public IntLit(int i) {
        intVal = i;
    }
    
    public int getValue() {
        return intVal;
    }

    public boolean isNumber() {
        return true;
    }

    public void print(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }

        System.out.print(intVal);
    }
}
