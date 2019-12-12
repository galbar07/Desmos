package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import EX1.ComplexFunction;
import EX1.Monom;
import EX1.Operation;
import EX1.Polynom;
import EX1.function;

class MonomTest {

	@Test
	void testTostring1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms ={"2x^3", "-x","-3.2x^2","0"};
		String[] monomsE ={"2.0x^3", "-1.0x","-3.2x^2","0"};
		boolean [] expecpted= {false,false,false,true};
		boolean suposse;
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			String sExpected = monomsE[i];
			assertEquals(s, sExpected);
			assertEquals(expecpted[i], m.isZero());
		}
		
	}
	@Test
	void testTostring2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		String[] monomsE ={"0", "-1.0","-1.3x","-2.2x^2"};
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			String sExpected = monomsE[i];
			assertEquals(s, sExpected);
	}
}
	@Test
	void MonomAdd() {
		System.out.println("****Add Test*****");
		Monom m1 = new Monom("2x^3");
		Monom m2 = new Monom("5x^3");
		Monom asert = new Monom("7x^3");
		m1.add(m2);
		assertTrue(m1.equals(asert));		
	}
	
	@Test
	void MonomMult() {
		System.out.println("****Mul Test*****");
		Monom m1 = new Monom("x^2");
		Monom m2 = new Monom("x^3");
		Monom m3 = new Monom("x^5");
		m1.multipy(m2);
		assertTrue(m1.equals(m3));			
	}
	
	@Test
	void isZero_test() {
		System.out.println("****Is Zero*****");
		Monom m1 = new Monom("3x^2");
		Monom m2 = new Monom("-3x^2");
		m1.add(m2);
		assertTrue(m1.isZero());		
	}
	
	@Test
	void Initfromstring() {
	System.out.println("****Init from a string*****");
	Monom m1 = new Monom("x^2");
	function m2 = m1.initFromString("x^2");
	assertTrue(m2.equals(m1));
	
	
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	void TestEquals() {
	System.out.println("****Test Equals*****");
	Monom m1 = new Monom("2x^2");
	ComplexFunction cf = new ComplexFunction(Operation.Plus,new Polynom("x^2"),new Polynom("x^2"));
	assertTrue(m1.equals(cf));
	Polynom p = new Polynom("x^2+x^2-1+1");
	assertTrue(m1.equals(p));
	assertFalse(m1.equals(new Polynom("x^2")));
	}
	
	
	
	
	
}
