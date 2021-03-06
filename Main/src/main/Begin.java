package main;

class Begin extends Special {
    
    public Begin() {
        
    }
    
    @Override
    public Node eval(Node function, Environment env) {
        if (!function.getCar().isNull() && !function.getCdr().getCar().isNull()) {
            return function.getCdr().eval(env);
        }
        else {
            System.out.println("Not enough arguments.");
            return function;
        }
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
