package tests;


import java.io.IOException;

import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Operation;
import myMath.Polynom;
import myMath.Range;
import myMath.complex_function;
import myMath.function;

public class ComplexTest {
	 public static void main(String[] args) throws IOException {
//		 		testf_comp();
//		        testToString();
//				testInitFrom();
		 testguifunction();
		}
	 
	 public static void testf_comp () {
		 
		 ComplexFunction cf1 = new ComplexFunction(Operation.Comp,new Polynom("x^2"),new Polynom("3x+5"));
		 System.out.println(cf1.f(3));
		 
		 
		 
	 }
	 
	 
	 
		public static void testInitFrom() {
			ComplexFunction c1= new ComplexFunction(new Polynom ("x"));
			function f1 = c1.initFromString("Plus(Max(22.0x^3,53.0),Divid(Comp(Plus(3.0x^3,46.0),Plus(1.0x^2,1.0x^7)),Max(22.0x^3,53.0)))");
//	    	 f1 = c1.initFromString("plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
			 System.out.println(f1.toString());
		}
		public static void testToString() {
		 ComplexFunction c1= new ComplexFunction(Operation.Plus,new Polynom("x^2"),new Polynom("x^7"));
				   /*
				    *        					+
				    *        x^2                       x^7  
				    */
				   System.out.println( "c1 is: "   + c1.f(2));
				   
				   ComplexFunction c2= new ComplexFunction(Operation.Min, new Polynom("3x^3"), new Polynom("46"));
				   /*						min
				    * 					3x^3	46
				    */
				   System.out.println( "c2 is: "   + c2.f(1));

				   System.out.println( "c2 is: "   + c2.toString());

				c2.min(c1);
				/*
				 * 						  min
				 *                 min              plus
				 *            3x^3       46      x^2       x^7
				 */  
				   System.out.println( "c2 is: "   + c2.f(2));

				   System.out.println( "c2.comp(c1) is: "   + c2.toString());
				   ComplexFunction c4= new ComplexFunction(Operation.Max, new Polynom("22x^3"), new Polynom("53"));
				 c2.div(c4);
				 /*
				  *                                         div
				  *                          comp                          max
				  * 						                          22x^3      53
				  *                 min              plus
				  *            3x^3       46      x^2       x^7
				  */     
				 System.out.println( "c2 is: "   + c2.toString());
				 
				   ComplexFunction c5= new ComplexFunction(Operation.Max, new Polynom("22x^3"), new Polynom("53"));
					c5.plus(c2);
				   System.out.println( "c5 is: "   + c5.toString());

				 ComplexFunction cPoly = new ComplexFunction(new Polynom("x^5+3")); 
				System.out.println("polynom is: "+ cPoly);
			}



	public static void 	 testguifunction() throws IOException {
		
		Functions_GUI f = new Functions_GUI();
		f.initFromFile("file_test.txt");
		f.saveToFile("fuchsi.txt");
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		f.drawFunctions(w,h,rx,ry,res);

	

		
	}
}

 

   
 
	


