package main;

import java.io.*;

class Quote extends Special {
    
    public Quote() {
        
    }

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    @Override
    void print(Node t, int n, boolean p) {
        System.out.print("'");
        Node cdr = t.getCdr();
        if (!cdr.isNull()) {
            cdr.print(0, false);
        }
    }
}
