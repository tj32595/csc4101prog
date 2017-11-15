package main;

class If extends Special {

    @Override
    public Node eval(Node function, Environment env) {
        Node condition = function.getCdr().getCar();
        Node exp = function.getCdr().getCdr();
                Node elseExp = function.getCdr().getCdr().getCdr();
        boolean test = condition.eval(env).booleanVal();
        if (test) {
            return exp.eval(env);
        } else {
            if  (!elseExp.isNull()){
                return elseExp.eval(env);
            }
        }
        return new Nil();
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
