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
//		// TODO Auto-generated method stub
//   ComplexFunction c1= new ComplexFunction(Operation.Plus,new Polynom("x^2"),new Polynom("x^7"));
//   function c2= c1.initFromString("plus(x+3,plus(x,7))");
//   ComplexFunction c3=new ComplexFunction(Operation.Plus,c1,new Polynom("x^5"));
//  // System.out.println(c3.tostring());
//   
//   System.out.println(c2.f(1));
//   System.out.println(c1.tostring());
		Functions_GUI test = new Functions_GUI();
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		test.drawFunctions(1000,600,rx,ry,200);
 

   
 
	}

}
