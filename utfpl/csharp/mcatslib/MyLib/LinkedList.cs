using System;
using System.Collections.Generic;
using System.Text;
using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

namespace PAT.Lib
{
    public class LinkedList : ExpressionValue
    {

        public LinkedList()
        {
        }

        public SysLinkedNode list_nil()
        {
            return null;
        }

        public SysLinkedNode list_cons(Object header, SysLinkedNode tail)
        {
            return new SysLinkedNode(header, tail);
        }

        public Boolean list_is_nil(SysLinkedNode xs)
        {
            return (null == xs);
        }

        public Object list_get_header(SysLinkedNode xs)
        {
            return xs.getValue();
        }

        public SysLinkedNode list_get_tail(SysLinkedNode xs)
        {
            return xs.getTail();
        }

        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        ///        
        public override string ToString()
        {
            return "LinkedList";
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
            get { return this.ToString(); }
        }
    }
}
