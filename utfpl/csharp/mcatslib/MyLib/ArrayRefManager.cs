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
    /// Note: 
    /// </summary>
    public class ArrayRefManager : ExpressionValue
    {
        private int m_index;

        private ArrayList m_array;

        public ArrayRefManager(int n) {
            m_index = 0;
            m_array = new ArrayList(n);
        }

        private ArrayRefManager(int index, ArrayList array) {
            m_index = index;
            m_array = array;
        }

        public int allocate(int n, Object init) {
            // System.Console.WriteLine("allocate 001");
            AtomRefManager arr = new AtomRefManager(n);
            // System.Console.WriteLine("allocate 002");
            arr.initialize(n, init);
            // System.Console.WriteLine("allocate 003");

            int ret = m_index;
            // System.Console.WriteLine("allocate 004");
            m_array.Add(arr);
            // System.Console.WriteLine("allocate 005");
            
            ++m_index;
            return ret;
        }

        public Object getElement(int row, int col) {
            Object ret = ((AtomRefManager)m_array[row]).getElement(col);
            return ret;

        }

        public void setElement(int row, int col, Object v) {
            ((AtomRefManager)m_array[row]).setElement(col, v);
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
            ArrayList new_array = new ArrayList(m_array.Capacity);
            int len = m_array.Count;
            for (int i = 0; i < len; ++i)
            {
                new_array.Add(((AtomRefManager)m_array[i]).GetClone());
            }

            ArrayRefManager ret = new ArrayRefManager(m_index, new_array);
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













