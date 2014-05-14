using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using PAT.Lib;

namespace PAT.Lib
{

    public class FStackNode<T>
    {
        private T m_v;
        private FStackNode<T> m_tail;

        public static FStackNode<Object> createFromLinkedList(SysLinkedNode xs, ref int i)
        {
            if (xs == SysLinkedNode.nil())
            {
                i = 0;
                return FStackNode<Object>.nil();
            }
            else
            {
                SysLinkedNode xs_tail = xs.getTail();
                FStackNode<Object> tail = createFromLinkedList(xs_tail, ref i);
                i = i + 1;
                Object xs_head = xs.getValue();

                return FStackNode<Object>.cons(tail, xs_head);
            }
        }

        private FStackNode(T x, FStackNode<T> s)
        {
            m_v = x;
            m_tail = s;
        }

        public static FStackNode<T> nil()
        {
            return null;
        }

        public static FStackNode<T> cons(FStackNode<T> s, T x)
        {
            return new FStackNode<T>(x, s);
        }

        public T getValue()
        {
            return m_v;
        }

        public FStackNode<T> getNext()
        {
            return m_tail;
        }

        public T getAtPos(int pos)
        {
            FStackNode<T> node = this;
            while (pos > 0)
            {
                node = node.getNext();
                pos--;
            }

            return node.getValue();
        }

        public static bool isEmpty(FStackNode<T> s)
        {
            return null == s;
        }

        public override string ToString()
        {
            return "[" + encode() + "]";
        }

        private string encode()
        {
            FStackNode<T> s = this;
            String returnString = "";
            do
            {
                if (null != s.m_v)
                {
                    returnString += s.m_v.ToString() + ", ";
                }
                else
                {
                    returnString += "null" + ", ";
                }
                s = s.m_tail;
            } while (null != s);

            if (returnString.Length > 0)
            {
                returnString = returnString.Substring(0, returnString.Length - 2);
            }

            return returnString;
        }

        public string ExpressionID
        {
            get
            {
                return encode();
            }
        }

    }

}
