using System;
using System.Collections.Generic;
using System.Text;
using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

/*
 * Tuple is immutable object.
 *
 */

namespace PAT.Lib
{
    public class Tuple : ExpressionValue
    {
        private Object [] m_members;

        public Tuple(int len) {
           m_members = new Object[len];
        } 

        public Tuple(Object [] members) {
           m_members = members;
        } 

        public void setElement(int index, Object ele)
        {
            m_members[index] = ele;
        }

        public Object getElement(int index)
        {
            return m_members[index];
        }

        private string getContent()
        {
            string ret = "(";
            foreach (Object ele in m_members)
            {
                if (ele != null) {
                    ret += ele.ToString();
                    ret += ",";
                } else {
                    ret += "null";
                    ret += ",";
                }
            }
            ret += ")";
            return ret;
        }

        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        ///        
        public override string ToString()
        {
            return getContent();
        }

        /// <summary>
        /// Please implement this method to return a deep clone of the current object
        /// </summary>
        /// <returns></returns>
        public override ExpressionValue GetClone()
        {
            return this;
        }

        /// <summary>
        /// Please implement this method to provide the compact string representation of the datatype
        /// </summary>
        /// <returns></returns>
        public override string ExpressionID
        {
            get { return getContent(); }
        }
    }
}

