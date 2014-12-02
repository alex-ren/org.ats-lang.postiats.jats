
using System;
using System.Collections;
using System.Collections.Generic;
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

    /// <summary>
    /// The math library that can be used in your model.
    /// all methods should be declared as public static.
    /// 
    /// The parameters must be of type "int", or "int array"
    /// The number of parameters can be 0 or many
    /// 
    /// The return type can be bool, int or int[] only.
    /// 
    /// The method name will be used directly in your model.
    /// e.g. call(max, 10, 2), call(dominate, 3, 2), call(amax, [1,3,5]),
    /// 
    /// Note: method names are case sensetive
    /// </summary>
    public class ViewManager : ExpressionValue
    {
        private List<Tuple> m_views;

        public ViewManager() {
            m_views = new List<Tuple>();
        }

        public ViewManager(List<Tuple> views) {
            m_views = views;
        }

        public Maybe get(int x, int y, int xi, int yi) {
            foreach (Tuple t in m_views) {
                int tx = (int)t.getElement(0);
                int ty = (int)t.getElement(1);
                int txi = (int)t.getElement(2);
                int tyi = (int)t.getElement(3);
                bool cmp = include(x, y, xi, yi, tx, ty, txi, tyi);
                if (true == cmp) {
                    return Maybe.none();
                } 
            }

            Tuple t0 = new Tuple(4);
            t0.setElement(0, x);
            t0.setElement(1, y);
            t0.setElement(2, xi);
            t0.setElement(3, yi);

            m_views.Add(t0);

            return Maybe.some(t0);
        }


        // Check 1 is included in 2.
        private bool include(int x1
                           , int y1
                           , int xi1
                           , int yi1
                           , int x2
                           , int y2
                           , int xi2
                           , int yi2
                ) {
            return true;
        }
        
        public void put(Tuple t) {
            bool ret = m_views.Remove(t);
            if (false == ret) {
                throw new PAT.Common.Classes.Expressions.ExpressionClass.RuntimeException(
                    "Return an unknown view!");
            }
            return;
        }

         /// <summary>
         /// Please implement this method to provide the string representation of the datatype
         /// </summary>
         /// <returns></returns>
         public override string ToString()
         {
 
             String returnString = "";
             foreach (Tuple t in m_views)
             {
                 returnString += t.ToString() + ", ";
             }
 
             if (returnString.Length > 0)
             {
                 returnString = returnString.Substring(0, returnString.Length - 2);
             }
 
             return "[" + returnString + "]";
 
         }
 
 
         /// <summary>
         /// Please implement this method to return a deep clone of the current object
         /// </summary>
         /// <returns></returns>
         public override ExpressionValue GetClone()
         {
             List<Tuple> nlst = new List<Tuple>(m_views);
             return new ViewManager(nlst);
         }
 
         /// <summary>
         /// Please implement this method to provide the compact string representation of the datatype
         /// </summary>
         /// <returns></returns>
         public override string ExpressionID
         {
             get
             {
                 String returnString = "";
                 foreach (Tuple t in m_views)
                 {
                     returnString += t.ExpressionID + ", ";
                 }
 
                 if (returnString.Length > 0)
                 {
                     returnString = returnString.Substring(0, returnString.Length - 2);
                 }
 
                 return "[" + returnString + "]";
 
             }
         }
    }
}





