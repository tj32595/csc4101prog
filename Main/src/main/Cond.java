package main;

class Cond extends Special {

    @Override
    public Node eval(Node function, Environment env) {
        Node exp = function.getCdr();
        while (!exp.isNull()) {
            Node car = exp.getCar();
            Node cdr = exp.getCar().getCdr();
            if (car.getCar().getName().equals("else")) {
                return car.getCdr().getCar().eval(env);
            } else {
                car = car.getCar();
                if (car.eval(env).booleanVal()) {
                    return cdr.getCar().eval(env);
                }
                exp = exp.getCdr();
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
