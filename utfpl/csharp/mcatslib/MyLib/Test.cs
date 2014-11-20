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
            Console.WriteLine("begin");
            AtomRefManager man = new AtomRefManager(10);
            man.setElement(0, 3);
            Console.WriteLine("end");
        }
    }
}

