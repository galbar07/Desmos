package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.ComplexFunction;
import myMath.Operation;
import myMath.Polynom;

class Complextest1 {

	@Test
	void test() {
		ComplexFunction cf1 = new ComplexFunction(Operation.Comp,new Polynom("x^2"),new Polynom("3x+5"));
		assertEquals(196, cf1.f(3));
		ComplexFunction c1= new ComplexFunction(Operation.Plus,new Polynom("x^2"),new Polynom("x^7"));
		/*				****tree c1*****
		 *        				+
		 *        x^2                       x^7  
		 */
		assertEquals(132, c1.f(2));
		ComplexFunction c2= new ComplexFunction(Operation.Min, new Polynom("3x^3"), new Polynom("46"));
		/*					****tree c2*****
		 * 						min
		 * 					3x^3	46
		 */
		assertEquals(24, c2.f(2));

		c2.max(c1);
		/*					****tree c2*****
		 * 						  max
		 *                 min              plus
		 *            3x^3       46      x^2       x^7
		 */  
		assertEquals(132, c2.f(2));
		ComplexFunction c4= new ComplexFunction(Operation.Max, new Polynom("22x^3"), new Polynom("53"));
		 c2.div(c4);
			/*								****tree c2*****
		  *                                         div
		  *                          comp                          max
		  * 						                          22x^3      53
		  *                 min              plus
		  *            3x^3       46      x^2       x^7
		  */     
			assertEquals(0.75, c2.f(2));
	}

}
