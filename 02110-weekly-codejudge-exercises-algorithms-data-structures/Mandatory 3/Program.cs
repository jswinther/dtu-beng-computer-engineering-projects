using System;

namespace Mandatory_3
{
    class Program
    {
        static void Main(string[] args)
        {
            String[] numbers = Console.ReadLine().Split(" ");
            int G = Int32.Parse(numbers[0]);
            int H = Int32.Parse(numbers[1]);
            System.Console.WriteLine("G = " + G + " H = " + H);

            bool[,] graph = new bool[G, H];

            for (int i = 0; i < G; i++)
            {
                String[] strarray = Console.ReadLine().Split(" ");
                int[] int_arr = Array.ConvertAll<string, int>(strarray, int.Parse);
                for (int j = 1; j < int_arr.Length; j++)
                {
                    graph[i, int_arr[j]] = true;
                }
            }

            System.Console.WriteLine(maxHats(graph, G, H));



        }

        static bool isBipartiteGraph(bool[,] graph, int G, int H, int guest, bool[] visisted, int[] hatPair) 
        { 
            for (int hat = 0; hat < G; hat++) 
            { 
                if (graph[guest, hat] && !visisted[hat]) 
                { 
                    visisted[hat] = true;  
                    if (hatPair[hat] < 0 || isBipartiteGraph(graph, G, H, hatPair[hat], visisted, hatPair)) 
                    { 
                        hatPair[hat] = guest; 
                        return true; 
                    } 
                } 
            } 
            return false; 
        } 

        static int maxHats(bool[,] graph, int G, int H) 
        { 
            int[] hatPair = new int[G]; 
            int result = 0; 

            for(int i = 0; i < G; i++) {
                hatPair[i] = Int32.MinValue; 
            }
                
            for (int i = 0; i < H; i++) {  
                bool[] visited = new bool[G] ; 
                for(int j = 0; j < G; j++) 
                    visited[i] = false; 
                if (isBipartiteGraph(graph, G, H, i, visited, hatPair)) 
                    result++; 
            } 
            return result; 
        }
    }
}
