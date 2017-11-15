package main;

import java.io.*;

public class Main {

    public static void main(String argv[]) throws IOException {
        // create scanner that reads from standard input
        Parser parser = new Parser(new Scanner(System.in));
        Node root;
        
        String[] builtInFunctions = {
            "symbol?", "number?", "b+", "b-", "b/", "b*", "b=", "b<", "car", "cdr",
            "cons", "set-car!", "set-cdr!", "null?", "pair?", "eq?", "procedure?",
            "write", "read", "display", "newline", "eval", "apply", "interaction-environment"
        };
        
        Environment builtIns = new Environment(null);
        builtIns.setName("builtIn");
        Environment file = new Environment(builtIns);
        file.setName("interaction-environment");
        
        for (String builtIn : builtInFunctions) {
            builtIns.define(new Ident(builtIn), new BuiltIn(new Ident(builtIn)));
        }
        builtIns.define(new Ident("interaction-environment"), new BuiltIn(new Ident("interaction-environment")));

        // Parse and pretty-print each input expression
        root = parser.parseExp();
        while (root != null) {
            root.eval(file).print(0);
            parser = new Parser(new Scanner(System.in));
            root = parser.parseExp();
        }
        System.exit(0);
    }
}
