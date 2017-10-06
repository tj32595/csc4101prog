package main;

import java.io.*;

class Let extends Special {
    
    public Let() {
        
    }

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    void print(Node t, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.print("(let ");

        Node cadr = t.getCdr().getCar();
        if (cadr.isPair()) {
            cadr.print(0, false);
        }

        System.out.println();

        Node cddr = t.getCdr().getCdr();
        if (cddr.isPair()) {
            cddr.print(n + 4, true);
        }

        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.print(")");
    }
}
