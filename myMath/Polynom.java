package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */

public class Polynom implements Polynom_able{
    public ArrayList<Monom> list_monom; 
	Monom_Comperator comp;
 
    //deafult constructor build a new polynom with "0" Monom
	public Polynom() {
		list_monom = new ArrayList<Monom>();
		list_monom.add(new Monom("0"));
		comp = new Monom_Comperator();		
	}

	//recieve a String and try to make it into an arraylist of Monoms in Polynom
	public Polynom(String s) {
		list_monom = new ArrayList<Monom>();
		comp = new Monom_Comperator();
		String [] arr=s.split("(?=\\+|-)");
		for (int i = 0; i < arr.length; i++) {
			this.add(new Monom(arr[i]));
			
		}
	    
		list_monom.sort(comp);
		
	}
	
	//Recieve a double variable and returns the value at the point x
	@Override
	public double f(double x) {
    double sum =0;
    for(int i=0;i<this.list_monom.size();i++) {
    	sum+= list_monom.get(i).f(x);
    }
		return sum;
	}
/* 
 * Recieve Polynom_able and add it to Polynom using itreator 
 * @see myMath.Polynom_able#add(myMath.Polynom_able)
 */
	@Override
	public void add(Polynom_able p1) {
	 Iterator<Monom> itr = p1.iteretor();
	 while(itr.hasNext()) {
		Monom m1 = itr.next();
		this.add(m1);
     }};
		
    /*Recieve Monom and add it to the polynom itself 
     * if the polynom is empty then add it,if it has the same power add it to each other
     * else add it and use list.sort using the comprater
     * @see myMath.Polynom_able#add(myMath.Monom)
     */
	@Override
	public void add(Monom m1) {
	comp = new Monom_Comperator();
	if(list_monom.size() == 0) {
		list_monom.add(new Monom(m1));
	}
	else {
	boolean flag = false;
	for (int i = 0; i < list_monom.size(); i++) {
		if(list_monom.get(i).get_power() == m1.get_power()) {
			list_monom.get(i).add(m1);
			
			if(list_monom.get(i).isZero()) {//in case x-x=0
				list_monom.remove(i);
				list_monom.add(new Monom("0"));
			}
			flag =true;
			break;
		}
	}
	if(!flag) {
		list_monom.add(new Monom(m1));
		}
	}
	list_monom.sort(comp);
	}
	
	
	/*Recieve Polynom_able and create new polynom and then multiply it by -1
	 * and then add it to the current polynom
	 * @see myMath.Polynom_able#substract(myMath.Polynom_able)
	 */
	@Override
	public void substract(Polynom_able p1) {
		Polynom_able p2 = p1.copy();
	     Monom m = new Monom("-1");
	     p2.multiply(m);
		 this.add(p2);
	}

	/* Recieve Polynom_able and than multply it with another polynom
	 * using a new polynom and then copy the list to the current polynom
	 * @see myMath.Polynom_able#multiply(myMath.Polynom_able)
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Iterator<Monom> itr_able = p1.iteretor();
		Polynom p3 = new Polynom();
		while (itr_able.hasNext()) {
		Monom m = itr_able.next();	 
		for (int i = 0; i < list_monom.size(); i++) {
			Monom mult = new Monom(list_monom.get(i));
			 mult.multipy(m);
			 p3.add(mult);
		}	
   }
	this.list_monom =p3.list_monom ;
	}
			

	/*Recieve Polynom_able and compare the Monoms between them 
	 * in the duffrence of Epsilon
	 * @see myMath.Polynom_able#equals(myMath.Polynom_able)
	 */
	@Override
	public boolean equals(Object p1) {
		if(p1 instanceof Polynom) {
		 Polynom p1_iterotor= (Polynom)p1;
		Iterator<Monom> itr= p1_iterotor.iteretor();
		int i=0;
		while (i< list_monom.size() && itr.hasNext())
	{
			Monom m1= itr.next();
			if (!(list_monom.get(i).equals(m1))) {
				return false;
			}
			i++;
		}
		if (i==list_monom.size() && !itr.hasNext()) return true;
		return false;
	}
		return false;
	}

	/*checks to see if the Polynom is the zero polynom
	 * @see myMath.Polynom_able#isZero()
	 */
	@Override
	public boolean isZero() {
		if (list_monom.size()==0) return false; //if havent polinom itsnt zero
		return list_monom.get(0).get_coefficient()==0; //check if after sort if the first elemnt is zero
	}

	/*checks to see if there exists a point c such that f(c)=0 between x0 and x1
	 * by checking each time that f(x0)>0 and f(x1)<0 without loss of generality
	 * and then find the middle between the points = mid 
	 * without loss of generality  switch x1 = mid
	 * and check again until f(mid)>epsilon
	 * and then return mid  
	 * 
	 * @see myMath.cont_function#root(double, double, double)
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if (f(x0)==0) return x0; 
		if (f(x1)==0) return x1;
		if (f(x0)*f(x1)>0 )
			throw new RuntimeException("This function in these points does not have cutting points");
		double mid=(x1+x0)/2;
	
		
		while ((f(x0)>0 && f(x1)<0) || (f(x0)<0 && f(x1)>0) && Math.abs(f(mid))>eps)
	    {
		mid=(x1+x0)/2;
		if (f(mid)==0) return mid;
		
			if((f(x0)>0 && f(x1)<0)) { // if fx0 positive and fx1 negetive
				if (f(mid)>0) { x0=mid;}
				else 
				{
				x1=mid;
				}
			}
			else{ // if fx0 negetive anf fx1 positive
				if (f(mid)>0) {
					x1=mid;
				}
				else 
				{
				x0=mid;
				}
			}
			
		}
		return mid;
	}

	/*
	 * create new Polynom using the current polynom by deep copy semantic 
	 * and return the new polynom
	 * @see myMath.Polynom_able#copy()
	 */
	@Override
	public Polynom_able copy() {
		Polynom p = new Polynom();
		for (int i = 0; i < this.list_monom.size(); i++) {
			p.add(new Monom(list_monom.get(i)));
		}
	return p; 	
	}

    /*create new Polynom using the current polynom and do a derative to it
     * @see myMath.Polynom_able#derivative()
     */
	@Override
	public Polynom_able derivative() {
		Polynom p = new Polynom();
		for (int i = 0; i < this.list_monom.size(); i++) {
			p.add(new Monom(list_monom.get(i).derivative()));
		}
		return p;
	}

	/**
	 * Calculate function area between xo and x1, only the postive area!
	 * if x0 > x1 than swap between them
	 * by using division into small rectangles (|x1-x0|/eps) and than multiply them in epsilon
	 * and then sum them all , stop when we reach x1
	 * 
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if (x0>x1) return 0; 
		double area=0;
		for ( double i = x0; i < x1; i+=eps) {
			if (f(i)>0)
			area+= f(i)*eps;
		}
		return area;
	}

	/*Create new itreator and return it
	 * @see myMath.Polynom_able#iteretor()
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator <Monom> itr = list_monom.iterator();
		return itr;
	}
	
	/*Receive Monom and multiply it in the Polynom and update the Polynom
	 * @see myMath.Polynom_able#multiply(myMath.Monom)
	 */
	@Override
	public void multiply(Monom m1) {
	
	for(int i=0;i<this.list_monom.size();i++) {
		list_monom.get(i).multipy(m1);
	 }	
	}
	
	/*print the Polynom in the format of f(x)=...
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
	String res= "";
	if(this.isZero()) {
		return res + "0";
	}
	else {
		res+= this.list_monom.get(0).toString();
	for(int i = 1; i < list_monom.size(); i++) {
		if(list_monom.get(i).isZero()) {
			res += "";
		 }
	else if(list_monom.get(i).get_coefficient()>0) {
		res+= " + " +list_monom.get(i).toString();
	 }
	else {
		res+=" - " + list_monom.get(i).toString().replace("-", ""); 
	}}
	return res;
		}
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
