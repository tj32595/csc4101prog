package main;

import java.io.*;

class Quote extends Special {

    public Quote() {

    }

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    @Override
    void print(Node root, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        if (root.isPair()) {
            root = root.getCdr();
            if (!root.isNull()) {
                root.getCar().print(n);
                root = root.getCdr();
            }
            while (root.isPair()) {
                System.out.print(" ");
                if (root.getCar().isPair()) {
                    root.getCar().print(n, false);
                } else {
                    root.getCar().print(n);
                }
                root = root.getCdr();
            }
        } else {
            System.err.println("Node is not a cons.");
        }
        root.print(0, true);
    }
    
    @Override
    public boolean isQuote() {
        return true;
    }
}
