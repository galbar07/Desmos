
package ex1;


import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	private double _coefficient; 
	private int _power;
	public static Comparator<Monom> getComp() {return _Comp;}

	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	}

	public boolean isZero() {return this.get_coefficient() == 0;}
	/*build a new Monom only if it stand in this following rules:
	 * 1) the string may contains only numbers and 1 "x" and  1 "^"
	 * 2) should stand in the format of ax^b where a is a real number and b
	 *natural number
	 */
	public Monom(String s) {
	s= s.replaceAll("X", "x");
	s = s.replaceAll("\\s+","");
	this._power=1;
	this._coefficient=1.0;
    if(!s.contains("x")) {
    	this._coefficient = Double.parseDouble(s);//check if we need to return eror**
    	this._power=0;
    }
    else {
    		if(counter_x(s)) { 
    			throw new RuntimeException("ERR monom should be in the type of ax^b where a is real number and b is natural");
    		}
    	
    	
    	String arrofStr [] = s.split("x");
    	if(arrofStr.length==0 || arrofStr[0].equals("")) {
    		this._coefficient=1;
    	}
        else if(arrofStr[0].equals("-")) {
        	this._coefficient = -1;
        }
        else if(arrofStr[0].equals("+")) {
        	this._coefficient=1;
        }
        else {
        	try {
        		this._coefficient = Double.parseDouble(arrofStr[0]);
        		
        	}
        	catch(Exception e) {
        		throw new RuntimeException("ERR monom should be in the type of ax^b where a is real number and b is natural");
        	}
        	
        }
        if(arrofStr.length>1) {
        	if(arrofStr[1].charAt(0)=='^') {
        		try {
        			this.set_power(Integer.parseInt(arrofStr[1].substring(1)));
        		}
        		catch(Exception e) {
            		throw new RuntimeException("ERR monom should be in the type of ax^b where a is real number and b is natural");
            	}
        	}
        	else {
        		throw new RuntimeException("ERR monom should be in the type of ax^b where a is real number and b is natural");
      }
    	}

    }}

		private boolean counter_x(String s) { // check how many 'x' there is in s
			int counter=0,i=0;
	    	while(i<s.length()) {
	    		if(s.charAt(i)=='x') {
	    			counter++;
	    		}		
	    		i++;
	    		if (counter>1)return true;
	    	}
	    	return false;
	    	}

	/*Add monom to another monom only if the power are the same
	 */
	public void add(Monom m) {
    if(this._power == m._power) {
    	this._coefficient += m._coefficient;
    }}

	/*Multiply the monom with the monom d using power rules
	 */
	public void multipy(Monom d) {
    this._coefficient *= d._coefficient;
    this._power += d._power;
		
		
	}

	/*return the monom in the format of ax^b
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String ans = "";
		if(this.isZero())return "0";
		else {
			
		ans=""+(this._coefficient);
		if(this._coefficient==0) return ans;
		if (this._power>0) 
			ans+="x"+"";
		if (this._power>1) {
			ans+="^"+"";
			ans+=""+ (this._power);
		}

		return ans;
		}
	}

	/*Compare between the monom in the diffrence of Epsilon
	 */
	public boolean equals (Object obj) {
		if(obj instanceof Monom) {
		Monom m1 = (Monom)obj;
	  if((this._power ==  m1._power) && (Math.abs(this._coefficient-m1._coefficient)<EPSILON)) {
		 return true; 
	  }
	}
		
	if(obj instanceof Polynom_able) {
		Polynom p = new Polynom(this.toString());
		return p.equals(obj);
	}
	
	
	if(obj instanceof ComplexFunction) {
		return obj.equals(this);
	}
	
		
	  return false;
	}
	

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	
	
	
	@Override
	public function initFromString(String s) {
		return new Monom(s);
	}

	@Override
	public function copy() {
		return (new Monom(this._coefficient,this._power));
	}

	private static Monom getNewZeroMonom() {return new Monom(ZERO);}


}
