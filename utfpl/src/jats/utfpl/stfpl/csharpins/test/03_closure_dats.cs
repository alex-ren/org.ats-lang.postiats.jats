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


class rec7_id<T1> {
    public T1 x_3461;

    public rec7_id(T1 x_34610) {
        x_3461 = x_34610;
    }
};
class rec12_id<T1> {
    public T1 y_3478;

    public rec12_id(T1 y_34780) {
        y_3478 = y_34780;
    }
};
class rec10_id<T1, T2> {
    public T1 foo9_3472;
    public T2 foo8_3471;

    public rec10_id(T1 foo9_34720, T2 foo8_34710) {
        foo9_3472 = foo9_34720;
        foo8_3471 = foo8_34710;
    }
};
class rec8_id<T1, T2> {
    public T1 x_3466;
    public T2 y_3468;

    public rec8_id(T1 x_34660, T2 y_34680) {
        x_3466 = x_34660;
        y_3468 = y_34680;
    }
};
class rec13_id<T1> {
    public T1 x_3482;

    public rec13_id(T1 x_34820) {
        x_3482 = x_34820;
    }
};
class rec9_id<T1> {
    public T1 x_3466;

    public rec9_id(T1 x_34660) {
        x_3466 = x_34660;
    }
};
class rec11_id<T1> {
    public T1 foo8_3471;

    public rec11_id(T1 foo8_34710) {
        foo8_3471 = foo8_34710;
    }
};
class rec6_id {

    public rec6_id() {
    }
};

delegate Y fun3_id<X1, X2, Y>(X1 m1, X2 m2);
delegate Y fun5_id<X1, X2, X3, Y>(X1 m1, X2 m2, X3 m3);
delegate void fun1_id();
delegate void fun4_id<X1>(X1 m1);
delegate Y fun2_id<X1, Y>(X1 m1);



class Program {
    static public void foo1_3449() {
        int y_3450 = 1;
        return;
    }
    static public void foo_3447() {
        int x_3448 = 3;
        return;
    }
    static public int foo1_3451(int x_3452) {
        int x1_3453 = CComp.pats_add(1, 2);
        int x2_3454 = foo1_3451(x_3452);
        return x1_3453;
    }
    static public int foo2_3455_clo_impl(Object f_3456, Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo2_3455 = new Closure((fun3_id<Object, Object, int>)foo2_3455_clo_impl, env0);
        int x_3457 = 1;
        int y_3458 = ((fun3_id<int, Object, int>)((Closure)f_3456).func)(x_3457, ((Closure)f_3456).env);
        return y_3458;
    }
    static public int foo4_3462_clo_impl(int y_3463, Object env) {
        rec7_id<int> env0 = (rec7_id<int>)env;
        Closure foo4_3462 = new Closure((fun3_id<int, Object, int>)foo4_3462_clo_impl, env0);
        return CComp.pats_add(y_3463, env0.x_3461);
    }
    static public int foo3_3459_clo_impl(int x_3460, Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo3_3459 = new Closure((fun3_id<int, Object, int>)foo3_3459_clo_impl, env0);
        int x_3461 = 1;
        rec7_id<int> env8_id = new rec7_id<int>(x_3461);
        Closure foo4_3462 = new Closure((fun3_id<int, Object, int>)foo4_3462_clo_impl, env8_id);
        int y_3464 = ((fun3_id<int, Object, int>)((Closure)foo4_3462).func)(2, ((Closure)foo4_3462).env);
        return y_3464;
    }
    static public void foo7_3469_clo_impl(Object env) {
        rec8_id<int, int> env0 = (rec8_id<int, int>)env;
        Closure foo7_3469 = new Closure((fun4_id<Object>)foo7_3469_clo_impl, env0);
        int z_3470 = CComp.pats_add(env0.x_3466, env0.y_3468);
        return;
    }
    static public void foo6_3467_clo_impl(Object env) {
        rec9_id<int> env0 = (rec9_id<int>)env;
        Closure foo6_3467 = new Closure((fun4_id<Object>)foo6_3467_clo_impl, env0);
        int y_3468 = env0.x_3466;
        rec8_id<int, int> env14_id = new rec8_id<int, int>(y_3468, env0.x_3466);
        Closure foo7_3469 = new Closure((fun4_id<Object>)foo7_3469_clo_impl, env14_id);
        return;
    }
    static public void foo5_3465_clo_impl(Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo5_3465 = new Closure((fun4_id<Object>)foo5_3465_clo_impl, env0);
        int x_3466 = 1;
        rec9_id<int> env12_id = new rec9_id<int>(x_3466);
        Closure foo6_3467 = new Closure((fun4_id<Object>)foo6_3467_clo_impl, env12_id);
        return;
    }
    static public void foo10_3473_clo_impl(Object env) {
        rec10_id<Object, Object> env0 = (rec10_id<Object, Object>)env;
        Closure foo10_3473 = new Closure((fun4_id<Object>)foo10_3473_clo_impl, env0);
        ((fun4_id<Object>)((Closure)env0.foo8_3471).func)(((Closure)env0.foo8_3471).env);
        ((fun4_id<Object>)((Closure)env0.foo9_3472).func)(((Closure)env0.foo9_3472).env);
        ((fun4_id<Object>)((Closure)foo10_3473).func)(((Closure)foo10_3473).env);
        ((fun4_id<Object>)((Closure)env0.foo8_3471).func)(((Closure)env0.foo8_3471).env);
        return;

    }
    static public void foo9_3472_clo_impl(Object env) {
        rec11_id<Object> env0 = (rec11_id<Object>)env;
        Closure foo9_3472 = new Closure((fun4_id<Object>)foo9_3472_clo_impl, env0);
        rec10_id<Object, Object> env20_id = new rec10_id<Object, Object>(foo9_3472, env0.foo8_3471);
        Closure foo10_3473 = new Closure((fun4_id<Object>)foo10_3473_clo_impl, env20_id);
        ((fun4_id<Object>)((Closure)env0.foo8_3471).func)(((Closure)env0.foo8_3471).env);
        return;

    }
    static public void foo8_3471_clo_impl(Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo8_3471 = new Closure((fun4_id<Object>)foo8_3471_clo_impl, env0);
        rec11_id<Object> env18_id = new rec11_id<Object>(foo8_3471);
        Closure foo9_3472 = new Closure((fun4_id<Object>)foo9_3472_clo_impl, env18_id);
        ((fun4_id<Object>)((Closure)foo9_3472).func)(((Closure)foo9_3472).env);
        return;

    }
    static public int foo12_3475_clo_impl(int x_3476, Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo12_3475 = new Closure((fun3_id<int, Object, int>)foo12_3475_clo_impl, env0);
        return x_3476;
    }
    static public Object foo11_3474_clo_impl(Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo11_3474 = new Closure((fun2_id<Object, Object>)foo11_3474_clo_impl, env0);
        rec6_id env27_id = new rec6_id();
        Closure foo12_3475 = new Closure((fun3_id<int, Object, int>)foo12_3475_clo_impl, env27_id);
        return foo12_3475;
    }
    static public int foo14_3479_clo_impl(int x_3480, Object env) {
        rec12_id<int> env0 = (rec12_id<int>)env;
        Closure foo14_3479 = new Closure((fun3_id<int, Object, int>)foo14_3479_clo_impl, env0);
        return CComp.pats_add(env0.y_3478, x_3480);
    }
    static public int foo13_3477_clo_impl(Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo13_3477 = new Closure((fun2_id<Object, int>)foo13_3477_clo_impl, env0);
        int y_3478 = 1;
        rec12_id<int> env31_id = new rec12_id<int>(y_3478);
        Closure foo14_3479 = new Closure((fun3_id<int, Object, int>)foo14_3479_clo_impl, env31_id);
        return ((fun3_id<int, Object, int>)((Closure)foo14_3479).func)(1, ((Closure)foo14_3479).env);
    }
    static public int foo16_3483_clo_impl(int y_3484, Object env) {
        rec13_id<int> env0 = (rec13_id<int>)env;
        Closure foo16_3483 = new Closure((fun3_id<int, Object, int>)foo16_3483_clo_impl, env0);
        return CComp.pats_add(env0.x_3482, y_3484);
    }
    static public void foo15_3481() {
        int x_3482 = 1;
        rec13_id<int> env34_id = new rec13_id<int>(x_3482);
        Closure foo16_3483 = new Closure((fun3_id<int, Object, int>)foo16_3483_clo_impl, env34_id);
        int y_3485 = ((fun3_id<int, Object, int>)((Closure)foo16_3483).func)(3, ((Closure)foo16_3483).env);
        return;
    }
    static public int foo17_3486_clo_impl(Object f_3487, int x_3488, Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo17_3486 = new Closure((fun5_id<Object, int, Object, int>)foo17_3486_clo_impl, env0);
        int y_3489 = ((fun3_id<int, Object, int>)((Closure)f_3487).func)(x_3488, ((Closure)f_3487).env);
        return y_3489;
    }
    static public int foo18_3490_clo_impl(Object f_3491, int x_3492, Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo18_3490 = new Closure((fun5_id<Object, int, Object, int>)foo18_3490_clo_impl, env0);
        int y_3493 = ((fun3_id<int, Object, int>)((Closure)f_3491).func)(x_3492, ((Closure)f_3491).env);
        return y_3493;
    }
    static public int foo20_3495_clo_impl(int x_3496, Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo20_3495 = new Closure((fun3_id<int, Object, int>)foo20_3495_clo_impl, env0);
        return x_3496;
    }
    static public Object foo19_3494_clo_impl(Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo19_3494 = new Closure((fun2_id<Object, Object>)foo19_3494_clo_impl, env0);
        rec6_id env42_id = new rec6_id();
        Closure foo20_3495 = new Closure((fun3_id<int, Object, int>)foo20_3495_clo_impl, env42_id);
        return foo20_3495;
    }
    static public int foo22_3499(int x_3500) {
        return x_3500;
    }
    static public Object foo21_3497_clo_impl(Object env) {
        rec6_id env0 = (rec6_id)env;
        Closure foo21_3497 = new Closure((fun2_id<Object, Object>)foo21_3497_clo_impl, env0);
        int y_3498 = 3;
        return (fun2_id<int, int>)foo22_3499;
    }
    static public void main_void_0_55() {
        CComp.pats_print("Helloworld, ATS2.\n");
        return;
    }
    static void Main() {
        rec6_id env4_id = new rec6_id();
        Closure foo2_3455 = new Closure((fun3_id<Object, Object, int>)foo2_3455_clo_impl, env4_id);
        rec6_id env6_id = new rec6_id();
        Closure foo3_3459 = new Closure((fun3_id<int, Object, int>)foo3_3459_clo_impl, env6_id);
        rec6_id env10_id = new rec6_id();
        Closure foo5_3465 = new Closure((fun4_id<Object>)foo5_3465_clo_impl, env10_id);
        rec6_id env16_id = new rec6_id();
        Closure foo8_3471 = new Closure((fun4_id<Object>)foo8_3471_clo_impl, env16_id);
        rec6_id env25_id = new rec6_id();
        Closure foo11_3474 = new Closure((fun2_id<Object, Object>)foo11_3474_clo_impl, env25_id);
        rec6_id env29_id = new rec6_id();
        Closure foo13_3477 = new Closure((fun2_id<Object, int>)foo13_3477_clo_impl, env29_id);
        rec6_id env36_id = new rec6_id();
        Closure foo17_3486 = new Closure((fun5_id<Object, int, Object, int>)foo17_3486_clo_impl, env36_id);
        rec6_id env38_id = new rec6_id();
        Closure foo18_3490 = new Closure((fun5_id<Object, int, Object, int>)foo18_3490_clo_impl, env38_id);
        rec6_id env40_id = new rec6_id();
        Closure foo19_3494 = new Closure((fun2_id<Object, Object>)foo19_3494_clo_impl, env40_id);
        rec6_id env44_id = new rec6_id();
        Closure foo21_3497 = new Closure((fun2_id<Object, Object>)foo21_3497_clo_impl, env44_id);
        main_void_0_55();
        return;
    }
}