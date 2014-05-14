using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

namespace PAT.Lib
{
    public class MemHolder : ExpressionValue
    {
        private Object m_v;
        private Guid m_id;

        public Object getValue()
        {
            return m_v;
        }


        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        ///        
        public override string ToString()
        {
            return m_id.ToString();
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
            get { return m_id.ToString(); }
        }
    }
}



