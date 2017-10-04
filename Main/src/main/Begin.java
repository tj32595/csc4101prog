package main;

import java.io.*;

class Begin extends Special {
    
    public Begin() {
        
    }

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    void print(Node t, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        System.out.println("(begin");
        
        Node cdr = t.getCdr();
        if (cdr.isPair()) {
            cdr.print(4, p);
        }
        
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        
        System.out.print(")");
    }
}
