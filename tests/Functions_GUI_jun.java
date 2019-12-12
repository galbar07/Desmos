package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ex1.ComplexFunction;
import ex1.Functions_GUI;
import ex1.Polynom;
import ex1.complex_function;
import ex1.function;
import ex1.functions;

class Functions_GUI_jun {

	@Test
	void testSavetofile_initfromfile() throws IOException {
		functions ans = new Functions_GUI();
		int i=0;
		String test_file = "Test_file_junit.txt";
		 String st; 
		String s1 = "1.0x^2";
		String s2 = "1.0x^3";
		String s3= "Plus(1.0x,Times(1.0x,1.0x + 1.0))";
		String test_array[]=new String[3];
		Polynom p = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		ComplexFunction p3 = new ComplexFunction(new Polynom("x^2"));
		function p4 = p3.initFromString(s3);
		ans.add(p);
		ans.add(p2);
		ans.add(p4);
		ans.saveToFile(test_file);
		BufferedReader br = new BufferedReader(new FileReader(test_file)); 
		while ((st = br.readLine()) != null) {
			test_array[i++]=st;
		} 
		assertEquals(test_array[0], s1);
		assertEquals(test_array[1], s2);
		assertEquals(test_array[2], s3);
		
	}
	@Test
	void testJsonfile( )throws IOException{
		functions ans = new Functions_GUI();
		String s1 = "1.0x^2";
		String s2 = "1.0x^3";
		String s3= "Plus(1.0x,Times(1.0x,1.0x + 1.0))";
		String fileName="paramas_json_junit.txt";
		Polynom p = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		ComplexFunction p3 = new ComplexFunction(new Polynom("x^2"));
		function p4 = p3.initFromString(s3);
		ans.add(p);
		ans.add(p2);
		ans.add(p4);
		String json="{\r\n" +"	\"Width\":1000,\r\n" +"	\"Height\":600,\r\n" +"	\"Resolution\":200,\r\n" + "\"Range_X\":[-10,10],\r\n" +"	\"Range_Y\":[-5,15]\r\n" +"}";
		 BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(json);
		    writer.close(); 
		ans.drawFunctions(fileName);
		    
	}
	
	

}
