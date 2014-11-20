using System;
using System.Collections;
using System.Text;
using PAT.Common.Classes.Expressions.ExpressionClass;

using PAT.Lib;
//the namespace must be PAT.Lib, the class and method names can be arbitrary
//

/*
 * PStack contains the stacks for all the threads.
 *
 *
 */
namespace PAT.Lib
{
    public class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("begin testing AtomRefManager");
            AtomRefManager atom = new AtomRefManager(10);
            int index = atom.allocate(0);
            atom.setElement(index, 1);
            int r = (int)atom.getElement(index);
            Console.WriteLine("end testing ArrayRefManager");

            Console.WriteLine("begin testing ArrayRefManager");
            ArrayRefManager arr = new ArrayRefManager(10);
            int index2 = arr.allocate(2, 0);
            arr.setElement(index2, 1, 42);
            int r2 = (int)arr.getElement(index2, 1);
            Console.WriteLine("end testing ArrayRefManager");



        }
    }
}

