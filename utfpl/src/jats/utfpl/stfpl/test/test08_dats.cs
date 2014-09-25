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


class rec2_id {

    public rec2_id() {
    }
};

delegate Y fun1_id<Y>();



class Program {
    static public int foo2_63() {
        return 3;
    }
    static void Main() {
        rec2_id env1_id = new rec2_id();
        Closure foo2_63 = new Closure((fun1_id<int>)foo2_63_clo_impl, env1_id);
    ();
        return;
    }
}