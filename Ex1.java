package Againatit;


public class Ex1 {

	public static void main(String[] args) {
// three inputs to check
				      long a = 9699690; 
				      long b = 30808063; 
				      long c = 87215586;
				    
// checks the starting time 			      
						long startTime   = System.nanoTime();
// a condition - to see all inputs are natural numbers and not equal or smaller than  "0" 
// if they are - prints Invalid number , if they aren't the else statement will continue and will enter the MCD method 						
						if(a <= 0 || b <= 0 || c <=0) {
							System.out.println("Invalid input");
						}
						else {
				      System.out.println("The minimal common denominator is "+ MCD(a , b ,c));
						}
// checks the final time after the method ended				      
						long endTime   = System.nanoTime();
// calculates the time that the method was running						
						double totalTime = endTime - startTime;
// converts nanoseconds into milliseconds						
						System.out.println("runtime - " + totalTime*0.001 + " ms");

				      
				      
				      
				   }
// a helping method to check the GCD between two number using the euclidean algorithm - the algo checks the remainder of two numbers ,
//until the remainder equals to "0" , once we received "0" we found the GCD and then we exit the while loop and returning the number
// that divides the larger number that gave us "0"	
				   public static long GCD(long x, long y) {
// while loop that ends when y = 0					   
				     while(y != 0) {
// sets temp to the remainder of the two numbers 				    	 
				         final long temp =  x%y;
// gives x the value of y				         
				         x=y;
// gives y the value of temp ( remainder) if temp = 0 , means we found the gcd and x is the GCD 				         
				         y=temp;
				      }
// after we exited the loop , we return x which is the GCD (the remainder of x and y is "0"				     
				     return x;
				      
				   }
// a method that finds the MCD of two numbers , using the euclidean gcd algorithm by using the helping method GCD				   
				   public static long MCD(long a , long b , long c) {
// using the GCD with the first two inputs , once it finds the GCD we divide a*b by the gcd to find the MCD between the two numbers					   
					      long gcd1 = GCD(a, b);
					      long mcd1 = a * b / gcd1;
// using the GCD with the third number and the first GCD we found , and does the same as we did with the first two numbers , 
// again we divide the mcd1*c by the seconds gcd we found to find the mcd between the both of them and that will give us the mcd of all three numbers					      
					      long gcd2 = GCD(mcd1 , c);
					      long mcd = mcd1 * c / gcd2;
					      
					      return mcd;
				   }
				   
				   
				}

				




		
		
		
		
	
	

