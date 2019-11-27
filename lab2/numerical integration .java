/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package benchmarking;

/**
 *
 * @author akohanim
 */

import java.util.stream.IntStream;


public class Benchmarking {

    interface FPFunction
  {
    double eval(double n);
  }
 
     public static double rectangularLeft(double a, double b, int n, FPFunction f)
  {
    return rectangular(a, b, n, f, 0);
  }
 
  public static double rectangularMidpoint(double a, double b, int n, FPFunction f)
  {
    return rectangular(a, b, n, f, 1);
  }
 
  public static double rectangularRight(double a, double b, int n, FPFunction f)
  {
    return rectangular(a, b, n, f, 2);
  }
    
    
 private static double rectangular(double a, double b, int n, FPFunction f, int mode)
  {
    double range = checkParamsGetRange(a, b, n);
    double modeOffset = (double)mode / 2.0;
    double nFloat = (double)n;
    double sum = 0.0;
    for (int i = 0; i < n; i++)
    {
      double x = a + range * ((double)i + modeOffset) / nFloat;
      sum += f.eval(x);
    }
    return sum * range / nFloat;
  }
 
  //streams 
  public static double rectangularStream(double a, double b, int n, FPFunction f, int mode)
  {
    double range = checkParamsGetRange(a, b, n);
    double modeOffset = (double)mode / 2.0;
    double nFloat = (double)n;
    
    
    double sum = IntStream.range (0,n)
            .mapToDouble(i -> a + range * ((double)i + modeOffset) / nFloat)
            .map(x -> f.eval(x))
            .reduce(0, (y, z) -> y+z);
        return sum * range / nFloat;
                 
  }
  
   public static double rectangularParallelStream(double a, double b, int n, FPFunction f, int mode)
  {
    double range = checkParamsGetRange(a, b, n);
    double modeOffset = (double)mode / 2.0;
    double nFloat = (double)n;
    
            
    double sum = IntStream.range (0,n)
            .parallel()
            .mapToDouble(i -> a + range * ((double)i + modeOffset) / nFloat)
            .map(x -> f.eval(x))
            .reduce(0, (y, z) -> y+z);
        return sum * range / nFloat;
                 
  }
 
 
  private static double checkParamsGetRange(double a, double b, int n)
  {
    if (n <= 0)
      throw new IllegalArgumentException("Invalid value of n");
    double range = b - a;
    if (range <= 0)
      throw new IllegalArgumentException("Invalid range");
    return range;
  }
 
 
  private static void testFunction(String fname, double a, double b, int n, FPFunction f, int mode, int type)
  {
    if (type == 1)
    {
    long start = System.nanoTime();
    System.out.println("Testing function \"" + fname + "\", a=" + a + ", b=" + b + ", n=" + n);
    System.out.println("rectangular forloop " + rectangular(a, b, n, f, mode));
    long finish = System.nanoTime();
    // Then you solve for the actual execution time
    long algTime =  finish - start;
    System.out.println("It takes "+ algTime +" nano seconds to execute this function");
    System.out.println();
    }
      
    else if (type == 2)
    {
    long start1 = System.nanoTime();
    System.out.println("Testing function \"" + fname + "\", a=" + a + ", b=" + b + ", n=" + n);
    System.out.println("rectangular Stream: " + rectangularStream(a, b, n, f, mode));
    long finish1 = System.nanoTime();
    // Then you solve for the actual execution time
    long algTime1 =  finish1 - start1;
    System.out.println("It takes "+ algTime1 +" nano seconds to execute this function");
    System.out.println();
    }
    
    else if (type == 3)
    {
    long start2 = System.nanoTime();
    System.out.println("Testing function \"" + fname + "\", a=" + a + ", b=" + b + ", n=" + n);
    System.out.println("rectangular parallel stream " + rectangularParallelStream(a, b, n, f, mode));
    long finish2 = System.nanoTime();
    // Then you solve for the actual execution time
    long algTime2 =  finish2 - start2;
    System.out.println("It takes "+ algTime2 +" nano seconds to execute this function");
    System.out.println();
    }
    
    
    return;
  }
  
  
  
  public static void main(String[] args)
  {
   for (int i= 1; i<20; i+=1)
   {
       //f(x) = 1/x dx
   testFunction("x", 1.0, 100.0, 1000000*i, new FPFunction() {
        public double eval(double n) {
          return 1.0 / n;
        }
      },1,1
    );
 
    testFunction("x", 1.0, 100.0, 1000000 *i, new FPFunction() {
        public double eval(double n) {
          return 1.0 / n;
        }
      },1,2
    );
 
    testFunction("x", 1.0, 100.0, 1000000 * i, new FPFunction() {
        public double eval(double n) {
          return 1.0 / n;
        }
      },1,3
    );
   
   }
    return;
  }
  
}
 