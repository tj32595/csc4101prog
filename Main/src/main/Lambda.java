package main;

import java.io.*;

class Lambda extends Special {
    
    public Lambda() {
        
    }

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    @Override
    void print(Node t, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        
        System.out.print("(lambda ");
        
        Node cadr = t.getCdr().getCar();
        if (cadr.isPair()) {
            cadr.print(0, false);
        }
        
        System.out.println();
        
        Node caddr = t.getCdr().getCdr().getCar();
        if (caddr.isPair()) {
            caddr.print(n + 4, false);
        }
        
        System.out.println();
        
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        System.out.print(")");
    }
}
