package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationDemo implements Serializable{
	
	transient final int i=10;
	 int j=20;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		SerializationDemo sd=new SerializationDemo();
		
		FileOutputStream file=new FileOutputStream(System.getProperty("user.dir")+"/myFile.txt");
		ObjectOutputStream oos=new ObjectOutputStream(file);
		oos.writeObject(sd); //here 10 (only final variable value) goes to file and j=20 goes to file
		
		FileInputStream file1=new FileInputStream(System.getProperty("user.dir")+"/myFile.txt");
		ObjectInputStream ois=new ObjectInputStream(file1);
		SerializationDemo sd1=(SerializationDemo) ois.readObject();
		
		System.out.println(sd1.i+"====="+sd1.j);
		//So here 10 (value only) is coming from file and j=20 is coming from file.

	}

}
