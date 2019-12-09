package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import myMath.Monom;

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
	
	
	
	
}
