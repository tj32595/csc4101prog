package main;

class IntLit extends Node {

    private int intVal;
    
    @Override
    public Node eval(Environment env) {
        return this;
    }

    public IntLit(int i) {
        intVal = i;
    }
    
    @Override
    public int getValue() {
        return intVal;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public void print(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }

        System.out.print(intVal);
    }
}
