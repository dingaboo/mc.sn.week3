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
		//File 인스턴스 만드시오
		File file = new File("c:\\java_dev\\data\\Abc1115.csv");
		
		if(file.exists()) {
			System.out.println(file.getName()+" 이 존재합니다.");
			//FileReader 인스턴스를 만드시오
			FileReader fr = new FileReader(file); //파일 내용 전체가 한줄에 이어서 출력됨
			BufferedReader br = new BufferedReader(fr); //fr를 한줄씩 끊어서 읽는 역할
			String line = null;
			while((line=br.readLine())!=null) { // 파일의 끝이 아닌 경우(줄이 있는 경우)
				System.out.println(line);
				break;
			}
			br.close();
			fr.close();
		}
		else {
			System.out.println("파일이 존재 하지 않습니다.");
		}
	}
}
