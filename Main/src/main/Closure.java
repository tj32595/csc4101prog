package main;
// Closure.java -- the data structure for function closures

// Class Closure is used to represent the value of lambda expressions.
// It consists of the lambda expression itself, together with the
// environment in which the lambda expression was evaluated.
class Closure extends Node {
    private Node fun;		// a lambda expression
    private Environment env;	// the environment in which the function
				// was defined

    public Closure(Node f, Environment e)	{ fun = f;  env = e; }

    public Node getFun()		{ return fun; }
    public Environment getEnv()		{ return env; }

    @Override
    public boolean isProcedure()	{ return true; }

    @Override
    public void print(int n) {
	// there got to be a more efficient way to print n spaces
	for (int i = 0; i < n; i++)
	    System.out.print(' ');
	System.out.println("#{Procedure");
	fun.print(n + 4);
	for (int i = 0; i < n; i++)
	    System.out.print(' ');
	System.out.println('}');
    }
    
    @Override
    public Node eval(Environment env) {
        return fun.apply(fun.getCdr().getCdr().getCdr());
    }

    // The method apply() takes the environment out of the closure,
    // adds a new frame for the function call, defines bindings for the
    // parameters with the argument values in the new frame, and evaluates
    // the function body.
    @Override
    public Node apply (Node args) {
	env = new Environment(env);
        Node function = fun.getCdr().getCar();
        while (args != null) {
            if (function == null) {
                System.err.println("Too many arguments.");
                break;
            }
            env.define(function.getCar(), args.getCar());
            function = function.getCdr();
            args = args.getCdr();
        }
        if (function != null) {
            System.err.println("Not enough arguments.");
        }
        
        return fun.eval(env);
    }
}
