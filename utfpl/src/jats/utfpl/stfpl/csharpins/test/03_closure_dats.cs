
delegate Y fun3_id<Y>();
delegate Y fun1_id<X1, Y>(X1 m1);
delegate Y fun2_id<X1, X2, Y>(X1 m1, X2 m2);

class Program {
    static public int foo1_3420(int x_3421) {
        int x1_3422 = fun: +(1, 2);
        int x2_3423 = fun: foo1_3420(x_3421);
        return x1_3422;
    }
    static public int foo2_3424(Object f_3425, Closure env) {
        int x_3426 = 1;
        int y_3427 = clo: ((fun2_id<int, Closure, int>)((Closure)f_3425).func)(x_3426, ((Closure)f_3425).env);
        return y_3427;
    }
    static public int foo4_3429(int x_3430) {
        return x_3430;
    }
    static public Object foo3_3428() {
        return foo4_3429;
    }
    static public int foo6_3433(int x_3434, Closure env) {
        return fun: +(y_3432, x_3434);
    }
    static public int foo5_3431(Closure env) {
        int y_3432 = 1;
        CSInsClosure_st
        return clo: foo6_3433(1, ((Closure)foo6_3433).env);
    }
}