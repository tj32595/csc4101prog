package main;

// Special.h -- the parse tree node data structure for special forms
// There are several different approaches for how to implement the Special
// hierarchy.  We'll discuss some of them in class.  The easiest solution
// is to not add any fields and to use empty constructors.
abstract class Special {

    abstract void print(Node t, int n, boolean p);
    
    public boolean isQuote() {
        return false;
    }
    
    public boolean isSpecial() {
        return true;
    }
    
    public Node eval(Node function, Environment env) {return null;}
}
