
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.Drawing;
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
    public class MyRec: ExpressionValue
    {
        private Rectangle m_rec;

        public MyRec(Rectangle rec)
        {
            m_rec = rec;
        }

        public Rectangle getRec()
        {
            return m_rec;
        }

        /// <summary>
        /// Please implement this method to provide the string representation of the datatype
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
 
            return m_rec.ToString();
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
            get
            {
                string returnString = "(" + m_rec.Left + "," +
                                          m_rec.Top  + "," +
                                          m_rec.Width + "," +
                                          m_rec.Height + ")";
 
                return returnString;
            }
        }
    }

    public class RecComp : IComparer<Rectangle>
    {
        // Compares by Height, Length, and Width. 
        public int Compare(Rectangle x, Rectangle y)
        {
            if (x.Left.CompareTo(y.Left) != 0)
            {
                return x.Left.CompareTo(y.Left);
            }
            else if (x.Top.CompareTo(y.Top) != 0)
            {
                return x.Top.CompareTo(y.Top);
            }
            else if (x.Width.CompareTo(y.Width) != 0)
            {
                return x.Width.CompareTo(y.Width);
            }
            else if (x.Height.CompareTo(y.Height) != 0)
            {
                return x.Height.CompareTo(y.Height);
            }
            else
            {
                return 0;
            }
        }
    }

    public class ViewManager : ExpressionValue
    {
        private SortedList<Rectangle, int> m_views;

        public ViewManager() {
            m_views = new SortedList<Rectangle, int>(new RecComp());
        }

        public ViewManager(SortedList<Rectangle, int> views) {
            m_views = views;
        }

        public Maybe get(int x, int y, int width, int height) {
            Rectangle rec = new Rectangle(x, y, width, height);
            IEnumerator<KeyValuePair<Rectangle, int>> iter = m_views.GetEnumerator();

            foreach (KeyValuePair<Rectangle, int> p in m_views) {

                if (rec.IntersectsWith(p.Key)) {
                    return Maybe.none();
                } 
            }

            m_views.Add(rec, 0);

            return Maybe.some(new MyRec(rec));
        }


        public void put(Maybe m) {
            if (Maybe.is_none(m)) {
                return;
            }
            Rectangle t = ((MyRec)Maybe.unsome(m)).getRec();
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
             foreach (KeyValuePair<MyRec, int> p in m_views)
             {
                 returnString += p.Key.ToString() + "." + p.Value + ",";
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
             SortedList<Rectangle> nlst = new SortList<Rectangle>(m_views);
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
                 foreach (KeyValuePair p in m_views)
                 {
                     returnString += "(" + p.Key.Left + "," +
                                           p.Key.Top  + "," +
                                           p.Key.Width + "," +
                                           p.Key.Height + "," +
                                           p.Value + ")";
                          
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





