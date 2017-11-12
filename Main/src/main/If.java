package main;

class If extends Special {
    
    @Override
    public Node eval(Node function, Environment env) {
        Node condition = function.getCdr().getCar();
        Node exp = function.getCdr().getCdr();
        Node elseExp = function.getCdr().getCdr().getCdr();
        if (condition.eval(env).booleanVal()) {
            exp.eval(env);
        }
        else {
            if (!elseExp.isNull()) {
                elseExp.eval(env);
            }
        }
        return function;
    }

    @Override
    void print(Node root, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        if (root.isPair()) {
            root.getCar().print(n);
            root = root.getCdr();
            System.out.print(" ");
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
