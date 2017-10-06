package main;

import java.io.*;

class Set extends Special {

    public Set() {

    }

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    void print(Node t, int n, boolean p) {
        if (!p) {
            System.out.print("(");
        }
        Node car = t.getCar();
        if (car.isPair()) {
            car.print(0, false);
        } else {
            car.print(0, true);
        }

        System.out.print(" ");
        Node cdr = t.getCdr();
        if (cdr.isNull()) {
            System.out.println(")");
        } else {
            cdr.print(0, true);
        }
    }
}
