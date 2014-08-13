
delegate Y fun2_id<Y>();
delegate Y fun1_id<X1, X2, Y>(X1 m1, X2 m2);
delegate Y fun3_id<X1, Y>(X1 m1);

class Program {
    static public int foo_3420(int x_3421, int y_3422) {
        bool temp2_id = >(x_3421, y_3422);
        if (temp2_id) {
            return x_3421;
        } else {
            return y_3422;
        }
    }
    static public void main_void_0_54() {
        print("Helloworld");
        return;
    }
}