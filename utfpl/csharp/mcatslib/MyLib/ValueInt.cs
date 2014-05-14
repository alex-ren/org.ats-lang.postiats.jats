using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;

using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

namespace PAT.Lib
{
    public class ValueInt : ExpressionValue
    {
        private int m_content;

        public ValueInt()
        {
            m_content = 0;
        }

        public ValueInt(int n)
        {
            m_content = n;
        }

        public ValueInt(Object obj)
        {
            m_content = 1;
        }

        public int getValue()
        {
            return m_content;
        }
        

        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        ///        
        public override string ToString()
        {
            return m_content.ToString();
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
            get
            {
                byte[] bytes = BitConverter.GetBytes(m_content);
                return Convert.ToBase64String(bytes);
            }
        }
    }
}