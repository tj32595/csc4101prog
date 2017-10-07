package main;

class Define extends Special {

    @Override
    void print(Node root, int n, boolean p) {
        boolean func = true;
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        if (root.isPair()) {
            root.getCar().print(n);
            root = root.getCdr();
            if (!root.getCar().isPair()) {
                func = false;
                System.out.print(" ");
                root.getCar().print(n);
            } else {
                System.out.print(" ");
                root.getCar().print(n);
                System.out.println();
            }
            root = root.getCdr();
            while (root.isPair()) {
                if (func) {
                    System.out.print("    ");
                } else {
                    System.out.print(" ");
                }
                if (root.getCar().isPair()) {
                    root.getCar().print(n, false);
                } else {
                    root.getCar().print(n);
                }
                //System.out.println();
                root = root.getCdr();
            }
        } else {
            System.err.println("Node is not a cons.");
        }
        if (func) {
            System.out.println();
        }
        root.print(0, true);
    }
}
