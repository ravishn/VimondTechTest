package com.vimond.techtest.fizzbuzz;

/**
 * Class with implementation of FizzBuzz
 * @author Ravish
 *
 */
public class FizzBuzz {
	
	/**
	 * main method to display numbers from 1 through 100 with FizzBuzz logic
	 * @param args
	 */
    public static void main(String[] args) {
        determineFizzBuzz(100);
    }

    /**
     * method to determine 
     * 1. multiples of 3 and return Fizz
     * 2. multiples of 5 and return Buzz
     * 3. multiples of both 3 and 5 return FizzBuzz
     * @param range
     */
	private static void determineFizzBuzz(int range) {
		// loop through the range starting from 1
		for (int i = 1; i <= range; i++) {
			// initialise fizzOrBuzz flag to false
            boolean fizzOrBuzz = false;
            
            // return Fizz if i is divisible by 3
            if (i % 3 == 0) {
                System.out.print("Fizz");
                // set fizzOrBuzz flag to true
                fizzOrBuzz = true;
            }
          // return Buzz if i is divisible by 5
            if (i % 5 == 0) {
                System.out.print("Buzz");
              // set fizzOrBuzz flag to true
                fizzOrBuzz = true;
            }

            // if fizzOrBuzz flag is true, it will return Fizz or Buzz or FizzBuzz
            if (fizzOrBuzz) {
                System.out.println();
            } else {
            	// print value of i if fizzOrBuzz flag is false or if i is neither divisible by 3 nor 5
                System.out.println(String.valueOf(i));
            } // end if
        } // end for
	} // end determineFizzBuzz()
} // end FizzBuzz class
