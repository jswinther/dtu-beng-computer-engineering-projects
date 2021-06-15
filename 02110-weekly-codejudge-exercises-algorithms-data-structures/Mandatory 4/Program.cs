using System;
using System.Collections;

namespace Mandatory_4
{
    class Program
    {
        static void Main(string[] args)
        {
            int N = Int32.Parse(Console.ReadLine());
            int[] inputArray = new int[N];
            int[] outputArray = new int[N];
            ArrayList studentCards = new ArrayList();

            for (int i = 0; i < N; i++)
            {
                int num = Int32.Parse(Console.ReadLine());
                studentCards.Add(num);
                studentCards.Sort();
                outputArray[i] = studentCards.IndexOf(num);
                System.Console.WriteLine(outputArray[i]+1);
            }
        }
    }
}
