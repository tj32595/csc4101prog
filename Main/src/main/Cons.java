package main;

class Cons extends Node {

    private Node car;
    private Node cdr;
    private Special form = null;

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
            String idName = car.getName();
            switch (idName) {
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
                case "'let":
                    form = new Let();
                    break;
                case "cond":
                    form = new Cond();
                    break;
                case "define":
                    form = new Define();
                    break;
                case "set":
                    form = new Set();
                    break;
                default:
                    form = new Regular();
                    break;
            }
        } else {
            form = new Regular();
        }
    }

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
        print(n, false);
    }

    @Override
    void print(int n, boolean p) {
        if (car.getName().equals("quote")) {
            System.out.print("'");
            form.print(this.cdr, n, false);
            System.out.print("\b");
        } else {
            if (form.isQuote()) {
                System.out.print("'");
            }
            for (int i = 0; i < n; i++) {
                System.out.print(" ");
            }
            if (!p) {
                System.out.print("(");
            }
            form.print(this, n, true);
        }
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

    @Override
    public Node eval(Environment env) {
        return form.eval(this, env);
    }
}
