package myMath;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.google.gson.Gson;


public class Functions_GUI implements functions {
	ArrayList<function>arr_function;
	 public Functions_GUI() {
		 arr_function = new ArrayList<function>();
		 arr_function.add(new Polynom("x^2"));
	}
	
	@Override
	public void initFromFile(String file) throws IOException {
		
			
		 
		
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		 // the function y = sin(4x) + sin(20x), sampled at n+1 points
        // between x = 0 and x = pi
		int n=100;
        double[] x = new double[n+1];
        double[] y = new double[n+1];
        for (int i = 0; i <= n; i++) {
            x[i] = this.arr_function.get(0).f(i);
            y[i] = this.arr_function.get(0).f(i);
            
        }

        // rescale the coordinate system
        StdDraw.setXscale(0, Math.PI);
        StdDraw.setYscale(-10, 10);

        // plot the approximation to the function
        for (int i = 0; i < n; i++) {
            StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
        }
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

	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}

}
