using System;
using System.Collections.Generic;
using System.Text;
using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

/*
 *
 */

namespace PAT.Lib
{
    public class MyString : ExpressionValue {
        public String m_str;

        public MyString(String str) {
            m_str = str;
        }

    }

    public class MyLibObj : ExpressionValue
    {

        public MyLibObj()
        {
        }

        public Object castvwtp0(Object input) {
            return input;
        }

        // String Operation
        //
        public MyString newString() {
            return new MyString("abc");
        }

        public void mc_print(Object obj) {
            System.Console.WriteLine(obj.ToString());
        }

        public void print_list(int x, SysLinkedNode xs) {
            System.Console.WriteLine(x + " " + xs.ToString());
            return;
        }

        public void showString(MyString str) {
            System.Console.WriteLine(str.m_str);
        }

        // Tuple Operation
        //
        public Tuple createTuple(int len) {
            // System.Console.WriteLine("aaaaaaaaaa");
            Tuple ret = new Tuple(len);
            // System.Console.WriteLine("bbbbbbbbbb");

            return ret;
        }

        public void setTupleElement(Tuple tup, int index, Object ele)
        {
            tup.setElement(index, ele);
        }

        public Tuple createTuple2(Object e1, Object e2) {
            Object [] arr = {e1, e2};
            return new Tuple(arr);
        }

        public Object getTupleElement(Tuple tup, int index)
        {
            return tup.getElement(index);
        }

        // List Operation
        //
        public SysLinkedNode list_nil()
        {
            return SysLinkedNode.s_nil;
        }

        public SysLinkedNode list_cons(Object header, SysLinkedNode tail)
        {
            // System.Console.WriteLine("eeeeeeeeeeeeeee header is " + header);
            return new SysLinkedNode(header, tail);
        }

        public Boolean list_is_nil(SysLinkedNode xs)
        {
            return (SysLinkedNode.s_nil == xs);
        }

        public Object list_get_header(SysLinkedNode xs)
        {
            return xs.getValue();
        }

        // Pre: xs != null
        public Object list_get_element(SysLinkedNode xs, int n)
        {
            return xs.getAtPos(n);
        }

        public SysLinkedNode list_get_tail(SysLinkedNode xs)
        {
            return xs.getTail();
        }

        // Pre: xs != null
        public SysLinkedNode list_remove_element(SysLinkedNode xs, Object e) {
            return xs.remove_element(e);
        }

        // Pre: e not in the list
        public SysLinkedNode list_insert_ordered(Object e, SysLinkedNode xs) {
            return xs.insert_ordered((IComparable)e);
        }

        // Pre: no common element in both lists
        public SysLinkedNode list_merge_ordered(SysLinkedNode xs, SysLinkedNode ys) {
            return xs.merge_ordered(ys);
        }

        public int list_length(SysLinkedNode xs) {
            return xs.length();
        }

        public SysLinkedNode list_revappend(SysLinkedNode xs, SysLinkedNode ys) {
            return xs.revappend(ys);
        }

        /* *************** ***************** */

        public bool is_none(Maybe m) {
            return Maybe.is_none(m);
        }

        static public ExpressionValue unsome(Maybe m) {
            return Maybe.unsome(m);
        }

        /* *************** ***************** */

        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        ///        
        public override string ToString()
        {
            return "MyLibObj";
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
