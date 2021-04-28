package mc.sn.day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GisaTest {
	private ArrayList<StudentDTO> list;
	
	public GisaTest() {
		list = new ArrayList<StudentDTO>();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GisaTest gt = new GisaTest();
		try {
			gt.makeGisaData();
			int answer = gt.quiz3();
			gt.printAnswer(3, answer);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void printAnswer(int number,int answer) throws IOException {
		String fileName = "Ans"+number+".txt";
		File file = new File(fileName);
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(answer);
		pw.close();
		fw.close();
	}
	
	// 문제3번의 코드를 수정하여 4번을 풀어보세요
	public int quiz4() {
		// 성취도가 A . B인 자료에 대해 국어점수+ 점수포인트의 합계가 50이상인 건수를 구하시오
		
		int count  = 0;
		for (StudentDTO dto : list) {
			if(dto.getAccPoint().equals("A")||dto.getAccPoint().equals("B")) {
				int point =5; //A
				if (dto.getLocalcode().equals("B")) {
					point = 10;
				} else if(dto.getLocalcode().equals("C")) {
					point = 15;
				}
				
				if(dto.getKor()+point>=50) {
					count++;
				}
			}
		}
		System.out.println("answer4 is "+count);
		return count;
	}
	
	public int quiz3() {
		// 영어 + 수학 점수의 합이 120점 이상인 자료에 대해 총점+ 점수포인트의 합계를 구하시오
		// 4시 20분까지 코드를 작성하세요
		int total  = 0;
		for (StudentDTO dto : list) {
			int sum = dto.getEng()+dto.getMath();
			if(sum>=120) {
				int point = 5; //A
				if (dto.getMgrCode().equals("B")) {
					point = 15;
				} else if(dto.getMgrCode().equals("C")) {
					point = 20;
				} 
				total = total + (dto.getTotal()+point);
			}
		}
		System.out.println("answer3 is "+total);
		return total;
	}
	
	public void quiz2() {
		System.out.println(list.size());
		// 지역변수가 B이고 국어+영어점수가 최고인 점수를 출력하시오 (max값 구하기)
		// 3시 30분까지
		StudentDTO tempDTO = list.get(0);
		int max = tempDTO.getKor()+tempDTO.getEng();
		for(StudentDTO dto : list) {
			if(dto.getLocalcode().equals("B")) {
				int score = dto.getEng()+dto.getKor();
				if(max < score) {
					max = score;
				}
			}
		}
		System.out.println("answer is "+max);
	}
	
	public void makeGisaData() throws NumberFormatException, IOException {
		//파일에 접속해서 해당 라인 읽어와서 아래와 같이 분리해서 리스트 저장
		File file = new File("C:\\java_dev\\data\\Abc1115.csv");
		if(file.exists()) {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine())!=null) {
				//990001,addx, 17, 29, 16, 49, 43,154,C,A,C
				String temp = line;//"990001,addx, 17, 29, 16, 49, 43,154,C,A,C";
				//위의 스트링을 ,로 분리하여 StudentDTO 인스턴스를 하나 생성하고
				//생성된 인스턴스를 출력하여 셋팅한 자료가 제대로 들어갔는지 확인하는 코드 생성
				//2시20분까지 하세요.
				String[] data = temp.split(",");
				StudentDTO dto = new StudentDTO();
				int i = Integer.parseInt(data[0]);
				dto.setStdNo(i);
				//990001을 int형으로 바꾸어야 한다 --> 어떻게?
				dto.setEmail(data[1]);
				i = Integer.parseInt(data[2].trim());
				dto.setKor(i);
				dto.setEng(Integer.parseInt(data[3].trim()));
				dto.setMath(Integer.parseInt(data[4].trim()));
				dto.setSci(Integer.parseInt(data[5].trim()));
				dto.setHist(Integer.parseInt(data[6].trim()));
				dto.setTotal(Integer.parseInt(data[7].trim()));
				dto.setMgrCode(data[8]);
				dto.setAccPoint(data[9]);
				dto.setLocalcode(data[10]);
				//System.out.println(dto);
				list.add(dto);
			}
			br.close();
			fr.close();
		}
	}

}