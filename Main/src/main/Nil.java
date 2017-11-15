package main;

class Nil extends Node {

    public Nil() {
    }

    @Override
    public boolean isNull() {
        return true;
    }
    
    @Override
    public Node eval(Environment env) {
        return this;
    }

    @Override
    public void print(int n) {
        print(n, false);
    }

    @Override
    public void print(int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }

        if (p) {
            System.out.print(")");
        }
    }
}
