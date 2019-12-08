
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import myMath.ComplexFunction;
import myMath.Operation;
import myMath.Polynom;
import myMath.function;

class Complextest1 {

	@Test
	void test_f_x1 () {
		System.out.println("***test_f_x1***");
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
	@Test
	void test_string () {
		System.out.println("***test_string***");

		ComplexFunction c1= new ComplexFunction(new Polynom ("x"));
		function f1 = c1.initFromString("Plus(Max(22.0x^3,53.0),Divid(Comp(Plus(3.0x^3,46.0),Plus(1.0x^2,1.0x^7)),Max(22.0x^3,53.0)))");
		String Exepted = "Plus(Max(22.0x^3,53.0),Divid(Comp(Plus(3.0x^3,46.0),Plus(1.0x^2,1.0x^7)),Max(22.0x^3,53.0)))";
		assertEquals(Exepted, f1.toString());
		 ComplexFunction c2= new ComplexFunction(Operation.Min, new Polynom("3x^3"), new Polynom("46"));
		   /*						min
		    * 					3x^3	46
		    */
		   Exepted = "Min(3.0x^3,46.0)";
			assertEquals(Exepted, c2.toString());
			   ComplexFunction c4= new ComplexFunction(Operation.Max, new Polynom("22x^3"), new Polynom("53"));

		
			c2.div(c4);
			 /*
			  *                                         div
			  *                          comp                          max
			  * 						                          22x^3      53
			  *                 min              plus
			  *            3x^3       46      x^2       x^7
			  */ 
			Exepted = "Divid(Min(3.0x^3,46.0),Max(22.0x^3,53.0))";
			assertEquals(Exepted, c2.toString());
			 ComplexFunction c5= new ComplexFunction(Operation.Max, new Polynom("22x^3"), new Polynom("53"));
				c5.plus(c2);
				   System.out.println( "c5 is: "   + c5.toString());
				   Exepted = "Plus(Max(22.0x^3,53.0),Divid(Min(3.0x^3,46.0),Max(22.0x^3,53.0)))";
					assertEquals(Exepted, c5.toString());
	}
	
	@AfterAll
	@Test
	void test_copy () {
		ComplexFunction c2= new ComplexFunction(Operation.Min, new Polynom("3x^3"), new Polynom("46"));
		ComplexFunction Exepted= new ComplexFunction(c2.copy());
		assertEquals(Exepted, c2);

	}
}
