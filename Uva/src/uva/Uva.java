package uva;

class Uva {

    static class C {

        public int foo(C p) {
            System.out.println("C.foo(C)");
            return 1;
        }
    }

    static class D extends C {

        public int foo(C p) {
            System.out.println("D.foo(C)");
            return 2;
        }

        public int foo(D p) {
            System.out.println("D.foo(D)");
            return 3;
        }
    }

    public static void main(String[] args) {
        C p = new C();
        C q = new D();
        D r = new D();
        int i = p.foo(r);
        System.out.println(i);
        int j = q.foo(q);
        System.out.println(j);
        int k = q.foo(r);
        System.out.println(k);
    }
}
