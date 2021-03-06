package main;

class Node {
    // The argument of print(int) is the number of characters to indent.
    // Every subclass of Node must implement print(int).

    void print(int n) {}

    // The first argument of print(int, boolean) is the number of characters
    // to indent.  It is interpreted the same as for print(int).
    // The second argument is only useful for lists (nodes of classes
    // Cons or Nil).  For all other subclasses of Node, the booleanean
    // argument is ignored.  Therefore, print(n,p) defaults to print(n)
    // for all classes other than Cons and Nil.
    // For classes Cons and Nil, print(n,TRUE) means that the open
    // parenthesis was printed already by the caller.
    // Only classes Cons and Nil override print(int,boolean).
    // For correctly indenting special forms, you might need to pass
    // additional information to print.  What additional information
    // you pass is up to you.  If you only need one more bit, you can
    // encode that in the sign bit of n.  If you need additional parameters,
    // make sure that you define the method print in all the appropriate
    // subclasses of Node as well.
    void print(int n, boolean p) {}

    // For parsing Cons nodes, for printing trees, and later for
    // evaluating them, we need some helper functions that test
    // the type of a node and that extract some information.
    public boolean isBoolean() {
        return false;
    }  // BooleanLit
    
    public boolean booleanVal() {
        return false;
    }
    
    public boolean isBuiltIn() {
        return false;
    }
    
    public boolean isProcedure() {
        return false;
    } // Closure

    public boolean isNumber() {
        return false;
    }  // IntLit

    public boolean isString() {
        return false;
    }  // StringLit

    public boolean isSymbol() {
        return false;
    }  // Ident

    public boolean isNull() {
        return false;
    }  // nil

    public boolean isPair() {
        return false;
    }  // Cons

    public Node getCar() {
        System.err.println("Cannot use getCar() on a Node object.");
        return null;
    }

    public Node getCdr() {
        System.err.println("Cannot use getCdr() on a Node object.");
        return null;
    }

    public void setCar(Node a) {
        System.err.println("Cannot use setCar() on a Node object.");
    }

    public void setCdr(Node d) {
        System.err.println("Cannot use setCdr() on a Node object.");
    }
    
    public String getName() {
        return "";
    }
    
    public int getValue() {
        return 0;
    }
    
    public Node apply(Node args) {
        System.err.println("Cannot use apply() on a Node object.");
        return null;
    }

    public Node eval(Environment env) {
        return null;
    }
}
