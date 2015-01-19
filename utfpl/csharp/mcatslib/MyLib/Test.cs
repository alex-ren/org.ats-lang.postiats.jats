#define DEBUG
#define TRACE

using System;
using System.Collections;
using System.Diagnostics;
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
            // open debug output
            var tl = new System.Diagnostics.ConsoleTraceListener();
            System.Diagnostics.Debug.Listeners.Add ( tl );

            Console.WriteLine("begin testing AtomRefManager\n");
            AtomRefManager atom = new AtomRefManager(10);
            int index = atom.allocate(0);
            atom.setElement(index, 1);
            int r = (int)atom.getElement(index);
            Console.WriteLine("end testing ArrayRefManager\n");

            Console.WriteLine("begin testing ArrayRefManager\n");
            ArrayRefManager arr = new ArrayRefManager(10);
            int index2 = arr.allocate(2, 0);
            arr.setElement(index2, 1, 42);
            int r2 = (int)arr.getElement(index2, 1);
            Debug.Assert(r2 == 42, "r2 == 42 failed");


            int index3 = arr.allocate(3, 0);
            arr.setElement(index3, 1, 41);
            int r3 = (int)arr.getElement(index3, 1);
            Debug.Assert(r3 == 41, "r3 == 41 failed");

            Console.WriteLine(arr.ExpressionID);
            Console.WriteLine(arr.ToString());

            Console.WriteLine("end testing ArrayRefManager\n");

            Console.WriteLine("begin testing ViewManager\n");

            ViewManager vm = new ViewManager();

            Maybe opt_rec = vm.get(0, 0, 1, 1);
            Debug.Assert(!Maybe.is_none(opt_rec), "Fail to get lock.");
            vm = (ViewManager)vm.GetClone();
            Console.WriteLine(vm.ExpressionID);
            Console.WriteLine(vm.ToString());

            
            opt_rec = vm.get(0, 0, 1, 1);
            Debug.Assert(Maybe.is_none(opt_rec), "should not get lock.");
            vm = (ViewManager)vm.GetClone();
            Console.WriteLine(vm.ExpressionID);
            Console.WriteLine(vm.ToString());

            opt_rec = vm.get(0, 1, 1, 1);
            Debug.Assert(!Maybe.is_none(opt_rec), "Fail to get lock.");
            vm = (ViewManager)vm.GetClone();
            Console.WriteLine(vm.ExpressionID);
            Console.WriteLine(vm.ToString());
            Console.WriteLine("end testing ViewManager\n");


        }
    }
}

