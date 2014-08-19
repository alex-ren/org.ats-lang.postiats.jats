using System;

namespace Postiats {
    public class CComp {
        static public int pats_add(int x1, int x2) {
            return x1 + x2;
        }

        static public int pats_sub(int x1, int x2) {
            return x1 - x2;
        }

        static public int pats_mul(int x1, int x2) {
            return x1 * x2;
        }

        static public int pats_div(int x1, int x2) {
            return x1 / x2;
        }

        static public bool pats_gt(int x1, int x2) {
            return x1 > x2;
        }

        static public bool pats_ge(int x1, int x2) {
            return x1 >= x2;
        }

        static public bool pats_lt(int x1, int x2) {
            return x1 < x2;
        }

        static public bool pats_lte(int x1, int x2) {
            return x1 <= x2;
        }

        static public bool pats_eq(int x1, int x2) {
            return x1 == x2;
        }

        static public bool pats_neq(int x1, int x2) {
            return x1 != x2;
        }

        static public int pats_neg(int x) {
            return -x;
        }

        // =======================================
        
        static public double pats_add(double x1, double x2) {
            return x1 + x2;
        }

        // =======================================
        
        static public void pats_print(string str) {
            Console.WriteLine(str);
        }
    }
}


