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

        public SysLinkedNode insert_ordered(IComparable e) {
            if (this == s_nil) {
                return new SysLinkedNode(e, s_nil);
            }
            if (e.CompareTo(m_v) < 0) {
                return new SysLinkedNode(e, this);
            }
            SysLinkedNode new_list = new SysLinkedNode(m_v, s_nil);

            SysLinkedNode pre_node = new_list;

            SysLinkedNode cur = m_next;
            while (s_nil != cur) {
                if (e.CompareTo(cur.m_v) < 0) {
                    SysLinkedNode node = new SysLinkedNode(e, cur);
                    pre_node.m_next = node;
                    return new_list;
                } else {
                    SysLinkedNode node = new SysLinkedNode(cur.m_v, s_nil);
                    pre_node.m_next = node;
                    cur = cur.m_next;
                    pre_node = pre_node.m_next;
                }
            }

            pre_node.m_next = new SysLinkedNode(e, s_nil);
            return new_list;
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

        public SysLinkedNode merge_ordered(SysLinkedNode right) {
            // System.Console.WriteLine("left is " + this.ToString());
            // System.Console.WriteLine("right is " + right.ToString());
            SysLinkedNode left = this;
            if (left == s_nil) {
                return right;
            }
            if (right == s_nil) {
                return left;
            }

            SysLinkedNode new_list = null;
            IComparable lv = (IComparable)left.m_v;
            Object rv = right.m_v;

            if (lv.CompareTo(rv) < 0) {
                new_list = new SysLinkedNode(lv, s_nil);
                left = left.m_next;

            } else { // It's impossible that == 0
                new_list = new SysLinkedNode(rv, s_nil);
                right = right.m_next;
            }

            SysLinkedNode pre_node = new_list;

            while (left != s_nil && right != s_nil) {
                lv = (IComparable)left.m_v;
                rv = right.m_v;
                if (lv.CompareTo(rv) < 0) {
                    SysLinkedNode new_node = new SysLinkedNode(lv, s_nil);
                    pre_node.m_next = new_node;
                    left = left.m_next;
                } else { // It's impossible that == 0
                    SysLinkedNode new_node = new SysLinkedNode(lv, s_nil);
                    pre_node.m_next = new_node;
                    right = right.m_next;
                }
                pre_node = pre_node.m_next;
            }
            if (left == s_nil) {
                pre_node.m_next = right;
            } else {
                pre_node.m_next = left;
            }

            return new_list;
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

