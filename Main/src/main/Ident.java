package main;

class Ident extends Node {

    private String name;

    public Ident(String n) {
        name = n;
    }

    @Override
    public boolean isSymbol() {
        return true;
    }

    @Override
    public void print(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }

        System.out.print(name);
    }
    
    public String getName() {
        return name;
    }
}
