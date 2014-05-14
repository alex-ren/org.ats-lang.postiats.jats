using System;
using System.Collections.Generic;
using System.Text;
using PAT.Common.Classes.Expressions.ExpressionClass;

//the namespace must be PAT.Lib, the class and method names can be arbitrary
namespace PAT.Lib 
{
    /// <summary>
    /// The math library that can be used in your model.
    /// all methods should be declared as public static.
    /// 
    /// The parameters must be of type "int", or "int array"
    /// The number of parameters can be 0 or many
    /// 
    /// The return type can be bool, int or int[] only.
    /// 
    /// The method name will be used directly in your model.
    /// e.g. call(max, 10, 2), call(dominate, 3, 2), call(amax, [1,3,5]),
    /// 
    /// Note: method names are case sensetive
    /// </summary>
    public class IntHolder: ExpressionValue
    {

        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        private int m_v;

        public IntHolder(int v)
        {
            m_v = v;
        }

        public override string ToString()
        {
            return m_v.ToString(); 
        }

        public int get()
        {
            return m_v;
        }

        public void set(int v)
        {
            m_v = v;
            return;
        }

        /// <summary>
        /// Please implement this method to return a deep clone of the current object
        /// </summary>
        /// <returns></returns>
        public override ExpressionValue GetClone()
        {
            return new IntHolder(m_v);
        }


        /// <summary>
        /// Please implement this method to provide the compact string representation of the datatype
        /// </summary>
        /// <returns></returns>
        public override string ExpressionID
        {
            get { return m_v.ToString(); }
        }

        static public IntHolder getInt()
        {
            return new IntHolder(99);
        }

    }

    //public class Monitor : ExpressionValue
    //{


    //    /// <summary>
    //    /// Please implement this method to provide the string representation of the datatype
    //    /// </summary>
    //    /// <returns></returns>
    //    public int m_mutex;
    //    public Monitor()
    //    {
    //        m_mutex = 0;
    //    }

    //    public void Wait(int tid)
    //    {
    //        // todo
    //        // put tid into waiting queue

    //    }
    //    public bool CanGo(int tid)
    //    {
    //        return false;
    //    }

    //    public override string ToString()
    //    {
    //        return "Monitor";
    //    }

    //    /// <summary>
    //    /// Please implement this method to return a deep clone of the current object
    //    /// </summary>
    //    /// <returns></returns>
    //    public override ExpressionValue GetClone()
    //    {
    //        return new Monitor();
    //    }
            
    //    /// <summary>
    //    /// Please implement this method to provide the compact string representation of the datatype
    //    /// </summary>
    //    /// <returns></returns>
    //    public override string ExpressionID
    //    {
    //        get { return "Monitor"; }
    //    }

    //}

    //public class NoMoreThreadException : System.Exception
    //{
    //    public NoMoreThreadException()
    //    {
    //    }

    //    public override string ToString()
    //    {
    //        return "No more thread";
    //    }
    //}

    //public class IATSRuntimeLib
    //{
    //    public static int GetTid(int[] threads_stat)
    //    {
    //        int i = 0;
    //        for (; i < threads_stat.Length; i++)
    //        {
    //            if (threads_stat[i] == 0)
    //            {
    //                threads_stat[i] = 1;
    //                break;
    //            }
    //        }

    //        if (i == threads_stat.Length)
    //        {
    //            throw new NoMoreThreadException();
    //        }
    //        return i;

    //    }
    //}

}
