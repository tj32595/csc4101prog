package main;

import java.io.*;

class If extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    @Override
    void print(Node t, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.print("(if ");

        Node cadr = t.getCdr().getCar();
        if (cadr.isPair()) {
            cadr.print(0, p);
        }

        System.out.println();

        Node caddr = t.getCdr().getCdr().getCar();
        if (!caddr.isNull()) {
            caddr.print(n + 4, p);
        }

        System.out.println();

        Node cadddr = t.getCdr().getCdr().getCdr().getCar();
        if (!cadddr.isNull()) {
            cadddr.print(n + 4, p);
        }

        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.print(')');
    }
}
