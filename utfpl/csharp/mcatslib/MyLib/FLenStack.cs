using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using PAT.Lib;

namespace PAT.Lib
{

    public class FLenStack<T>
    {
        private int m_len;
        private FStackNode<T> m_node;

        public FLenStack(int len, FStackNode<T> node)
        {
            m_len = len;
            m_node = node;
        }

        public static FLenStack<T> create()
        {
            return new FLenStack<T>(0, FStackNode<T>.nil());
        }

        public static FLenStack<Object> create(SysLinkedNode xs)
        {
            int i = 0;
            FStackNode<Object> ys = FStackNode<Object>.createFromLinkedList(xs, ref i);
            return new FLenStack<Object>(i, ys);
        }
        
        public static FLenStack<T> push(FLenStack<T> s, T x)
        {
            FStackNode<T> node = FStackNode<T>.cons(s.m_node, x);
            int len = s.m_len + 1;
            return new FLenStack<T>(len, node);
        }

        //public FLenStack<T> pop(FLenStack<T> s)
        //{
        //    FStackNode<T> node = s.m_node.getNext();
        //    int len = s.m_len - 1;
        //    return new FLenStack<T>(len, node);
        //}

        //public T top()
        //{
        //    return m_node.getValue();
        //}

        public T getFromTop(int pos)
        {
            FStackNode<T> s = m_node;

            while (pos > 0)
            {
                s = s.getNext();
                pos--;
            }
            return s.getValue();
        }

        public T getFromBottom(int pos)
        {
            return getFromTop(m_len -1 - pos);
        }

        public bool isEmpty()
        {
            return 0 == m_len;
        }

        public override string ToString()
        {
            return "[" + encode() + "]";
        }

        private string encode()
        {
            String returnString = "";
            FStackNode<T> s = m_node;
            while (null != s)
            {
                T v = s.getValue();
                if (null != v)
                {
                    returnString += v.ToString() + ", ";
                }
                else
                {
                    returnString += "null" + ", ";
                }
                s = s.getNext();
            };

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
