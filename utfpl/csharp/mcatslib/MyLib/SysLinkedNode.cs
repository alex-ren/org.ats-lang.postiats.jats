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

        public static SysLinkedNode s_nil = new SysLinkedNode(null, null);
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
        // This is functional style.
        public SysLinkedNode remove_element(Object e) {
            // empty list
            if (this == s_nil) {
                return this;
            }

            SysLinkedNode tail = m_next;
            if (e.Equals(m_v)) {
                return tail;
            }

            SysLinkedNode new_list = new SysLinkedNode(m_v, s_nil);
            SysLinkedNode cur_list = new_list;

            while (s_nil != tail) {
                if (e.Equals(tail.m_v)) {
                    cur_list.m_next = tail.m_next;
                    return new_list;
                } else {
                    SysLinkedNode new_tail = new SysLinkedNode(tail.m_v, s_nil);
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
            while (s_nil != cur) {
                ++n;
                cur = cur.m_next;
            }
            return n;
        }

        public SysLinkedNode revappend(SysLinkedNode ys) {
            SysLinkedNode cur = this;

            while (ys != s_nil) {
                cur = new SysLinkedNode(ys.m_v, cur);
                ys = ys.m_next;
            }

            return cur;
        }

        private string getContent()
        {
            string ret = "";
            SysLinkedNode link = this;
            while (s_nil != link)
            {
                if (null != link.m_v) {
                    ret += link.m_v.ToString();
                } else {
                    ret += "null";
                }

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

