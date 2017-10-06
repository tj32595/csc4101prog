package main;

import java.io.*;

class Regular extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    void print(Node t, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }

        if (!p) {
            System.out.print('(');
        }
        Node car = t.getCar();
        car.print(0);
        System.out.print(' ');
        Node cdr = t.getCdr();
        cdr.print(0, true);
        
        System.out.print(")");
    }
}
