package main;

class StrLit extends Node {

    private String strVal;

    public StrLit(String s) {
        strVal = s;
    }
    
    @Override
    public Node eval(Environment env) {
        return this;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public void print(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }

        System.out.print("\"" + strVal + "\"");
    }
    
    @Override
    public String getName() {
        return strVal;
    }
}
