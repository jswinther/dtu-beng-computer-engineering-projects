using System;

namespace WrittenMandatory3Question3
{
    class Program
    {
        static int[] s = new int[100];
        static int[] Bing = new int[s.Length];
        static int[] Ding = new int[s.Length];
        static void Main(string[] args)
        {

            for ( int i = 0; i < s.Length;i++ ) {
                Bing[i] = int.MinValue;
                Ding[i] = int.MinValue;
            }

            Random randNum = new Random();
            for (int i = 1; i < s.Length; i++) {
                s[i] = randNum.Next(-1000, 1000);
            }
            var watch = System.Diagnostics.Stopwatch.StartNew();
            int t = OPT(Bing, Ding);
            watch.Stop();
            var elapsedMs = watch.ElapsedMilliseconds;
            System.Console.WriteLine(t + " cookies\n" + elapsedMs + " ms");
        }

        static int OPT(int[] Bing, int[] Ding) {
            return Math.Max(L(0, s.Length-1, Bing, Ding), L(1, s.Length-1, Bing, Ding));
        }

        static int L(int b, int i, int[] Bing, int[] Ding) {
            
            if (i == 0) {
                return 0;
            }  
            else if (b == 1 && i == 1) {
                return s[1];
            }
            else if (b == 0 && i == 1) {
                return -s[1];
            }     
            else if (b == 1 && i > 1) {
                if(Bing[i] == int.MinValue) {
                    Bing[i] = Math.Max(L(0, i-1, Bing, Ding), L(0, i-2, Bing, Ding) + s[i-1]) + s[i];
                }
                return Bing[i];
            }      
            else if (b == 0 && i > 1) {
                if(Ding[i] == int.MinValue) {
                    Ding[i] = Math.Max(L(1, i-1, Bing, Ding), L(1, i-2, Bing, Ding) - s[i-1]) - s[i]; 
                }
                return Ding[i];
                    
            }
            return 0;
        }

    }
}
