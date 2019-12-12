package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ex1.ComplexFunction;
import ex1.Monom;
import ex1.Operation;
import ex1.Polynom;
import ex1.function;

class PolynomTest {

	@Test
	void test_String() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		String Sp1 = "-4.7x^2 - 1.0x + 6.0";
		assertEquals(p1.toString(),Sp1);
		String Sp2 = "1.7000000000000002x^2 + 1.7x + 2.0";
		assertEquals(p2.toString(),Sp2);
		Polynom p3 = new Polynom("1.7000000000000002x^2 + 1.7x + 2.0") ;
		assertEquals(p3.toString(),Sp2);
		function f = p3.initFromString("2x");


	}

	@Test
	void functionality_test() {
		System.out.println("functionality tests");
		Polynom p4 = new Polynom("x^5-4x"),p5 = new Polynom("-x^5+4x");
		Polynom p6 = new Polynom("x^3+x^8+0"),p7 = new Polynom("x^6+x^9+8");
		p4.add(p5);
		assertTrue(p4.isZero());
		p4.add(p6); // x^3+x^8
		String stringPolynom = "1.0x^8 + 1.0x^3";
		assertEquals(p4.toString(),stringPolynom);

		p4.add(p7); //x^9+x^8+x^6+x^3+8
		Polynom p8 = new Polynom("x^9+x^8+x^6");
		p4.substract(p8);
		stringPolynom = "1.0x^3 + 8.0";
		assertEquals(p4.toString(),stringPolynom);
		//multy monom
		Monom m1 = new Monom("x");
		// p4 is x^3 + 8
		p4.multiply(m1); //x^4 + 8x
		stringPolynom  = "1.0x^4 + 8.0x";
		assertEquals(p4.toString(),stringPolynom);
		// multy polynom
		Polynom p9 = new Polynom("x^3+x^7+x^9");
		Polynom p10 = new Polynom("5x+7x^4+12x^11");
		p10.multiply(p9);
		stringPolynom  = "12.0x^20 + 12.0x^18 + 12.0x^14 + 7.0x^13 + 7.0x^11 + 5.0x^10 + 5.0x^8 + 7.0x^7 + 5.0x^4";
		assertEquals(p10.toString(),stringPolynom);

	}
	@Test
	void root_test() {
		// root

		System.out.println("root");
		Polynom pRoot = new Polynom("x^2-7x+8");
		double root1= pRoot.root(2.35, 6, 0.01); //5.562
		assertEquals(root1, 5.561572265624999);
	}
	@Test
	void area_test() {
		//area
		System.out.println("**area test");

		Polynom pArea = new Polynom("x^2-7x+8");
		double area1= pArea.area(3, 8, 0.0001);
		assertEquals(area1, 17.0918663120845);
	}
	@Test
	void equals_test() {
		System.out.println("equals tests");
		Polynom pEquals1 = new Polynom("x^2-7x+1+1+1+1+1+4+1");
		//	assertEquals(p10.toString(),stringPolynom);
		Polynom pEquals2 = new Polynom("x^2-7x+10");
		assertTrue(pEquals1.equals(pEquals2));

	}
	
	
	@Test
	void Allfunctions_test2() {
	System.out.println("Function test");
	Polynom p1 = new Polynom("5x^2");
	Monom m1 = new Monom("5x^2");
	ComplexFunction cf = new ComplexFunction(Operation.Plus,new Polynom("-3x^2"),new Polynom("8x^2"));
	assertTrue(p1.equals(m1));
	assertTrue(p1.equals(cf));
		
	}
	
	
	

}