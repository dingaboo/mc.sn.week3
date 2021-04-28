package mc.sn.day10;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestInput {
	public static void main(String[] args) {
		TestInput tc=new TestInput();
		try {
			tc.test1();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		tc.test2();
	}
	
	//1. Scanner 활용
	public void test2() {
		Scanner sc=new Scanner(System.in);
		String line=sc.nextLine();
		sc.close();
		System.out.println(line);
	}
	
	//2. InputStream 활용
	public void test1() throws IOException {
		InputStream is=System.in;
		InputStreamReader isr=new InputStreamReader(is);
		BufferedReader br= new BufferedReader(isr);
		String line=br.readLine();
		br.close();
		isr.close();
		is.close();
		System.out.println(line);
	}
}
