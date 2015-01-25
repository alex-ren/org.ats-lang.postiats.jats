using System;
using System.Collections.Generic;
using System.Text;
using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

/*
 * SysLinkedNode is a functional style linked list.
 *
 *
 */

namespace PAT.Lib
{
    public class SysLinkedNode : ExpressionValue
    {
        private Object m_v;
        private SysLinkedNode m_next;
        // private Guid m_id;
        //

        public SysLinkedNode(Object v, SysLinkedNode next)
        {
            m_v = v;
            m_next = next;
            // m_id = Guid.NewGuid();
        }


        public SysLinkedNode getTail() {
            return m_next;
        }
        
        public Object getValue() {
            return m_v;
        }

        public Object getAtPos(int pos)
        {
            SysLinkedNode node = this;
            while (pos > 0)
            {
                node = node.getTail();
                pos--;
            }

            return node.getValue();
        }

        // It's allowed that e is not in "this" list.
        public SysLinkedNode remove_element(Object e) {
            SysLinkedNode tail = m_next;
            if (e.Equals(m_v)) {
                return tail;
            }

            SysLinkedNode new_list = new SysLinkedNode(m_v, null);
            SysLinkedNode cur_list = new_list;

            while (null != tail) {
                if (e.Equals(tail.m_v)) {
                    cur_list.m_next = tail.m_next;
                    return new_list;
                } else {
                    SysLinkedNode new_tail = new SysLinkedNode(tail.m_v, null);
                    cur_list.m_next = new_tail;
                    cur_list = new_tail;
                    tail = tail.m_next;
                }
            }
            return this;  // element not found
        }

        public int length() {
            int n = 0;
            SysLinkedNode cur = this;
            while (null != cur) {
                ++n;
                cur = cur.m_next;
            }
            return n;
        }

        public static SysLinkedNode nil()
        {
            return null;
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

