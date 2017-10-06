package main;

import java.io.*;

class Define extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    @Override
    void print(Node t, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }

        System.out.print("(define ");

        Node cadr = t.getCdr().getCar();
        if (cadr.isSymbol()) {
            cadr.print(0, false);
        }

        System.out.print(" ");

        Node caddr = t.getCdr().getCdr().getCar();
        if (!caddr.isNull()) {
            caddr.print(0, true);
        }
        System.out.print(")");
    }
}
