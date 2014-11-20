using System;
using System.Collections;
using System.Text;
using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;
//the namespace must be PAT.Lib, the class and method names can be arbitrary
//

/*
 * PStack contains the stacks for all the threads.
 *
 *
 */
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
    public class AtomRefManager : ExpressionValue
    {
        private int m_index;

        private ArrayList m_array;

        public AtomRefManager(int n) {
            m_index = 0;
            m_array = new ArrayList(n);
        }

        private AtomRefManager(int index, ArrayList array) {
            m_index = index;
            m_array = array;
        }

        public int allocate() {
            int ret = m_index;
            ++m_index;
            return ret;
        }

        public Object getElement(int index) {
            return m_array[index];
        }

        public void setElement(int index, int v) {
            System.Console.WriteLine("size is " + m_array.Count);

            System.Console.WriteLine("eeeeeeeeee" + index);
            m_array[index] = "ddd";
            System.Console.WriteLine("fffffffffffff");
            return;
        }

        private string encode()
        {
            String returnString = "";
            foreach (Object v in m_array)
            {
                returnString += v.ToString() + ", ";
            }

            if (returnString.Length > 0)
            {
                returnString = returnString.Substring(0, returnString.Length - 2);
            }

            return returnString;
        }

        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return "[" + encode() + "]";
        }


        /// <summary>
        /// Please implement this method to return a deep clone of the current object
        /// </summary>
        /// <returns></returns>
        public override ExpressionValue GetClone()
        {
            // todo
            // Currently, I assume each element in the array is immutable.
            ArrayList new_array = new ArrayList(m_array);
            AtomRefManager ret = new AtomRefManager(m_index, new_array);
            return ret;
        }

        /// <summary>
        /// Please implement this method to provide the compact string representation of the datatype
        /// </summary>
        /// <returns></returns>
        public override string ExpressionID
        {
            get
            {
                return encode();
            }
        }
    }
}












