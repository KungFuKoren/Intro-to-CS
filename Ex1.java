package Againatit;

public class Ex1 {

	public static void main(String[] args) {
		// 209271535
				
				long startTime = System.nanoTime();
				System.out.println("the MCD is - "+denominator(9699690,30808063,87215586));
				long endTime   = System.nanoTime();
				double totalTime = endTime - startTime;
				System.out.println("runtime - " + totalTime*0.001 + " ms");

			}
			// creating a method for calculating the MCD - receives 3 longs and returns a long	
			public static long denominator(long a, long b, long c) {
				// checks which number is the largest
			    long max = Math.max(Math.max(a, b), c);
			    // checks to see if only natural numbers are entered
			   if(a <= 0 || b <= 0 || c <= 0) {
				   // returns -1 if the input isn't valid
				   return -1;		  
			   }
			   // if only natural numbers are submitted 
			   else {
				   // a loop from that runs from the largest number until the MCD
				   //(initially I had a condition that limits to a*b*c but it was faulty with large numbers) 
				   // therefor I had to remove it in order for the for loop to perform
			    for (long i = max; ; i += max) {
			    	// if statement that checks if the current number is divisible by all 3 inputs
			    	// if it does , it returns the number , if not the number will be added the max number to itself
			        if (i % a == 0 && i % b == 0 && i % c == 0) {
			        	//once we found the MCD the loop will end and returns the MCD
			            return i;
			        }
			    }
			 }
			}
		

		    }
		

	


