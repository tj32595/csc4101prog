package main;

class Set extends Special {

    public Set() {

    }
    
    @Override
    public Node eval(Node function, Environment env) {
        Node arg1 = function.getCdr().getCar();
        Node arg2 = function.getCdr().getCdr().getCar();
        env.assign(arg1, arg2.eval(env));
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
            while (root.isPair()) {
                System.out.print(" ");
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
        root.print(0, true);
    }
}
