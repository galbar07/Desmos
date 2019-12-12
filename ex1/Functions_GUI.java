package EX1;


import java.io.*;
import java.nio.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.awt.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;



public class Functions_GUI implements functions {
	ArrayList<function>arr_function;
	 public Functions_GUI() {
		 arr_function = new ArrayList<function>(); 
	}
	
	 //Getting strings from a file that is given and from this file initfromastring to each functions
	@Override
	public void initFromFile(String file) throws IOException {
		
		  ComplexFunction cf_intofile = new ComplexFunction(new Polynom("0"));//Complexfunctions in order to get into initfromastring
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  String st; 
		  while ((st = br.readLine()) != null) {
			  arr_function.add(cf_intofile.initFromString(st));
		  } 		
		
	}

	//printing all that i have in the array list into the given file
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

	//Drawing the functions according to width ,height and resolution that are given to me
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		if(rx.isEmpty() ||ry.isEmpty()) return;
		StdDraw.setCanvasSize(width,height);
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());
		
		////////horizontal lines
		StdDraw.setPenColor(Color.GRAY);
		for(double i=rx.get_min();i<=rx.get_max();i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		////////vertical lines
		for(double i=ry.get_min();i<=ry.get_max();i+=2) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		
		for(double i=rx.get_min();i<=rx.get_max();i++) {
			if(i!=0)
			StdDraw.text(i, -0.30,Integer.toString((int) i));
		}
		
		for(double i=ry.get_min();i<=ry.get_max();i++) {
			if(i!=0)
			StdDraw.text(-0.20,i,Integer.toString((int) i));
		}
		
		StdDraw.setPenColor(Color.black);
		StdDraw.setPenRadius(0.005);
		//y axis
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		//x axis
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		
		double x_steps= (Math.abs(rx.get_max())+Math.abs(rx.get_min()))/resolution;
		for(int i=0;i<arr_function.size();i++) {
			System.out.println(arr_function.get(i).toString());
		StdDraw.setPenColor(new Color((int)(Math.random() * 0x1000000)));	
		for(double j=rx.get_min();j<rx.get_max();j+=x_steps) {
			StdDraw.line(j, arr_function.get(i).f(j), j+(x_steps), arr_function.get(i).f(j+(x_steps)));
	}
		
		
	}
		
	}

    //Drawing the function according to the json file that is giving to me
	@Override
	public void drawFunctions(String json_file) throws IOException {
		Gson gson = new Gson();
		JsonObj obj = new JsonObj();
				
		//Option 2: from JSON file to Object
		FileReader reader = new FileReader(json_file);
		obj = gson.fromJson(reader,JsonObj.class);

		Iterator<JsonElement> iter = obj.Range_X.iterator();
		Iterator<JsonElement> iter1 = obj.Range_Y.iterator();

		Range rx = new Range(iter.next().getAsDouble(), iter.next().getAsDouble());
		Range ry = new Range(iter1.next().getAsDouble(), iter1.next().getAsDouble());

		drawFunctions(obj.Width, obj.Height, rx, ry, obj.Resolution);
		  
	}
		  
	
	
	//implemnting all of java collections method of our array list

	@Override
	public boolean add(function e) {
		return arr_function.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return arr_function.addAll(c);
	}

	@Override
	public void clear() {
		arr_function.clear();	
	}

	@Override
	public boolean contains(Object o) {
		return arr_function.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return arr_function.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return arr_function.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return arr_function.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return arr_function.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return arr_function.retainAll(c);
	}

	@Override
	public int size() {
		return arr_function.size();
	}

	@Override
	public Object[] toArray() {
		return arr_function.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return arr_function.toArray(a);
	}

	public Iterator<function> iteretor() {
		Iterator <function> itr = arr_function.iterator();
		return itr;
	}
	
	//our class of jason object that we are taking the jason string inside of him

	public static class JsonObj{
		 int Width;
		 int Height;
		 int Resolution;
		 JsonArray Range_X;
		 JsonArray Range_Y;
		 
		 
		
		
		
	}


}
