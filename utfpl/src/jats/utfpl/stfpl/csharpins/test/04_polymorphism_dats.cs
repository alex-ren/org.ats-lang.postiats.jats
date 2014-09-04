using System;
using Postiats;

class Closure {
    public Object func;
    public Object env;

    public Closure(Object func0, Object env0) {
        func = func0;
        env = env0;
    }
}



delegate void fun2_id();
delegate Y fun1_id<X1, X2, Y>(X1 m1, X2 m2);



class Program {
    static public Object left_63(Object x_64, Object y_65) {
        return x_64;
    }
    static public void main_void_0_55() {
        return;
    }
    static void Main() {
        main_void_0_55();
        return;
    }
}