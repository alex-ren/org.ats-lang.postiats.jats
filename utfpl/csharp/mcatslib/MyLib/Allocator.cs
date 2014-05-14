using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;

using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;

namespace PAT.Lib
{
    public class Allocator : ExpressionValue
    {
        private BitArray m_bits;
        private Byte[] m_bytes;

        public Allocator(int n)
        {
            m_bits = new BitArray(n);
            m_bits.SetAll(false);
            m_bytes = new Byte[(n - 1) / 8 + 1];
        }

        public Allocator()
        {
            int n = 10;
            m_bits = new BitArray(n);
            m_bits.SetAll(false);
            m_bytes = new Byte[(n - 1) / 8 + 1];
        }

        public Allocator(BitArray bits, Byte[] bytes)
        {
            this.m_bits = bits;
            this.m_bytes = bytes;
        }

        public int allocate()
        {
            int i = 0;
            foreach (bool bit in m_bits)
            {
                if (!bit)
                {
                    m_bits.Set(i, true);
                    return i;
                }
                else
                {
                    i++;
                }
            }
            return -1;
        }

        public void release(int i)
        {
            m_bits.Set(i, false);
        }


        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        ///        
        public override string ToString()
        {
            m_bits.CopyTo(m_bytes, 0);
            return Convert.ToBase64String(m_bytes);
        }

        /// <summary>
        /// Please implement this method to return a deep clone of the current object
        /// </summary>
        /// <returns></returns>
        public override ExpressionValue GetClone()
        {
            BitArray bits = new BitArray(m_bits);
            Byte[] bytes = new Byte[m_bytes.Length];
            return new Allocator(bits, bytes);
        }

        /// <summary>
        /// Please implement this method to provide the compact string representation of the datatype
        /// </summary>
        /// <returns></returns>
        public override string ExpressionID
        {
            get
            {
                m_bits.CopyTo(m_bytes, 0);
                return Convert.ToBase64String(m_bytes);
            }
        }
    }
}