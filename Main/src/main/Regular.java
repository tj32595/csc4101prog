package main;

class Regular extends Special {

    @Override
    public Node eval(Node function, Environment env) {
        Node car = function.getCar();
        Node cdr = function.getCdr();
        if (car.isSymbol()) {
            Node newCar = env.lookup(car);
            if (newCar != null) {
                if (newCar.isBuiltIn()) {
                    Node evalCdr = cdr.eval(env);
                    return newCar.apply(evalCdr);
                } else {
                    function.setCar(newCar);
                }
            }
        }
        else if (car.isProcedure()) {
            car.apply(cdr.eval(env));
        }
        else if (car.isPair()) {
            return new Cons(car.eval(env), cdr.eval(env));
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
