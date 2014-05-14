using System;
using System.Collections.Generic;
using System.Text;

//the namespace must be PAT.Lib, the class and method names can be arbitrary
namespace PAT.Lib
{
    /// <summary>
    /// You can use static library in PAT model.
    /// All methods should be declared as public static.
    /// 
    /// The parameters must be of type "int", "bool", "int[]" or user defined data type
    /// The number of parameters can be 0 or many
    /// 
    /// The return type can be void, bool, int, int[] or user defined data type
    /// 
    /// The method name will be used directly in your model.
    /// e.g. call(max, 10, 2), call(dominate, 3, 2), call(amax, [1,3,5]),
    /// 
    /// Note: method names are case sensetive
    /// </summary>
    public class Example
    {
        public static Boolean negation(Boolean x)
        {
            return !x;
        }

        public static int neg(int x)
        {
            return -x;
        }

        public static int add(int x, int y)
        {
            return x + y;
        }

        public static int sub(int x, int y)
        {
            return x - y;
        }

        public static int mul(int x, int y)
        {
            return x * y;
        }

        public static int div(int x, int y)
        {
            return x * y;
        }

        public static bool gt(int x, int y)
        {
            return x > y;
        }

        public static bool gte(int x, int y)
        {
            return x >= y;
        }

        public static bool lt(int x, int y)
        {
            return x < y;
        }

        public static bool lte(int x, int y)
        {
            return x <= y;
        }

        public static bool eq(int x, int y)
        {
            return x == y;
        }

        //==========================================================
        //the following sections are the functions used by Mailbox 
        //==========================================================
        private static List<int[]> Matrix;

        //dominate(v,w) == CHOOSE x \in 1..7 : GT(x, v) /\ GT(x, w)
        public static int dominate(int v, int w)
        {
            for (int i = 1; i <= 7; i++)
            {
                if (matrix[i - 1][v - 1] == 1 && matrix[i - 1][w - 1] == 1)
                {
                    return i;
                }
            }
            return -1;
        }

        //GT(v,w) == (Edges[v][w]=1)
        public static bool mailorder(int v, int w)
        {
            return matrix[v - 1][w - 1] == 1;
        }

        private static List<int[]> matrix
        {
            get
            {
                if (Matrix == null)
                {
                    Matrix = new List<int[]>(7);
                    Matrix.Add(new int[] { 0, 0, 1, 1, 1, 0, 0 });
                    Matrix.Add(new int[] { 1, 0, 1, 0, 0, 0, 1 });
                    Matrix.Add(new int[] { 0, 0, 0, 0, 1, 1, 1 });
                    Matrix.Add(new int[] { 0, 1, 1, 0, 0, 1, 0 });
                    Matrix.Add(new int[] { 0, 1, 0, 1, 0, 0, 1 });
                    Matrix.Add(new int[] { 1, 1, 0, 0, 1, 0, 0 });
                    Matrix.Add(new int[] { 1, 0, 0, 1, 0, 1, 0 });
                }
                return Matrix;
            }
        }

        //==========================================================
        //the following sections are the functions used by Bakery  
        //The maximum value of the array will be returned.
        //==========================================================
        public static int amax(int[] values)
        {
            int max = values[0];
            foreach (int v in values)
            {
                if (max < v)
                {
                    max = v;
                }
            }
            return max;
        }

        public static int asum1(int[] values, int startingIndex)
        {
            int sum = 0;
            int dimention = (int)Math.Sqrt(values.Length);
            for (int i = 0; i < dimention; i++)
            {
                sum += values[startingIndex + i];
            }
            return sum;
        }

        public static int asum2(int[] values, int startingIndex)
        {
            int sum = 0;
            int dimention = (int)Math.Pow(values.Length, 1.0 / 3.0); ;
            for (int i = 0; i < dimention; i++)
            {
                sum += values[startingIndex + i];
            }
            return sum;
        }

        /// <summary>
        /// Multi-lift example to check whether life i can make a move on the given level and direction
        /// </summary>
        /// <param name="level"></param>
        /// <param name="direction"></param>
        /// <param name="i"></param>
        /// <param name="NoOfFloors"></param>
        /// <param name="intrequests"></param>
        /// <param name="extrequestsUP"></param>
        /// <param name="extrequestsDOWN"></param>
        /// <returns></returns>
        public static bool CheckIfToMove(int level, int direction, int i, int NoOfFloors, int[] intrequests, int[] extrequestsUP, int[] extrequestsDOWN)
        {
            int Counter = level + direction;
            while (Counter >= 0 && Counter < NoOfFloors)
            {
                if (extrequestsUP[Counter] != 0 || extrequestsDOWN[Counter] != 0 || intrequests[i * NoOfFloors + Counter] != 0)
                {
                    return true;
                }
                else
                {
                    Counter = Counter + direction;
                }
            }
            return false;
        }

        public static bool CheckIfToMovePRTS(int level, int direction, int i, int NoOfFloors, int[] intrequests, int[] extrequestsUP, int[] extrequestsDOWN)
        {
            int Counter = level + direction;
            while (Counter >= 0 && Counter < NoOfFloors)
            {
                if (extrequestsUP[i * NoOfFloors + Counter] != 0 || extrequestsDOWN[i * NoOfFloors + Counter] != 0 || intrequests[i * NoOfFloors + Counter] != 0)
                {
                    return true;
                }
                else
                {
                    Counter = Counter + direction;
                }
            }
            return false;
        }

        public static int assignUP(int pos, int NoOfFloors, int[] direction, int[] level, int[] interquests)
        {
            int i = 0;
            int distance;

            int MinDistance = 2 * NoOfFloors;
            int assign = -1;

            while (i < level.Length)
            {

                if (direction[i] == -1)
                {
                    int j = 0;
                    while (j < pos)
                    {
                        if (interquests[i * NoOfFloors + j] == 1) break;
                        j++;
                    }
                    distance = Math.Abs(level[i] - j) + Math.Abs(pos - j);

                }
                else if (level[i] > pos)
                {
                    int j = NoOfFloors - 1;
                    while (j > pos)
                    {
                        if (interquests[i * NoOfFloors + j] == 1) break;
                        j--;
                    }
                    distance = (j - level[i]) + j - pos;
                }
                else
                {
                    distance = pos - level[i];
                }

                if (distance >= MinDistance)
                {
                    i++;
                    continue;
                }
                MinDistance = distance;
                assign = i;
                i++;
            }

            return (assign * NoOfFloors);
        }

        public static int assignDOWN(int pos, int NoOfFloors, int[] direction, int[] level, int[] interquests)
        {
            int i = 0;
            int distance;

            int MinDistance = 2 * NoOfFloors;
            int assign = -1;

            while (i < level.Length)
            {

                if (direction[i] == 1)
                {
                    int j = NoOfFloors - 1;
                    while (j > pos)
                    {
                        if (interquests[i * NoOfFloors + j] == 1) break;
                        j--;
                    }
                    distance = Math.Abs(j - level[i]) + Math.Abs(j - pos);
                }
                else if (level[i] < pos)
                {
                    int j = 0;
                    while (j < pos)
                    {
                        if (interquests[i * NoOfFloors + j] == 1) break;
                        j++;
                    }
                    distance = (level[i] - j) + (pos - j);
                }
                else
                {
                    distance = level[i] - pos;
                }

                if (distance >= MinDistance)
                {
                    i++;
                    continue;
                }
                MinDistance = distance;
                assign = i;
                i++;
            }

            return (assign * NoOfFloors);

        }

        public static int assignUP_Simple(int[] extrequestsUP, int pos, int[] Level)
        {
            if (extrequestsUP[0] == -1 && extrequestsUP[1] == -1)
            {
                if (Math.Abs(Level[0] - pos) <= Math.Abs(Level[1] - pos))
                { return 0; }
                else return 1;
            }
            else if (extrequestsUP[0] == -1)
            { return 0; }
            else return 1;
        }

        public static int assignDOWN_Simple(int[] extrequestsDOWN, int pos, int[] Level)
        {
            if (extrequestsDOWN[0] == -1 && extrequestsDOWN[1] == -1)
            {
                if (Math.Abs(Level[0] - pos) <= Math.Abs(Level[1] - pos))
                { return 0; }
                else return 1;
            }
            else if (extrequestsDOWN[0] == -1)
            { return 0; }
            else return 1;
        }

        public static int assignDOWN_Simple2(int[] ExterRequests, int pos, int[] Level)
        {
            if (ExterRequests[0] == -1 && ExterRequests[1] != -1) return 0;
            else if (ExterRequests[0] != -1 && ExterRequests[1] == -1) return 1;
            else
            {
                if (Level[0] >= pos && Level[1] >= pos)
                {
                    if ((Level[0] - pos) <= (Level[1] - pos))
                    { return 0; }
                    else return 1;
                }
                else if (Level[0] >= pos)
                { return 0; }
                else return 1;
            }
        }

        public static int synchronize_Wait3(int i, int[] ExterRequests, int[] intrequests, int[] Level)
        {

            if (ExterRequests[1 - i] + 3 <= Level[1 - i] && intrequests[1 - i] + 3 <= Level[1 - i])
                return 3;
            else
                return Math.Min((Level[1 - i] - intrequests[1 - i]), (Level[1 - i] - ExterRequests[1 - i]));

        }

        public static int synchronize_Wait1(int i, int[] ExterRequests, int[] intrequests, int[] Level)
        {

            if (ExterRequests[1 - i] + 1 <= Level[1 - i] && intrequests[1 - i] + 1 <= Level[1 - i])
                return 1;
            else
                return Math.Min((Level[1 - i] - intrequests[1 - i]), (Level[1 - i] - ExterRequests[1 - i]));

        }

        /// <summary>
        /// Real-time Multi-lift example. to get the destimation from external request
        /// </summary>
        /// <param name="externalRequests"></param>
        /// <param name="floor"></param>
        /// <param name="direction"></param>
        /// <param name="destination"></param>
        /// <param name="NoOfFloors"></param>
        /// <returns></returns>
        public static int GetNextDestinationFromExternalQ(int[] externalRequests, int floor, int direction, int destination, int NoOfFloors)
        {
            int index = floor;
            if (destination != -1)
            {
                while (((index < NoOfFloors) && (index >= 0)) && (index != destination))
                {
                    if (externalRequests[index] == direction)
                    {
                        return index;
                    }
                    index += direction;
                }
                return destination;
            }
            while ((index < NoOfFloors) && (index >= 0))
            {
                if (externalRequests[index] == direction)
                {
                    return index;
                }
                index += direction;
            }
            for (index = floor; (index < NoOfFloors) && (index >= 0); index -= direction)
            {
                if (externalRequests[index] == direction)
                {
                    return index;
                }
            }
            return -1;
        }

        /// <summary>
        /// Real-time Multi-lift example. to get the destimation from internal request
        /// </summary>
        /// <param name="externalRequests"></param>
        /// <param name="floor"></param>
        /// <param name="direction"></param>
        /// <param name="destination"></param>
        /// <param name="NoOfFloors"></param>
        /// <returns></returns>
        public static int GetNextDestinationFromInternalQ(int[] internalRequests, int floor, int direction, int NoOfFloors, int i)
        {
            int num;
            for (num = floor; (num < NoOfFloors) && (num >= 0); num += direction)
            {
                if (internalRequests[num + (NoOfFloors * i)] == 1)
                {
                    return num;
                }
            }
            for (num = floor; (num < NoOfFloors) && (num >= 0); num -= direction)
            {
                if (internalRequests[num + (NoOfFloors * i)] == 1)
                {
                    return num;
                }
            }
            return -1;
        }

    }
}
