using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;

using PAT.Lib;

namespace LibTester
{
    class A
    {
    }
    
    class Program
    {
        static void Main(string[] args)
        {
            System.Console.WriteLine("hello world");
            IntHolder ih = new IntHolder(3);
            A a = new A();
            System.Console.WriteLine(a);

            //PStack ps = new PStack();
            //ps.addStack();
            //ps.newFrame(0);
            //ps.newFrame(0);
            //ps.push(0, 2);
            //ps.get(0, 0);
            //ps.push(0, 3);
            //System.Console.WriteLine(ps.ToString());
            //ps.get(0, 1);
            //ps.deleteFrame(0);
            //ps.push(0, 33);

            byte[] myBytes = new byte[5] { 66, 67, 68, 69, 70 };
            BitArray myBA3 = new BitArray(myBytes);
            Console.WriteLine(">" + Convert.ToBase64String(myBytes) + "<");

            Console.WriteLine("myBA3");
            Console.WriteLine("   Count:    {0}", myBA3.Count);
            Console.WriteLine("   Length:   {0}", myBA3.Length);
            Console.WriteLine("   Values:");
            PrintValues(myBA3, 8);
            Console.WriteLine("dd " + 3);

            Allocator allo = new Allocator(32);
            string str = allo.ToString();
            Console.WriteLine("len of " + str + "is " + str.Length);
            Console.WriteLine("1 to string is " + 1.ToString());


        }



        public static void PrintValues(IEnumerable myList, int myWidth )
        {
           int i = myWidth;
           foreach ( Object obj in myList )
           {
              if ( i <= 0 )
              {
                 i = myWidth;
                 Console.WriteLine();
              }
              i--;
              Console.Write( "{0,8}", obj );
           }
           Console.WriteLine();
        }
    }


}
