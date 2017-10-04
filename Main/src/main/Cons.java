package main;

class Cons extends Node {

    private Node car;
    private Node cdr;
    private Special form;

    // parseList() `parses' special forms, constructs an appropriate
    // object of a subclass of Special, and stores a pointer to that
    // object in variable form.  It would be possible to fully parse
    // special forms at this point.  Since this causes complications
    // when using (incorrect) programs as data, it is easiest to let
    // parseList only look at the car for selecting the appropriate
    // object from the Special hierarchy and to leave the rest of
    // parsing up to the interpreter.
    void parseList() {
        if (car.isSymbol()) {
            Ident id = (Ident) car;
            String idName = id.getName();
            
            switch(idName) {
                case "quote":
                    form = new Quote();
                    break;
                case "'":
                    form = new Quote();
                    break;
                case "lambda":
                    form = new Lambda();
                    break;
                case "begin":
                    form = new Begin();
                    break;
                case "if":
                    form = new If();
                    break;
                case "let":
                    form = new Let();
                    break;
                case "cond":
                    form = new Cond();
                    break;
                case "define":
                    form = new Define();
                    break;
                case "set!":
                    form = new Set();
                    break;
                default:
                    form = new Regular();
                    break;
            }
        }
        else {
            form = new Regular();
        }
    }
    // TODO: Add any helper functions for parseList as appropriate.

    public Cons(Node a, Node d) {
        car = a;
        cdr = d;
        parseList();
    }

    @Override
    public boolean isPair() {
        return true;
    }

    @Override
    void print(int n) {
        form.print(this, n, false);
    }

    @Override
    void print(int n, boolean p) {
        form.print(this, n, p);
    }
    
    // TODO implement
    @Override
    public Node getCar() {
        return car;
    }

    @Override
    public Node getCdr() {
        return cdr;
    }

    // After setCar, a Cons cell needs to be `parsed' again using parseList.
    @Override
    public void setCar(Node a) {
        car = a;
        parseList();
    }

    @Override
    public void setCdr(Node d) {
        cdr = d;
    }

}
