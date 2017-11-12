package main;

import java.io.*;

public class Main {

    public static void main(String argv[]) throws IOException {
        // create scanner that reads from standard input
        Scanner scanner = new Scanner(System.in);

        // Create parser
        Parser parser = new Parser(scanner);
        Node root;
        
        String[] builtInFunctions = {
            "symbol?", "number?", "b+", "b-", "b/", "b*", "b=", "b<", "car", "cdr",
            "cons", "set-car!", "set-cdr!", "null?", "pair?", "eq?", "procedure?",
            "write", "read", "display", "newline", "eval", "apply", "interaction-environment"
        };
        
        Environment builtIns = new Environment();
        
        for (String builtIn : builtInFunctions) {
            builtIns.define(new Ident(builtIn), new BuiltIn(new Ident(builtIn)));
        }
        builtIns.define(new Ident("interaction-environment"), new BuiltIn(builtIns));

        // Parse and pretty-print each input expression
        root = parser.parseExp();
        while (root != null) {
            root.eval(builtIns).print(0);
            root = parser.parseExp();
        }
        System.exit(0);
    }
}
