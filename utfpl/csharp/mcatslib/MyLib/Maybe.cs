
using System;
using System.Collections.Generic;
using System.Text;
using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

/*
 * Tuple is immutable object.
 *
 */

namespace PAT.Lib
{
    public class Maybe : ExpressionValue
    {
        private ExpressionValue m_v;
        private bool m_is_none;

        private Maybe(ExpressionValue v) {
            m_v = v;
            m_is_none = false;
        } 

        private Maybe() {
            m_v = null;
            m_is_none = true;
        } 

        static public Maybe some(ExpressionValue v) {
            return new Maybe(v);
        }

        static public bool is_none(Maybe m) {
            return m.m_is_none;
        }

        static public ExpressionValue unsome(Maybe m) {
            return m.m_v;
        }

        static public Maybe none() {
            return new Maybe();
        }

        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        ///        
        public override string ToString()
        {
            if (m_is_none) {
                return "none";
            } else {
                return "some:" + m_v.ToString();
            }
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
            get { 
                if (m_is_none) {
                    return "n";
                } else {
                    return "s:" + m_v.ExpressionID;
                }
            }
        }
    }
}





