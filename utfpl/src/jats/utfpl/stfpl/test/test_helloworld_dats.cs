class rec1_id<T1, T2> {
    T1 m0;
    T2 m1;

    public rec1_id(T1 m00, T2 m10) {
        m0 = m00;
        m1 = m10;
    }
};

delegate Y fun3_id<X1, Y>(X1 m1);
delegate Y fun2_id<X1, X2, Y>(X1 m1, X2 m2);
delegate Y fun4_id<Y>();

class Program {
    static public int foo1_3420(Object x_3421, int y_3422) {
        return 3;
    }
    static public int foo2_3423(Object x_3424, int y_3425) {
        return y_3425;
    }
    static public Object foo3_3426(int x_3427) {
        return new rec1_id<int, int>(3, 3);
    }
    static public int foo4_3428() {
        return +(1, 2);
    }
    static public void main_void_0_54() {
        print("Helloworld");
        return;
    }
}