using System;
using System.Linq;

namespace Mandatory_1
{
    class Program
    {
        static int N;
        static string[] str_arr;

        static int[,] max_scores;
        static void Main(string[] args)
        {
            N = Int32.Parse(Console.ReadLine());

            str_arr = new string[N];
            max_scores = new int[N,N];

            for (int i = 0; i < N; i++) {
                str_arr[i] = Console.ReadLine();
            }
            int num = OPT(0, 0, 0);
            System.Console.WriteLine(num < 0 ? "impossible" : ""+num);
        }

        static int OPT(int x, int y, int sum) {
            int p = sum;

            if(board(x, y) == 'P') {
                ++p;
            } 
            
            if (board(x,y) == 'X') {
                return -10000;
            }

            if(max_scores[x,y] < p) {
                max_scores[x,y] = p;
            } else if(max_scores[x,y] != 0) {
                return -10000;
            }
                
            if((x < N-1 && y < N-1)) {


                if(board(x+1,y) == 'X' && board(x, y+1) != 'X') {
                    if(max_scores[x, y+1] > sum) {
                        return -10000; 
                    }
                        return OPT(x, y+1,p);
                        
                }
                if(board(x+1,y) != 'X' && board(x, y+1) == 'X') {
                    if(max_scores[x+1, y] > sum) {
                        return -10000; 
                    }
                        return OPT(x+1, y, p);
                }
                

                if(max_scores[x, y+1] > sum && max_scores[x+1, y] > sum) {
                    return -10000; 
                } if (max_scores[x, y+1] > sum) {
                    return OPT(x+1, y,p);
                } if (max_scores[x+1, y] > sum) {
                    return OPT(x, y+1, p);
                }
                    return Math.Max(OPT(x+1,y,p), OPT(x, y+1,p));
            }
            if(x == N-1 && y == N-1) {
                return p;

            } else if(x == N-1) {
                if(board(x, y+1) == 'X' || max_scores[x, y+1] > sum) {
                    return -10000;
                } else {
                    return OPT(x, y+1, p);
                }

            } else if(y == N-1) {
                if(board(x+1, y) == 'X' || max_scores[x+1, y] > sum) {
                    return -10000;
                } else {
                    return OPT(x+1, y,p);
                }
            }
            
            return p;
        }
        

        static char board(int x, int y) {
            string str = str_arr[x];
            return str[y];
        }
      

    }

    
}
