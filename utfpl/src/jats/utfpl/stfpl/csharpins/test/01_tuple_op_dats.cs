class rec1_id<T1, T2> {
    public T1 m0;
    public T2 m1;

    public rec1_id(T1 m00, T2 m10) {
        m0 = m00;
        m1 = m10;
    }
};

delegate Y fun2_id<X1, X2, Y>(X1 m1, X2 m2);
delegate Y fun3_id<Y>();
delegate Y fun4_id<X1, Y>(X1 m1);

class Program {
    static public Object foo1_3420(Object x_3421, int y_3422) {
        rec1_id<int, int> tup2_id = new rec1_id<int, int>(2, 3);
        return new rec1_id<int, rec1_id<int, int>>(1, tup2_id);
    }
    static public int foo1_3423(Object x_3424, int y_3425) {
        rec1_id<int, int> x_3426 = ((rec1_id<int, rec1_id<int, int>>)x_3424).m1;
        int a_3427 = ((rec1_id<int, int>)x_3426).m0;
        return a_3427;
    }
    static public void main_void_0_54() {
        print("Helloworld");
        return;
    }
}