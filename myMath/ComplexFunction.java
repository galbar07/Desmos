package myMath;

import java.util.Stack;

public class ComplexFunction implements complex_function {
    private function left;
    private function right;
    Operation op;
    public static final double EPSILON = 0.0000001;

	public ComplexFunction(function f) {
	this.op=Operation.None;
	this.left = f.copy();
	this.right=new Polynom("0");	
	}
	
	public ComplexFunction(String root,function left,function right ) {
		this.op=valid_opreation(root);
		this.left=left.copy();
		this.right=right.copy();
		
		
	}	
	public ComplexFunction(Operation root,function left,function right ) {
	this.op=root;
	this.left=left.copy();
	this.right=right.copy();		
	}
	
	
	@Override
	public double f(double x) {
		
		double sum=0;
		switch(this.op) {
		case Plus:
			return this.left.f(x)+this.right.f(x);
		case Times:
			return sum+this.left.f(x)*this.right.f(x);
		case Divid:
			return sum+this.left.f(x)/this.right.f(x);
		case Max:
			return sum+Math.max(this.left.f(x), this.right.f(x));
		case Min:
			return sum+Math.min(this.left.f(x), this.right.f(x));
		case Comp:
			return sum+this.left.f(this.right.f(x));
		case None:
			break; //check with Boaz
		case Error:
			throw new RuntimeException("unknown opreations");
		}
		
		if (this.left instanceof Polynom) {
			Polynom temp = (Polynom)this.left;
			sum += temp.f(x);
		}
		if (this.right instanceof Polynom) {
			Polynom temp = (Polynom)this.right;
			sum+= temp.f(x);
		}
		if (this.right instanceof Monom) {
			Monom temp = (Monom)this.right;
			sum+= temp.f(x);
		}
		if (this.left instanceof Monom) {
			Monom temp = (Monom)this.left;
			sum+= temp.f(x);
		}
		return -1;
	}

	public function initFromString(String s) {
		s=s.toLowerCase();
		//break case
		if (!s.contains(",")){
			return new Polynom(s);
		}
		if (!checkParenthesisComma(s)) {
			  throw new RuntimeException("ERR in the String Parenthesis");
		}

		function f= recInitinitFromString(s);
		
		return f;
		
	}
    
	public boolean equals(Object obj) {
		if(!(obj instanceof function)) return false;
			function f = (function)obj;
		for(double i=-1;i<1;i+=EPSILON) {
			if(this.f(i)!=f.f(i)) {
				return false;
			}
		}
		return true;
	
		
		
	
	}


	@Override
	public function copy() {
		function left= this.left.copy();
		function right = this.right.copy();
		return new ComplexFunction(this.op,left,right);
	}

	@Override
	public void plus(function f1) {
	this.left=this.copy();
	this.op=Operation.Plus;
	this.right= f1;
		
	}
	
	@Override
	public void mul(function f1) {
		this.left=this.copy();
		this.op=Operation.Times;
		this.right= f1;	
	}

	@Override
	public void div(function f1) {
		this.left=this.copy();
		this.op=Operation.Divid;
		this.right= f1;
	}

	@Override
	public void max(function f1) {
		this.left=this.copy();
		this.op=Operation.Max;
		this.right= f1;
	}

	@Override
	public void min(function f1) {
		this.left=this.copy();
		this.op=Operation.Min;
		this.right= f1;
	}

	@Override
	public void comp(function f1) {
		this.left=this.copy();
		this.op=Operation.Comp;
		this.right= f1;
	
	}

	@Override
	public function left() {
		return this.left.copy();
	}

	@Override
	public function right() {
		return this.right.copy();
	}

	@Override
	public Operation getOp() {
		return this.op;
	}
	
	public String toString() {
		String ans= this.op + "(";
		return ans+ this.left.toString()+","+this.right.toString()+")";
		
	}
	
	private static Operation valid_opreation(String op) {
		op=op.toLowerCase();
		if(op.equals("plus"))return Operation.Plus;
		else if(op.equals("times")||op.equals("mul"))return Operation.Times;
		else if(op.equals("div")||op.equals("divid"))return Operation.Divid;
		else if(op.equals("none"))return Operation.None;
		else if(op.equals("max"))return Operation.Max;
		else if(op.equals("min"))return Operation.Min;
		else if(op.equals("comp"))return Operation.Comp;
		else if(op.equals("error")) return Operation.Error;
		else { throw new RuntimeException("ERROR OPREATOR");}
		
	}
	
	private int indexParentParenthesis(String s) {
		Stack <Character> st = new Stack <Character>();
		if (!s.contains("(")) {
			return s.indexOf(",")+1;
		}

		int index= s.indexOf('(');
		int index_psik= s.indexOf(',');
		if (index_psik<index) return index_psik+1;
		st.push('(');
		for (int i = index+1; i < s.length()-1; i++) {
			char curr= s.charAt(i);
			if (curr=='(') {
				st.push(curr);
			}
			if (curr == ')') {
				st.pop();
			}
			if (st.empty() ) return i+1;
		}
		return -1;
	}


	private boolean checkParenthesisComma(String s) { // Check for balanced parentheses
		Stack <Character> st = new Stack <Character>();
		Stack <Character> stComma = new Stack <Character>();
		for (int i = 0; i < s.length(); i++) {
			char curr = s.charAt(i);
			if (curr== '(') {
				st.push(curr);
				stComma.push(curr);}
				if (curr == ')') {
					if (st.isEmpty()) { // if ((st.isEmpty()? return false : st.pop
				return false;
			}
			else {
				st.pop();
			}
			
		}
		if (curr == ',') {
			if (stComma.isEmpty()) { // if ((st.isEmpty()? return false : st.pop
				return false;
			}
			else {
				stComma.pop();
			}
		}
	}
	return st.isEmpty() && stComma.isEmpty();
}
	

	private function recInitinitFromString(String s) {

	int index_left=s.indexOf('(');
	String op=s.substring(0,index_left);
	int index = index_left+ indexParentParenthesis(s.substring(index_left+1, s.length()-1));
	int size= index;
	if (s.charAt(size)== ')') size++;
	if (s.charAt(size)=='^') size++;
	String left= s.substring(index_left+1,size);
	if (s.charAt(index+1)==',') index++;
	size = s.length()-1;
	if (s.charAt(size)==')') size--;
	String right = s.substring(index+1,size+1);
	
	Operation options=valid_opreation(op);
		
	return new ComplexFunction(options,initFromString(left),initFromString(right));
	
}




}

