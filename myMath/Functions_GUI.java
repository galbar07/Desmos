package myMath;


import java.io.*;
import java.nio.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.awt.*;
import com.google.gson.Gson;



public class Functions_GUI implements functions {
	ArrayList<function>arr_function;
	 public Functions_GUI() {
		 arr_function = new ArrayList<function>(); 
	}
	
	@Override
	public void initFromFile(String file) throws IOException {
		
		  ComplexFunction cf_intofile = new ComplexFunction(new Polynom("0"));//Complexfunctions in order to get into initfromastring
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  String st; 
		  while ((st = br.readLine()) != null) {
			  arr_function.add(cf_intofile.initFromString(st));
		  } 		
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		 BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter(file));
		  for (int i = 0; i < arr_function.size(); i++) {
		    outputWriter.write(arr_function.get(i).toString());
		    outputWriter.newLine();
		  }
		  outputWriter.flush();  
		  outputWriter.close();  
	
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width,height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		drawfunctions(arr_function.get(0),rx,ry);

		
	}
	private void drawfunctions(function f,Range rx,Range ry) {
		int x_points[]=new int[(int) (Math.abs(rx.get_min())+Math.abs(rx.get_max()))];
		int y_points[]=new int[(int) (Math.abs(ry.get_min())+Math.abs(ry.get_max()))];
		double count=rx.get_min();
		for(int i=0;i<x_points.length;i++) {
			x_points[i]=(int) f.f(count++);
		}
		count = ry.get_min();
		for(int i=0;i<y_points.length;i++) {
			y_points[i]=(int) f.f(count++);
		}
		Polygon p = new Polygon();
		StdDraw.polygon(x_points, y_points);
		
		
		
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean add(function e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}



}
