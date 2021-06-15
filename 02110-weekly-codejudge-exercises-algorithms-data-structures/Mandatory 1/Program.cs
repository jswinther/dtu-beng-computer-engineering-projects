using System;
using System.Linq;

namespace Mandatory_1
{
    class Program
    {
        static int[] int_arr;
        static void Main(string[] args)
        {
            int N = Int32.Parse(Console.ReadLine());
            string A = Console.ReadLine();
            string[] A_arr = A.Split(' ');
            int_arr = Array.ConvertAll<string, int>(A_arr, int.Parse);

            // find max.
            int longestSequence = findMax(0, N-1);
            Console.WriteLine(longestSequence);
        }


        static int findMax(int lower, int upper) {
            int max = 0, index = 0;
            for(int i = lower; i <= upper; i++) {
                if(int_arr[i] > max) {
                    max = int_arr[i];
                    index = i;
                }
            }
            if(max == 0) {
                return 0;
            }
            if(index == lower) {
                return 1 + findMax(index+1,upper);
            } else if (index == upper) {
                return 1 + findMax(lower,index-1);
            }                
                return 1 + Math.Max(findMax(lower, index-1), findMax(index+1, upper)); 
        }

    }
}
