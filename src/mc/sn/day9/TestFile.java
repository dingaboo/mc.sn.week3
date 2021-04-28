package mc.sn.day9;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class TestFile {
	public static void main(String[] args) {
		TestFile tf = new TestFile();
		try {
			tf.connectFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connectFile() throws IOException {
		//File �ν��Ͻ� ����ÿ�
		File file = new File("c:\\java_dev\\data\\Abc1115.csv");
		
		if(file.exists()) {
			System.out.println(file.getName()+" �� �����մϴ�.");
			//FileReader �ν��Ͻ��� ����ÿ�
			FileReader fr = new FileReader(file); //���� ���� ��ü�� ���ٿ� �̾ ��µ�
			BufferedReader br = new BufferedReader(fr); //fr�� ���پ� ��� �д� ����
			String line = null;
			while((line=br.readLine())!=null) { // ������ ���� �ƴ� ���(���� �ִ� ���)
				System.out.println(line);
				break;
			}
			br.close();
			fr.close();
		}
		else {
			System.out.println("������ ���� ���� �ʽ��ϴ�.");
		}
	}
}
