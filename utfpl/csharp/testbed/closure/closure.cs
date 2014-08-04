
using System;

delegate int ClosureFunType1(int x, Object env);

class ClosureType1 {
    public ClosureFunType1 m_fun;
    public Object m_env;

    public ClosureType1(ClosureFunType1 fun, Object env) {
        m_fun = fun;
        m_env = env;
    }

    public int invoke(int x) {
        return m_fun(x, m_env);
    }
};

delegate int ClosureFunType2(int x, Object env);

class ClosureType2 {
    public ClosureFunType2 m_fun;
    public Object m_env;

    public ClosureType2(ClosureFunType2 fun, Object env) {
        m_fun = fun;
        m_env = env;
    }

    public int invoke(int x) {
        return m_fun(x, m_env);
    }
};

class ClosureTest {
    static void Main() {
        ClosureType1 closure = createClosure(3);
        Object oclosure = (Object)closure;
        ClosureType2 closure2 = (ClosureType2)oclosure;
        Console.WriteLine("Hello\n" + closure.invoke(2));
    }

    static int add(int x, Object env) {
        return x + (int)env;
    }

    static ClosureType1 createClosure(int x) {
        return new ClosureType1(add, x);
    }


}




