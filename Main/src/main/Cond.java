package main;

import java.io.*;

class Cond extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    @Override
    void print(Node t, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.println("(cond");

        Node cdr = t.getCdr();
        if (cdr.isPair()) {
            cdr.print(n + 4, true);
        }
    }
}
