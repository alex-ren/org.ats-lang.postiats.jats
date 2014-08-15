class Closure {
    public Object func;
    public Object env;

    public Closure(Object func0, Object env0) {
        func = func0;
        env = env0;
    }
}



delegate Y fun2_id<X1, X2, Y>(X1 m1, X2 m2);
delegate Y fun1_id<X1, Y>(X1 m1);

class Env_foo7_3424 {
    public int x_3421;

    public Env_foo7_3424(int x_34210) {
        x_3421 = x_34210;
    }
};


class Program {
    static public void foo7_3424_clo_impl(Object env) {
        foo7_3424 env0 = (foo7_3424)env;
        int z_3425 = fun: +(x_3421, y_3423);
        return;
    }
    static public void foo6_3422_clo_impl(Object env) {
        int y_3423 = x_3421;
        Env_foo7_3424 foo7_3424_env_obj = new Env_foo7_3424(x_3421);
        Closure foo7_3424 = new Closure((fun1_id<Object, void>)foo7_3424_clo_impl, foo7_3424_env_obj);
        return;
    }
    static public void foo5_3420_clo_impl(Object env) {
        int x_3421 = 1;
        Env_foo6_3422 foo6_3422_env_obj = new Env_foo6_3422();
        Closure foo6_3422 = new Closure((fun1_id<Object, void>)foo6_3422_clo_impl, foo6_3422_env_obj);
        return;
    }
}