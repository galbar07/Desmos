package tests;

import myMath.ComplexFunction;
import myMath.Operation;
import myMath.Polynom;
import myMath.complex_function;
import myMath.function;

public class ComplexTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   ComplexFunction c1= new ComplexFunction(Operation.Plus,new Polynom("x^2"),new Polynom("x^7"));
   function c2= c1.initFromString("plus(x+3,plus(x,7))");
   ComplexFunction c3=new ComplexFunction(Operation.Plus,c1,new Polynom("x^5"));
   System.out.println(c3.tostring());
   
   System.out.println(c2.f(1));
   System.out.println(c1.tostring());
 

   
 
	}

}
