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



delegate Y fun5_id<X1, Y>(X1 m1);
delegate Y fun1_id<X1, X2, Y>(X1 m1, X2 m2);
delegate void fun6_id<X1>(X1 m1);
delegate void fun4_id<X1, X2>(X1 m1, X2 m2);
delegate Y fun2_id<Y>();
delegate void fun3_id();



class Program {
    static public int read_63(int q_66, int z_67) {
        return 999;
    }
    static public void boo_68() {
        int x_69 = 3;
        mc_set_int1_2581(g1_2591, x_69);
        mc_set_int1_2581(g2_2592, x_69);
        int y_70 = 3;
        return;
    }
    static public void foo_71() {
        int mc_x_73 = mc_get_int1_2585(g1_2591);
        int xx_74 = CComp.pats_add(mc_x_73, 3);
        bool temp6_id = CComp.pats_gt(xx_74, 6);
        mc_assert_2589(temp6_id);
        int temp8_id = read_63(1, 2);
        return;
    }
    static void Main() {
    ();
        return;
    }
}