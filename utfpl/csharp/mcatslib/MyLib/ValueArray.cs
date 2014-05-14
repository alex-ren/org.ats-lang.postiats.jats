using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;

using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

namespace PAT.Lib
{
    public class ValueArray : ExpressionValue
    {
        private Object[] m_arr;

        public ValueArray(int n)
        {
            m_arr = new Object[n];
        }

        private ValueArray(Object[] arr)
        {
            m_arr = new Object[arr.Length];
            for (int i = 0; i < arr.Length; i++)
            {
                m_arr[i] = arr[i];
            }                
        }

        // obj can be either integer or ExpressionValue
        public void set(int index, Object obj)
        {
            m_arr[index] = obj;
        }

        //public void set(int index, int obj)
        //{
        //    m_arr[index] = obj;
        //}

        public Object get(int index)
        {
            return m_arr[index];
        }

        private string encode()
        {
            String returnString = "";
            foreach (Object obj in m_arr)
            {
                if (null == obj)
                {
                    returnString += "null, ";
                }
                else
                {
                    returnString += obj.ToString() + ", ";
                }
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
        ///        
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
            return new ValueArray(m_arr);
        }

        /// <summary>
        /// Please implement this method to provide the compact string representation of the datatype
        /// </summary>
        /// <returns></returns>
        public override string ExpressionID
        {
            get
            {
                String returnString = "";
                foreach (Object obj in m_arr)
                {
                    if (null == obj)
                    {
                        returnString += ",null";
                    }
                    else if (obj is int)
                    {
                        byte[] bytes = BitConverter.GetBytes((int)obj);
                        returnString += "," + Convert.ToBase64String(bytes);
                    }
                    else
                    {
                        returnString += "," + ((ExpressionValue)obj).ExpressionID;
                    }
                }
                return returnString;
            }
        }
    }
}