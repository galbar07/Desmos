package myMath;



public class ComplexFunction implements complex_function {
    private function left;
    private function right;
    Operation op;

	public ComplexFunction(function f) {
	this.op=Operation.None;
	this.left = f.copy();
	this.right=new Polynom("0");	
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
		System.out.println("No Opertion..."); // if the String isn't has opertion
		return -1;
	}

	@Override
	public function initFromString(String s) {
		//break case
		if (!s.contains(",")){
			return new Polynom(s);
		}
		
		int index_left=s.indexOf('(');
		int index_comma=s.indexOf(',');
		String op=s.substring(0,index_left);
		String left= s.substring(index_left+1,index_comma);
		String right = s.substring(index_comma+1,s.length()-1);
		Operation options=Operation.Plus;
		if(op.equals("plus"))options=Operation.Plus;
		if(op.equals("Times"))options=Operation.Times;
		if(op.equals("divid"))options=Operation.Divid;
		
		
		return new ComplexFunction(options,initFromString(left),initFromString(right));
		
	}

	@Override
	public function copy() {//fix this code
		return new ComplexFunction(this.op,this.left.copy(),this.right.copy());
	}

	@Override
	public void plus(function f1) {
	ComplexFunction cf_temp= new ComplexFunction(this.op,this.left,this.right);
	this.left=cf_temp;
	this.op=Operation.Plus;
	this.right= f1;
		
	}
	
	@Override
	public void mul(function f1) {
		ComplexFunction cf_temp= new ComplexFunction(this.op,this.left,this.right);
		this.left=cf_temp;
		this.op=Operation.Times;
		this.right= f1;	
	}

	@Override
	public void div(function f1) {
		ComplexFunction cf_temp= new ComplexFunction(this.op,this.left,this.right);
		this.left=cf_temp;
		this.op=Operation.Divid;
		this.right= f1;
	}

	@Override
	public void max(function f1) {
		ComplexFunction cf_temp= new ComplexFunction(this.op,this.left,this.right);
		this.left=cf_temp;
		this.op=Operation.Max;
		this.right= f1;
	}

	@Override
	public void min(function f1) {
	
		ComplexFunction cf_temp= new ComplexFunction(this.op,this.left,this.right);
		this.left=cf_temp;
		this.op=Operation.Min;
		this.right= f1;
	}

	@Override
	public void comp(function f1) {
		ComplexFunction cf_temp= new ComplexFunction(this.op,this.left,this.right);
		this.left=cf_temp;
		this.op=Operation.Comp;
		this.right= f1;
	
	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}
	
	public String tostring() {//return to this
		 		return "";

		
	}

  




}

