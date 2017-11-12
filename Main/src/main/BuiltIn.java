package main;
// BuiltIn.java -- the data structure for function closures

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Class BuiltIn is used for representing the value of built-in functions
// such as +.  Populate the initial environment with
// (name, new BuiltIn(name)) pairs.
// The object-oriented style for implementing built-in functions would be
// to include the Java methods for implementing a Scheme built-in in the
// BuiltIn object.  This could be done by writing one subclass of class
// BuiltIn for each built-in function and implementing the method apply
// appropriately.  This requires a large number of classes, though.
// Another alternative is to program BuiltIn.apply() in a functional
// style by writing a large if-then-else chain that tests the name of
// of the function symbol.
class BuiltIn extends Node {

    private BooleanLit f = new BooleanLit(false);
    private BooleanLit t = new BooleanLit(true);

    private Node symbol;

    public BuiltIn(Node s) {
        symbol = s;
    }

    public Node getSymbol() {
        return symbol;
    }

    @Override
    public boolean isProcedure() {
        return true;
    }

    @Override
    public void print(int n) {
        // there got to be a more efficient way to print n spaces
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.println("#{Built-in Procedure");
        symbol.print(n + 4);
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.println('}');
    }

    @Override
    public Node apply(Node args) {
        Node arg1, arg2;
        if (args != null) {
            arg1 = args.getCar();
            args = args.getCdr();
            switch (symbol.getName()) {
                case "interaction-environment":
                    return symbol;
                case "symbol?":
                    if (arg1.isSymbol()) {
                        return t;
                    } else {
                        return f;
                    }
                case "number?":
                    if (arg1.isNumber()) {
                        return t;
                    } else {
                        return f;
                    }
                case "car":
                    return arg1;
                case "null?":
                    if (arg1 == null) {
                        return t;
                    } else {
                        return f;
                    }
                case "pair?":
                    if (arg1.isPair()) {
                        return t;
                    } else {
                        return f;
                    }
                case "procedure?":
                    if (arg1.isProcedure()) {
                        return t;
                    } else {
                        return f;
                    }
                case "write":
                    arg1.print(0);
                    return arg1;
                case "display":
                    arg1.print(0);
                    return arg1;
                case "read":
                    Parser parser = new Parser(new Scanner(System.in));
                     {
                        try {
                            return parser.parseExp();
                        } catch (IOException ex) {
                            Logger.getLogger(BuiltIn.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                case "newline":
                    System.out.println();
                case "eval":
                    return arg1.eval();
                default:
                    if (args != null) {
                        arg2 = args.getCar();
                        switch (symbol.getName()) {
                            case "b+":
                                return new IntLit(arg1.getValue() + arg2.getValue());
                            case "b-":
                                return new IntLit(arg1.getValue() - arg2.getValue());
                            case "b/":
                                return new IntLit(arg1.getValue() / arg2.getValue());
                            case "b*":
                                return new IntLit(arg1.getValue() * arg2.getValue());
                            case "b=":
                                if (arg1.getValue() == arg2.getValue()) {
                                    return t;
                                } else {
                                    return f;
                                }
                            case "b<":
                                if (arg1.getValue() < arg2.getValue()) {
                                    return t;
                                } else {
                                    return f;
                                }
                            case "cdr":
                                return arg2;
                            case "set-car!":
                                arg1.setCar(arg2);
                                return arg1;
                            case "set-cdr!":
                                arg1.setCdr(arg2);
                                return arg1;
                            case "eq?":
                                if (arg1 == arg2) {
                                    return t;
                                } else {
                                    return f;
                                }
                            case "apply":
                                return arg1.apply(arg2);
                            default:
                                return null;
                        }
                    }
            }
        }
        return null;
    }

}
