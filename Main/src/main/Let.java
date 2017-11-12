package main;

class Let extends Special {
    
    public Let() {
        
    }
    
    @Override
    public Node eval(Node function, Environment env) {
        Environment newEnv = new Environment(env);
        Node definitions = function.getCdr().getCar();
        while (!definitions.isNull()) {
            newEnv.define(definitions.getCar().getCar(), definitions.getCar().getCdr().getCar());
            definitions = definitions.getCdr();
        }
        Node exp = function.getCdr().getCdr().getCar();
        return exp.eval(newEnv);
    }

    @Override
    void print(Node root, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        if (root.isPair()) {
            root.getCar().print(n);
            root = root.getCdr();
            while (root.isPair()) {
                System.out.println();
                System.out.print("    ");
                if (root.getCar().isPair()) {
                    root.getCar().print(n, false);
                } else {
                    root.getCar().print(n);
                }
                root = root.getCdr();
            }
        } else {
            System.err.println("Node is not a cons.");
        }
        System.out.println();
        root.print(0, true);
    }
}
