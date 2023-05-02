package Exe.Ex2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  * Introduction to Computer Science 2023, Ariel University,
 *  * Ex2: arrays, static functions and JUnit
 *
 * This JUnit class represents a JUnit (unit testing) for Ex2 - 
 * It contains few testing functions for the polynomial functions as define in Ex2.
 * Note: you should add additional JUnit testing functions to this class.
 *
 * @author boaz.ben-moshe
 */

class Ex2Test {
	static final double[] P1 ={2,0,3, -1,0}, P2 = {0.1,0,1, 0.1,3};
	static double[] po1 = {2,2};
	static double[]	po2 = {-3, 0.61, 0.2};
	static double[] po3 = {2,1,-0.7, -0.02,0.02};
	static double[] po4 = {-3, 0.61, 0.2};
	static double[] poMinOne = {-1};
	static double[] negPol = {-5 , -4 ,-0.2 , -0.35};
	static double[] negPolAns = {-4 , -0.4 , -1.05};
 	@Test
	/**
	 * Tests that f(x) == poly(x).
	 */
	void testF() {
		double fx0 = Ex2.f(po1, 0);
		double fx1 = Ex2.f(po1, 1);
		double fx2 = Ex2.f(po1, 2);
		assertEquals(fx0, 2, Ex2.EPS);
		assertEquals(fx1, 4, Ex2.EPS);
		assertEquals(fx2, 6, Ex2.EPS);
	}
	@Test
	/**
	 * Tests that p1(x) + p2(x) == (p1+p2)(x)
	 */
	void testF2() {
		double x = Math.PI;
		double[] po12 = Ex2.add(po1, po2);
		double f1x = Ex2.f(po1, x);
		double f2x = Ex2.f(po2, x);
		double f12x = Ex2.f(po12, x);
		assertEquals(f1x + f2x, f12x, Ex2.EPS);
	}
	@Test
	/**
	 * Tests that p1+p2+ (-1*p2) == p1
	 */
	void testAdd() {
		double[] p12 = Ex2.add(po1, po2);
		double[] minus1 = {-1};
		double[] pp2 = Ex2.mul(po2, minus1);
		double[] p1 = Ex2.add(p12, pp2);
		assertTrue(Ex2.equals(p1, po1));
	}
	@Test
	/**
	 * Tests that p1+p2 == p2+p1
	 */
	void testAdd2() {
		double[] p12 = Ex2.add(po1, po2);
		double[] p21 = Ex2.add(po2, po1);
		assertTrue(Ex2.equals(p12, p21 ));
	}
	@Test
	/**
	 * Tests that p1+0 == p1
	 */
	void testAdd3() {
		double[] p1 = Ex2.add(po1, Ex2.ZERO);
		assertTrue(Ex2.equals(p1, po1 ));
	}
	@Test
	/**
	 * Tests that p1*0 == 0
	 */
	void testMul1() {
		double[] p1 = Ex2.mul(po1, Ex2.ZERO);
		assertTrue(Ex2.equals(p1, Ex2.ZERO));
	}
	@Test
	/**
	 * Tests that p1*p2 == p2*p1
	 */
	void testMul2() {
		double[] p12 = Ex2.mul(po1, po2);
		double[] p21 = Ex2.mul(po2, po1);
		assertTrue(Ex2.equals(p12, p21));
	}
	/*
	 * test that if we multiply a polynomial by -1 twice , we will get the same polynomial
	 */
	@Test
	void testMulNegTwice() {
		double [] pNeg = Ex2.mul(po3 , poMinOne);
		double [] pNegNeg = Ex2.mul(pNeg , poMinOne);
		assertTrue(Ex2.equals(pNegNeg, po3));
		
		}
	
	@Test
	/**
	 * Tests that p1(x) * p2(x) = (p1*p2)(x),
	 */
	void testMulDoubleArrayDoubleArray() {
		double[] xx = {0,1,2,3,4.1,-15.2222};
		double[] p12 = Ex2.mul(po1, po2);
		for(int i = 0;i<xx.length;i=i+1) {
			double x = xx[i];
			double f1x = Ex2.f(po1, x);
			double f2x = Ex2.f(po2, x);
			double f12x = Ex2.f(p12, x);
			assertEquals(f12x, f1x*f2x, Ex2.EPS);
		}
	}
	@Test
	/**
	 * Tests a simple derivative examples - till ZERO.
	 */
	void testDerivativeArrayDoubleArray() {
		double[] p = {1,2,3}; // 3X^2+2x+1
		double[] pt = {2,6}; // 6x+2
		double[] dp1 = Ex2.derivative(p); // 2x + 6
		double[] dp2 = Ex2.derivative(dp1); // 2
		double[] dp3 = Ex2.derivative(dp2); // 0
		double[] dp4 = Ex2.derivative(dp3); // 0
		assertTrue(Ex2.equals(dp1, pt));
		assertTrue(Ex2.equals(Ex2.ZERO, dp3));
		assertTrue(Ex2.equals(dp4, dp3));
	}
	/*
	 * checks to see if the derivative is of the same values as it should have (pre-calculation).
	 */
	@Test
	void testDerivativeNeg() {
		double [] negDer = Ex2.derivative(negPol);
		assertTrue(Ex2.equals(negPolAns, negDer));
		
	}
	
	@Test
	/** 
	 * Tests the parsing of a polynomial in a String like form.
	 */
	public void testFromString() {
		double[] p = {-1.1,2.3,3.1}; // 3.1X^2+ 2.3x -1.1
		String sp2 = "3.1x^2 +2.3x -1.1";
		String sp = Ex2.poly(p);
		double[] p1 = Ex2.getPolynomFromString(sp);
		double[] p2 = Ex2.getPolynomFromString(sp2);
		boolean isSame1 = Ex2.equals(p1, p);
		boolean isSame2 = Ex2.equals(p2, p);
		if(!isSame1) {fail();}
		if(!isSame2) {fail();}
		assertEquals(sp, Ex2.poly(p1));
	}
	@Test
	/** 
	 * Tests the parsing of a polynomial in a String like form with different order.
	 */
	public void testFromStringNotByOrder() {
		double[] p = {0,0,3.1,2.3,0,-1.1}; // 3.1X^2+ 2.3x -1.1
		String sp2 = "3.1x^2 2.3x^3 -1.1x^5";
		String sp = Ex2.poly(p);
		double[] p1 = Ex2.getPolynomFromString(sp);
		double[] p2 = Ex2.getPolynomFromString(sp2);
		boolean isSame1 = Ex2.equals(p1, p);
		boolean isSame2 = Ex2.equals(p2, p);
		if(!isSame1) {fail();}
		if(!isSame2) {fail();}
		assertEquals(sp, Ex2.poly(p1));
	}
	@Test
	/**
	 * Tests the equality of pairs of arrays.
	 */
	public void testEquals() {
		double[][] d1 = {{0}, {1}, {1,2,0,0}};
		double[][] d2 = {Ex2.ZERO, {1+Ex2.EPS/2}, {1,2}};
		double[][] xx = {{-2*Ex2.EPS}, {1+Ex2.EPS*1.2}, {1,2,Ex2.EPS/2}};
		for(int i=0;i<d1.length;i++) {
			assertTrue(Ex2.equals(d1[i], d2[i]));
		}
		for(int i=0;i<d1.length;i++) {
			boolean ans = Ex2.equals(d1[i], xx[i]);
			if(ans = false){
			assertFalse(Ex2.equals(d1[i], xx[i]));
			}
		}
	}

	@Test
	/**
	 * Tests is the sameValue function is symmetric.
	 */
	public void testSameValue2() {
		double x1=-4, x2=0;
		double rs1 = Ex2.sameValue(po1,po2, x1, x2, Ex2.EPS);
		double rs2 = Ex2.sameValue(po2,po1, x1, x2, Ex2.EPS);
		assertEquals(rs1,rs2,Ex2.EPS);
	}
	@Test
	/**
	 * Test the area function - it should be symmetric.
	 */
	public void testArea() {
		double x1=-4, x2=0;
		double a1 = Ex2.area(po1, po2, x1, x2, 100);
		double a2 = Ex2.area(po2, po1, x1, x2, 100);
		assertEquals(a1,a2,Ex2.EPS);
}
	@Test
	public void testArea2() {
		
		double b1 = Ex2.area(po2, po3, 6, 8, 90); 
		double b2 = Ex2.area(po3, po2, 6, 8, 90);
		assertEquals(b1 , b2 ,Ex2.EPS);
	}
	/*
	 * tests to see if the area works with negative values
	 */
	@Test
	public void testAreaNegValues() {
	    double[] p1 = {-2, -3};
	    double[] p2 = {-2, 1};
	    double x1 = -2;
	    double x2 = 2;
	    int numberOfTrapezoid = 4;
	    double result = Ex2.area(p1, p2, x1, x2, numberOfTrapezoid);
	    double expected = 16.0; // calculated manually
	    assertEquals(expected, result);
	}
    /*
     * tests to check if the function works with x1>x2
     */
	@Test
	public void testAreaX1GreaterThanX2() {
	    double[] p1 = {0, 0};
	    double[] p2 = {0, 5};
	    double x1 = 10;
	    double x2 = 2;
	    int numberOfTrapezoid = 100;
	    double result = Ex2.area(p1, p2, x1, x2, numberOfTrapezoid);
	    double expected = 240.00000000000017; // calculated manually
	    assertEquals(expected, result);
	}
	// test only two points given
	@Test 
	public void testLeng2points() {
		double[] p = {0, 1}; // y = x function
		double s1 = 0; // first x point
		double s2 = 1; // second x point
		int numSeg = 2; // number of segments

		double result = Ex2.length(p, s1, s2, numSeg);

		// expected result is the distance between (0,0) and (1,1)
		// which is sqrt(2)
		assert Math.abs(result - Math.sqrt(2)) < Ex2.EPS; // use tolerance for floating point comparison

	}
	// tests a linear equation
   @Test
	   public void testLinearLength() {
	        double[] p = {0, 1}; // y = x function
	        double s1 = 0; // first x point
	        double s2 = 1; // second x point
	        int numSeg = 2; // number of segments

	        double result = Ex2.length(p, s1, s2, numSeg);

	        // expected result is the distance between (0,0) and (1,1)
	        // which is the square root of 2
	        assertEquals(Math.sqrt(2), result);
	    
   }
   // tests quadratic equation
   @Test
   public void testQuadraticLength() {
       double[] p = {0, 0, 1}; // y = x^2 function
       double s1 = 0; // first x point
       double s2 = 2; // second x point
       int numSeg = 1; // number of segments

       double result = Ex2.length(p, s1, s2, numSeg);

       // expected result is the distance between (0,0) and (2,4)
       // which is the square root of 20
       assertEquals(Math.sqrt(20), result);
   }
   // tests a line
   @Test
   public void testPolynomFromPointsLine() {
       double[] xx = {1, 2};
       double[] yy = {2, 4};
       double[] expected = {-0, 2};
       assertTrue(Ex2.equals(expected, Ex2.PolynomFromPoints(xx, yy)));
   }
   // tests quadratic equation
   @Test
   public void testPolynomFromPointsParabola() {
       double[] xx = {1, 2, 3};
       double[] yy = {3, 6, 11};
       double[] expected = {1, 0, 2};
       double [] ans = Ex2.PolynomFromPoints(xx, yy);
       assertTrue(Ex2.equals(expected , ans));
   }
  // both x and y coordinates are null
   @Test
   public void testPolynomFromPointsNull() {
       double[] xx = null;
       double[] yy = null;
       double[] expected = null;
       double[] tester = Ex2.PolynomFromPoints(xx , yy);
       assertArrayEquals(expected, tester);
   }
   // uneven coordinates 
   @Test
   public void testPolynomFromPointsUneven() {
       double[] xx = {1, 2, 3};
       double[] yy = {3, 6};
       double[] expected = null;
       assertArrayEquals(expected, Ex2.PolynomFromPoints(xx, yy), 1e-6);
   }
   // tests poly that starts with blank places
  @Test
  public void testPoly1() {
	  double[] poly1 = {0, 0, 0, 0, 0, -1, 0, 2};
	  String result = Ex2.poly(poly1);
	  String expected = "2.0x^7-x^5";
	  assertEquals(result , expected);

  }
  // tests poly with few blanks in the middle of the array
  @Test
  public void testPoly2() {
	  double[] poly = {-5, 0, 0, 2};
	  String result = Ex2.poly(poly);
	  String expected = "2.0x^3 -5.0";
	  assert(result.equals(expected)) : "Test Case 2 Failed";

  }
   
}
