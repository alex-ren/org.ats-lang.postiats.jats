class Closure {
    public Object func;
    public Object env;

    public Closure(Object func0, Object env0) {
        func = func0;
        env = env0;
    }
}



delegate Y fun1_id<X1, X2, Y>(X1 m1, X2 m2);

class Env_foo3_3420 {
    public int y_3425;
    public int x_3422;

    public Env_foo3_3420(int y_34250, int x_34220) {
        y_3425 = y_34250;
        x_3422 = x_34220;
    }
};
class Env_foo4_3423 {
    public int x_3422;

    public Env_foo4_3423(int x_34220) {
        x_3422 = x_34220;
    }
};


class Program {
    static public int foo4_3423_clo_impl(int y_3424, Object env) {
        foo4_3423 env0 = (foo4_3423)env;
        return fun: +(y_3424, x_3422);
    }
    static public int foo3_3420_clo_impl(int x_3421, Object env) {
        foo3_3420 env0 = (foo3_3420)env;
        int x_3422 = 1;
        Env_foo4_3423 foo4_3423_env_obj = new Env_foo4_3423(x_3422);
        Closure foo4_3423 = new Closure((fun1_id<int, Object, int>)foo4_3423_clo_impl, foo4_3423_env_obj);
        int y_3425 = clo1: ((fun1_id<int, Object, int>)((Closure)foo4_3423).func)(2, ((Closure)foo4_3423).env);
        return y_3425;
    }
}