package Exe.Ex2;

/**
 * Introduction to Computer Science 2023, Ariel University,
 * Ex2: arrays, static functions and JUnit
 *
 * This class represents a set of functions on a polynomial - represented as array of doubles.
 * The array {0.1, 0, -3, 0.2} represents the following polynomial: 0.2x^3-3x^2+0.1
 * This is the main Class you should implement (see "add your code here")
 *
 * @author boaz.benmoshe
 */
public class Ex2 {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynomial is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};
	/**
	 * Computes the f(x) value of the polynomial at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynomial value at x.
	 */
	public static double f(double[] poly, double x) {
		double ans = 0;
		for(int i=0;i<poly.length;i++) {
			double c = Math.pow(x, i);
			ans +=c*poly[i];
		}
		return ans;
	}
	/* Given a polynomial (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x1) <= 0. 
	 * This function should be implemented recursively.
	 * @param p - the polynomial
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double f1 = f(p,x1);
		double f2 = f(p,x2);
		double x12 = (x1+x2)/2;
		double f12 = f(p,x12);
		if (f1*f2<=0 && Math.abs(f12)<eps) {return x12;}
		if(f12*f1<=0) {return root_rec(p, x1, x12, eps);}
		else {return root_rec(p, x12, x2, eps);}
	}
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynomial.
	 * The solution is based on: //	http://stackoverflow.com/questions/717762/how-to-calculate-the-vertex-of-a-parabola-given-three-points
	 * Note: this function only works for a set of points containing up to 3 points, else returns null.
	 * @param xx
	 * @param yy
	 * @return an array of doubles representing the coefficients of the polynomial.
	 */
	
	// FUNCTION EXPLANATION 
	// A function that calculates an equation representing the polynomial , if given an array with two points , calculate using a simple line equation between two points ,
	// y = mx + c , c will be the first element in the array and m is the second one. and if given only two points we initialize the array to be of 2 elements only.
	// if three points are given , we will initialize the array to be of 3 elements , and calculate an equation of all three points (with the same y) 
	// 
	public static double[] PolynomFromPoints(double[] xx, double[] yy) {
		double [] ans = null;
		int lx = xx.length;
		int ly = yy.length;
		// given two points - calculation of a line
		if(xx!=null && yy!=null && lx==ly && lx>1 && lx<4) {
			 if(lx == 2) {
				   double [] ans1 = new double [2]; //initializing the array to be the size of 2
				   double m =(yy[0]-yy[1])/(xx[0]-xx[1]); // the straight line formula
				   ans1[0] = (-m)*xx[1]+yy[1];
				   ans1[1] = m;
				   return ans1;
			   }
			   else {
				   double [] ans2 = new double [3]; // initializing the array
               // using a formula for getting a polynom from three points
			   //https://math.stackexchange.com/questions/889569/finding-a-parabola-from-three-points-algebraically	   
				   double moneA = (xx[0]*(yy[2]-yy[1])+xx[1]*(yy[0]-yy[2])+xx[2]*(yy[1]-yy[0])); 
				   double mehaneA = ((xx[0]-xx[1])*(xx[0]-xx[2])*(xx[1]*xx[2]));
				   double a =moneA/mehaneA;
				   
				   double b = (yy[1]-yy[0])/(xx[1]-xx[0]) -a*(xx[0]-xx[1]);
				   
				   double c = yy[0] -a*xx[0]*xx[0] -b*xx[0];
				   // setting the calculated numbers to their proper place in the answer array
				   ans2[0] = c;
				   ans2[1] = b;
				   ans2[2] = a;
				   
				   return ans2;
			   }
		}
		return ans;
	}
	/** Two polynomials are equal if and only if the have the same values f(x) for 1+n values of x, 
	 * where n is the max degree (over p1, p2) - up to an epsilon (aka EPS) value.
	 * @param p1 first polynomial
	 * @param p2 second polynomial
	 * @return true iff p1 represents the same polynomial as p2.
	 */
	
	// FUNCTION EXPLANATION
	// given two arrays , the function will be true only if the f(x) of both function is of the same value up to eps value , 
	// if both arrays are of the same length , simple - we just check to see we remove one element from another the absolute value of that is smaller than eps.
	// if both arrays aren's of same size , it will check to see if the absolute value of remaining elements of the larger array are smaller than eps
	// using a while loop that runs from the minimal length of an element until the max length
	
	public static boolean equals(double[] p1, double[] p2 ) {		
		 boolean ans = true; // setting ans to be true. will return true if non of the following terms are met
		 
			if (p1.length > p2.length) { // if one array is bigger
				for(int i = 0 ; i < p2.length ; i ++) {
					if(!(Math.abs(p2[i]-p1[i])<=EPS)){ // if the absolute value isn't smaller than epsilon 
						ans = false;  // elements aren't equal up to an epsilon value
						break; // if terms are met breaks the loop and returns ans.
					}
					int j = p2.length;
					while(j < p1.length) { // while loop for only the larger array
						double check = p1[j];
						if(!(Math.abs(check)<=EPS)) // checks if the absolute value of remaining elements is smaller than epsilon
							ans = false;
						break;
					}
					j++;
				}
					}
			
			else if (p2.length>p1.length) { // same as before just if the other array is larger
				for(int i = 0 ; i < p1.length ; i ++) {
					if(!(Math.abs(p1[i]-p2[i])<=EPS)){
						ans = false;
						break;
					}
					int j = p1.length;
					while(j < p2.length) {
						double check = p2[j];
						if(!(Math.abs(check)<=EPS)) {
							ans = false;
						break;
						}
						j++;
					}
				}
				}
			
			else {          // same as before just with same size arrays
				for(int i = 0 ; i < p1.length ; i++) {
					if(!(Math.abs(p2[i]-p1[i])<=EPS)) {
						ans = false;
						break;
					}
				}
				
		}
			return ans;
	}
		
	

	/** 
	 * Computes a String representing the polynomial.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 * @param poly the polynomial represented as an array of doubles
	 * @return String representing the polynomial: 
	 */
	
	/* FUNCTION EXPLANATION
	split into few cases ;
	 if the array's length is 0 - returns 0.
	if not , goes into for loop that runs from the last element of the array and checks if the number within is x>0 , x<0 or x=0
	 if x>0 then the string will print +x^(the place in the array) , if x<0 will print -x^(place) , else will continue to the next number in the loop.
	 then it checks the two first numbers in the array and adds them separately without adding ^(place) 
	 afterwards checks if the string starts with "+" (shouldn't with positive numbers) and the it removes it if so.
	 */
	public static String poly(double[] poly) {
		String ans = "";
		if(poly.length==0) {ans="0";} // if given polynomial is empty
		else {
			for(int i = poly.length-1 ; i > 1 ; i --) {				// for loop from the highest array element till the third one (all of the elements that has "x^"
				if(poly[i] > 0) { // if the element is positive (we need to add +)
					ans = ans +" +"+ String.valueOf(poly[i])+"x^"+String.valueOf(i);	// adds "+" , value of element , "x^" and the exponent				
				}
				else if (poly[i] < 0){
					ans = ans +String.valueOf(poly[i])+"x^"+String.valueOf(i); // if element is negative ans will be the element value and "x^" , exponent
				}				
			    if(poly[i] == 0) { continue;}
		}
			if (poly[0] != 0 && poly[1] != 0) { // if the first and second elements aren't zero
				
			     if(poly[0] > 0 && poly[1] > 0) { // if both are positive
				     ans = ans +" +" +String.valueOf(poly[1])+"x" + " +" + String.valueOf(poly[0]); 
			          }
			     else if(poly[0] > 0 && poly[1] < 0) { // if only one is positive
				     ans = ans + String.valueOf(poly[1])+"x" + " +" +String.valueOf(poly[0]);
			          }
			     else if(poly[0] < 0 && poly[1] > 0) { // if only one is positive
				     ans = ans + " +" + String.valueOf(poly[1])+"x" +" "+ String.valueOf(poly[0]);
			          }
			     else if(poly[0] < 0 && poly[1] < 0) { // if both are negative
				     ans = ans + String.valueOf(poly[1])+"x" + " " + String.valueOf(poly[0]);
			          }
			}
		
			else if(poly[0] != 0 && poly[1] == 0) { // if the first element isn't 0 and the second is
				if(poly[0] > 0) { // and if the element is positive
					ans = ans + " +" + String.valueOf(poly[0]);
				}
				else ans = ans + " " + String.valueOf(poly[0]); // if negative
			}
			else if(poly[0] == 0 && poly[1] != 0) { // if the second one isn't 0 , and the first one is
				if(poly[1] > 0) { // if the element is positive
					ans = ans + " +" + String.valueOf(poly[1])+"x";
				}
				else ans = ans + " " + String.valueOf(poly[1])+"x"; // if negative
				
			}
			 
		}
		
		if(ans.startsWith(" +")) { // if the String starts with + it removes it
			ans = ans.substring(2, ans.length());
		}
		return ans;
	}
	/**
	 * Given two polynomials (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 * @param p1 - first polynomial
	 * @param p2 - second polynomial
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) - p2(x)| < eps.
	 */
	//FUNCTION EXPLANATION
	//using the root_rec function we got , this function receives two polynomials and multiplies one of them by -1 , afterwards adds the two polynomials,
	// giving us a new polynomial , using the new polynomial and the distance we were already given and the eps - it calculates the x using the root_rec function
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double [] arr = {-1};
		double [] p2neg = (mul(p2 , arr)); // multiplying by -1
		double [] hx = (add(p1, p2neg));  // the new polynomial h(x)
		return root_rec(hx , x1 ,x2 ,eps); // using the function we have to calculate
		
		
		
	}
	/**
	 * Given a polynomial (p), a range [x1,x2] and an integer with the number (n) of sample points. 
	 * This function computes an approximation of the length of the function between f(x1) and f(x2) 
	 * using n inner sample points and computing the segment-path between them.
	 * assuming x1 < x2. 
	 * This function should be implemented iteratively (none recursive).
	 * @param p - the polynomial
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfSegments - (A positive integer value (1,2,...).
	 * @return the length approximation of the function between f(x1) and f(x2).
	 */
	
	// FUNCTION EXPLANATION
	//divides the segment to equal parts , and calculates the distance using simple equation , calculates the y point of the current and next point of the segment ,
	// and calculates the distance using absolute value and pythagorean theorem.
	public static double length(double[] p, double s1, double s2, int numSeg) {
		double ans = 0;
        double segLeng = (Math.abs(s1-s2))/numSeg; // number of segments
        double maxX = Math.max(s1 , s2);
        double minX = Math.min(s1 , s2);
        
		for(double i = minX ; i < maxX ; i = i+segLeng) { // for loop from the first point to the one before last.
			double y1 = f(p , i); // current y point
			double x1 = i;        // current x 
			double y2 = f(p , i+segLeng); // next y point
			double x2 = i+segLeng;        // next x
			double xDis = Math.abs(x1 - x2); // distance between x
			double yDis = Math.abs(y1-y2);   // distance between y
			
			double segDis = Math.sqrt(xDis*xDis + yDis*yDis); //pythagorean theorem
			
			ans = ans + segDis; //adds each length calculated 
		}								
		return ans;
	}
	
	/**
	 * Given two polynomials (p1,p2), a range [x1,x2] and an integer representing the number of Trapezoids between the functions (number of samples in on each polynomial). 
	 * This function computes an approximation of the area between the polynomials within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 * @param p1 - first polynomial
	 * @param p2 - second polynomial
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfTrapezoid - a natural number representing the number of Trapezoids between x1 and x2.
	 * @return the approximated area between the two polynomials within the [x1,x2] range.
	 */
	
	//FUNCTION EXPLANATION 
	//calculates a trapezoid area using the equation - the average of both bases and duplicates it by the distance (height)
	// using a for loop from the min of the segment , and add the height to i after every loop , until the final trapezoid.
	// if one of the distances found is equal to zero - it will calculate the area of a triangle and add to the answer.
	// if both distances are equal to 0 , means were in a line and there's no size.
	
	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfTrapezoid) {
		double ans = 0;
		double min = Math.min(x1, x2);
		double max = Math.max(x1, x2);
		double leng = Math.abs(min-max);
		double seg = leng/numberOfTrapezoid;
		
		for(double i = min ; i < max ; i = i+seg) { // loops that runs for each segment (trapezoid) 
		double y1dist	=Math.abs(f(p1 , i) - f(p2 , i));       //y distance
		double y2dist = Math.abs(f(p1 , i+seg) - f(p2 , i+seg));//next y distance
		if(y2dist == 0 && y1dist == 0) { // straight line - no area
			ans = ans +0;
		}
		else if(y2dist == 0 && !(y1dist==0)) { // triangle area
			ans = ans + (seg*y1dist)/2;
		}
		else if(y1dist == 0 && !(y2dist==0)) { // triangle area
			ans = ans + (seg*y2dist)/2;
		}  
		else {                                  // trapezoid area
			ans = ans + ((y2dist+y1dist)/2)*seg;
		}
		}
		return ans;
	}
	/**
	 * This function computes the array representation of a polynomial from a String
	 * representation. Note:given a polynomial represented as a double array,  
	 * getpolynomialFromString(poly(p)) should return an array equals to p.
	 * 
	 * @param p - a String representing polynomial.
	 * @return
	 */
	
	//FUNCTION EXPLANATION
	//splits the string given by the space between the different x's , and sorts them into an array of strings.
	// in the array we check for the highest exponent using the helper function , and initialize the ans array by it.
	//the we check if the number is positive or negative , and sort it to its place by checking his exponent (the number that comes after ^)
	
    public static double[] getPolynomFromString(String p) {
		
	    String[] strArr = p.split(" ");
	    double[] ans = new double[highestExp(p)+1];   // checks the highest exponent to initialize the array

	    for (int i = 0; i < strArr.length; i++) {   // loops that runs through all of the string characters
	        if (strArr[i].contains("x") && !strArr[i].contains("^")) { // if the element has only x and no exponent
	        	if(strArr[i].contains("+")) { // if positive
	        		 String sub1 = strArr[i].substring(1, strArr[i].indexOf('x')); // gets the number without the x
	 	            ans[1] = Double.parseDouble(sub1); // value of the string to double
	        	}
	        	else { // if negative
	        		 String sub1 = strArr[i].substring(0, strArr[i].indexOf('x')); // gets the number including the -
	 	            ans[1] = Double.parseDouble(sub1); // value of string 
	        	}	            
	        } 
	        else if (strArr[i].contains("x") && strArr[i].contains("^")) { // if there's x and ^
	        	if(strArr[i].contains("+")){ // if positive
	        		String sub2 = strArr[i].substring(1, strArr[i].indexOf('x'));
		            int place = Character.getNumericValue(strArr[i].charAt(strArr[i].indexOf('^') + 1)); // place of the element in new array
		            ans[place] = Double.parseDouble(sub2);   		
	        	}
	        	else {
	        		String sub2 = strArr[i].substring(0, strArr[i].indexOf('x'));                        //  substring of the number only
		            int place = Character.getNumericValue(strArr[i].charAt(strArr[i].indexOf('^') + 1)); // place of element in new array
		            ans[place] = Double.parseDouble(sub2);     // value of string to double
	        	} 
	        } 
	        else { // if there's no x and ^
	        	if(strArr[i].contains("+")){ // if positive
	        		String sub3 = strArr[i].substring(1, strArr[i].indexOf('x')); 
		            ans[0] = Double.parseDouble(sub3);   		// places in the 0 element of the array

	        	}
	        	else { // if negative
		            ans[0] = Double.valueOf(strArr[i]); // value of string to double

	        	}
	        }
	    } 
	    return ans;
	}
	
	public static int highestExp(String s) { // calculates which is the highest exponent
	     
        
        int maxExp = -1;
        int index = s.indexOf("x^"); // gets the index of x^
        while (index != -1) {
            int start = index + 2; // the start of the exponent
            int end = start;
            while (end < s.length() && Character.isDigit(s.charAt(end))) { // checks if following characters are digits
                end++;
            }
            int exponent = Integer.parseInt(s.substring(start, end)); // sets the exponent we found
            if (exponent > maxExp) { // if we found a higher exponent
                maxExp = exponent;
            }
            index = s.indexOf("x^", end);
        }
       return maxExp; // returns max exponent
    }
	/**
	 * This function computes the polynomial which is the sum of two polynomials (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	
	// FUNCTION EXPLANATION
	//first , we check to see which of the two arrays is the largest , then we add to a new array the sum of both places of the different arrays
	// using a for loop that goes from the first element to the last element in the smaller array , and adds the rest of the sum of the largest array to the empty places in the new one
	public static double[] add(double[] p1, double[] p2) {
		double [] ans = new double[Math.max(p1.length, p2.length)];
		int min = Math.min(p1.length, p2.length);
		int max = Math.max(p2.length, p1.length);
		
		for(int i = 0 ; i<min ; i++) { // runs for all the elements until the minimal array length
			ans[i] = p1[i] + p2[i];			// add elements
		}
		if(p1.length != p2.length) { // if arrays aren't of equal size
			if(p1.length > p2.length) {
				for(int j = min ; j < max ; j++) { // adds only the elements of the larger array to the new array
					ans[j] = p1[j];
				}
			}
			else if (p2.length > p1.length) {
				for(int j = min ; j < max ; j++) {
					ans[j] = p2[j];
			}
		
		}
		}
		return ans;
	}
	/**
	 * This function computes the polynomial which is the multiplication of two polynomials (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	
	// FUNCTION EXPLANATION
	//initialize the answer array to be of the size of both lengths -1 (size will be the sum of the highest exponents from each array minus one)
	//afterwards there are two for loops for each array that multiplies the elements in the arrays and sort them into the new array.(sum of exponent is the place)
	public static double[] mul(double[] p1, double[] p2) {
		int n = p1.length + p2.length - 1;
	    double[] result = new double[n]; // initializing the new array to be the size of sum of both exponents minus one
	    for (int i = 0; i < result.length; i++) {
	        result[i] = 0;
	    }
	    for (int i = 0; i < p1.length; i++) { // all the elements of one array
	        for (int j = 0; j < p2.length; j++) { // all the elements of second array
	            result[i+j] += p1[i] * p2[j];  // multiplies both elements and sets them to their place in the new array
	        }
	    }
	    return result;
		}
	
	/**
	 * This function computes the derivative polynomial:.
	 * @param po
	 * @return
	 */
		
		// FUNCTION EXPLANATION
		// using a simple derivative formula , initialize new array to be the a smaller by one ans simply calculates it
	    // if receives an array of 0 or 1 elements , returns an empty array.
	public static double[] derivative (double[] po) {
		
	        double ans [] = new double[po.length -1];
			if(po.length == 0) { // if given array is empty
				return ZERO;
			}
			else if(po.length == 1) { // if given array has only the first element
				return ZERO;
			}
			else { // simple derivative calculation
			     for(int i = po.length -1 ; i>0 ; i--) { // runs through all of the array
				     ans[i-1] = po[i]*(i); // exponent times element value
			     }
			     return ans;
			}
		}
		
}