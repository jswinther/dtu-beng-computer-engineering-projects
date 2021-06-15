using System;

namespace CodeJudge
{
    class Program
    {
        static void Main(string[] args)
        {

            string firstLine = Console.ReadLine();
            string secondLine = Console.ReadLine();


            string[] strarray = secondLine.Split(' ');
            int[] int_arr = Array.ConvertAll<string, int>(strarray, int.Parse);

            for(int i = int_arr.Length - 1; i >= 0; i--) {
                Console.Write(int_arr[i] + " ");
            }
        }
    }
}
