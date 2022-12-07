package tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Account {
	
	String userName="shubhanshu";
	transient String password="1234";
	
	private void writeObject(ObjectOutputStream os) throws Exception{
		os.defaultWriteObject();
		String encryptedPwd="123"+password;
		os.writeObject(encryptedPwd);
	}
	
	private void readObject(ObjectInputStream is) throws Exception{
		is.defaultReadObject();
		String encryptedPwd=(String)is.readObject();
		password=encryptedPwd.substring(3);
	}
	
	public static void main(String[] args) throws IOException {
		
		Account ac=new Account();
		
		FileOutputStream file=new FileOutputStream(System.getProperty("user.dir")+"/myFile.txt");
		ObjectOutputStream oos=new ObjectOutputStream(file);
		oos.writeObject(ac);
		
		//But when you read object from file you will get value of password variable as null 
		//because this variable is transient
	}	
		
}
