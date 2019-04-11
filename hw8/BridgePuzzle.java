/*
 * task from Codewars
 * link https://www.codewars.com/kata/56f6380a690784f96e00045d
 * 
 */

package hw8;

import java.util.stream.IntStream;

public class BridgePuzzle {
    boolean statement1(int s) {
      return streamAdd(s).noneMatch(addition -> isPrime(addition) && isPrime(s - addition));
    }

    private static boolean isPrime(int addition) {
      return streamMult(addition).noneMatch(multiplier -> addition % multiplier == 0);
    }
    
    boolean statement2(int p) {
      return streamMult(p)
              .filter(n -> p % n == 0)
              .filter(n -> statement1(n + p / n))
              .count() == 1;
    }

    boolean statement3(int s) {
      return streamAdd(s)
              .filter(n -> statement2(n * (s - n)))
              .count() == 1;
    }
    
    boolean isSolution(int a, int b) {
      return statement1(a + b) && statement2(a * b) && statement3(a + b);
    }
    
    private static IntStream streamAdd(int s) {
      return IntStream.rangeClosed(2, s / 2);
    }
    
    private static IntStream streamMult(int p) {
      return IntStream.rangeClosed(2, (int) Math.sqrt(p));
    }
}