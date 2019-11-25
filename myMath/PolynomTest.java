package myMath;

public class PolynomTest {
	public static final double EPSILON = 0.0000001;
	public static void main(String[] args) {
		test1();
		test2();
		test();//our test
	
	}


	public static void test1() {
		System.out.println("test 1 ********");
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
		System.out.println();
	}
	public static void test2() {
		System.out.println("test 2 ********");
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
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		System.out.println();
	}
	
	
	public static void test() {
		System.out.println("Our test ***************");
		System.out.println("Error tests ");
		String [] monoms1 = {"x^2","4x^-1"}; // eror ^-1
		String [] monoms2 = {"x^5","4x^*8"}; // eror *
		String [] monoms3 = {"x^5","4x^8.2"}; // eror power is 8.2
		Polynom p1= new Polynom();
		try {
			for(int i=0;i<monoms1.length;i++) {
				Monom m = new Monom(monoms1[i]);
				p1.add(m);
			}}
		catch(Exception e) {
			System.out.println("4x^-1 this monom power is negative");
			System.out.println(e); // 
		}
		// polynom p2
		Polynom p2= new Polynom();
		try {
			for(int i=0;i<monoms2.length;i++) {
				Monom m = new Monom(monoms2[i]);
				p1.add(m);
			}}
		catch(Exception e) {
			System.out.println("");
			System.out.println("4x^*8 this monom have char");
			System.out.println(e); // 
		}

		// // polynom p3
		Polynom p3= new Polynom();
		try {
			for(int i=0;i<monoms2.length;i++) {
				Monom m = new Monom(monoms3[i]);
				p1.add(m);
			}}
		catch(Exception e) {
			System.out.println("");
			System.out.println("4x^8.2 this monom have double power");
			System.out.println(e); // 
		}
		System.out.println("");
		System.out.println("functionality tests");
		Polynom p4 = new Polynom("x^5-4x"),p5 = new Polynom("-x^5+4x");
		Polynom p6 = new Polynom("x^3+x^8+0"),p7 = new Polynom("x^6+x^9+8");
		p4.add(p5);

		System.out.println("f(x) = x^5-4x+ -x^5+4x = " +p4.toString() + " isZero: " + p4.isZero()); // =0
		p4.add(p6); // x^3+x^8
		p4.add(p7); //x^9+x^8+x^6+x^3+8
		System.out.println("f(x1) x^3+x^8 + f(x2) x^6+x^9+8 = "); //x^9+x^8+x^6+x^3+8
		System.out.println();
		System.out.println( p4.toString());
		System.out.println();
		// substract
		Polynom p8 = new Polynom("x^9+x^8+x^6");
		p4.substract(p8);
		System.out.println("substract x^9+x^8+x^6 = " );
		System.out.println();
		System.out.println(p4.toString()); // x^3 + 8

		Monom m1 = new Monom("x");
		// p4 is x^3 + 8
		p4.multiply(m1); //x^4 + 8x]
		System.out.println("monom*multy:");
		System.out.println("x*(x^3+8) = ");
		System.out.println(p4.toString());
		Polynom p13 = new Polynom("x^9+x^8+x^6");
		p13.multiply(new Monom("0"));
		System.out.println("multy*0 is zero? " + p13.toString());
		System.out.println("multy polynom");
		System.out.println("");
		// multy polynom
		Polynom p9 = new Polynom("x^3+x^7+x^9");
		Polynom p10 = new Polynom("5x+7x^4+12x^11");
		p10.multiply(p9);
		System.out.println("(x^3+x^7+x^9)*(5x+7x^4+12x^11)");
		System.out.println();
		System.out.println(p10.toString()); 
		// root
		
		System.out.println("root");
		Polynom pRoot = new Polynom("x^2-7x+8");
		double root1= pRoot.root(2.35, 6, EPSILON); //5.562
		System.out.println("the root of " +pRoot.toString()+ " is: "+ root1);
		System.out.println();
		Polynom pRoot2 = new Polynom("-x^3+8");
		try {
			double root2= pRoot.root(-2, 1, EPSILON); //erorr
			System.out.println("the root of " +pRoot2.toString()+ " is: "+ root2);
		}
		catch (Exception e) {
			System.out.println(e);
		}

		//area
		System.out.println("area function x^2-7x+8 between 3-8 is: ");
		Polynom pArea = new Polynom("x^2-7x+8");
		double area1= pArea.area(3, 8, EPSILON);
		System.out.println("the area is " + area1); //17.09
		
		 //equals
		
		Polynom pEquals1 = new Polynom("x^2-7x+1+1+1+1+1+4+1");
		System.out.println(pEquals1.toString());
		Polynom pEquals2 = new Polynom("x^2-7x+10");
		System.out.println("p2 is " + pEquals2.toString());
		System.out.println(" is equals? " + pEquals1.equals(pEquals2));
		System.out.println("end test ");





	}
}
