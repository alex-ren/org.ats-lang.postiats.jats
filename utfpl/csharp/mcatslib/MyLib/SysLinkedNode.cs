using System;
using System.Collections.Generic;
using System.Text;
using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

namespace PAT.Lib
{
    public class SysLinkedNode : ExpressionValue
    {
        private Object m_v;
        private SysLinkedNode m_next;
        private Guid m_id;

        public static SysLinkedNode nil()
        {
            return null;
        }

        public SysLinkedNode(Object v, SysLinkedNode next)
        {
            m_v = v;
            m_next = next;
            m_id = Guid.NewGuid();
        }

        public Object getValue()
        {
            return m_v;
        }

        public SysLinkedNode getTail()
        {
            return m_next;
        }

        private string getContent()
        {
            string ret = "";
            SysLinkedNode link = this;
            while (null != link)
            {
                ret += link.m_v.ToString();
                ret += "-";
                link = link.m_next;
            }
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

