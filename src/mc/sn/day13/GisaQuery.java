package mc.sn.day13;

/*
  Statement : 고정 쿼리
   - 특이한 경우, 반복 작업이 아닐때 사용
   
  PreparedStatement : 전체적인 구조는 같은데 데이터만 변함
   - 사용 구조만 미리 컴파일 시켜놓고, 데이터를 보냄, 객체 데이터도 보낼 수 있다.
   - 스키마에서 정의한 데이터 타입을 요구에 맞게 그대로 보낼 수 있다. -> 한번더 데이터를 걸러주는 역할(ex.setInt, setString)
  
   <db로 데이터 전송하는 과정>
   1. Connection : database와 client간의 통로(stream)을 생성
   2. connection이 생성된 후, (prepared)Statement로 query를 전송한다.
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GisaQuery {
	private ArrayList<StudentDTO> list;
	
	public GisaQuery() {
		list=new ArrayList<StudentDTO>();
	}
	
	public static void main(String[] args) {	
		GisaQuery tq=new GisaQuery();
		try {
			tq.insertQuery1();
			tq.insertQuery2();
			
			tq.selectQuery1();
			tq.selectQuery2();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
/*데이터 삽입 쿼리 작성*/
	//방법1) Statement 활용하여 쿼리 전송하기
	public void insertQuery1() throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		this.makeGisaData();
		System.out.println(list.size());
		
		//gisaTBL에 한 줄의 값을 삽입
		StudentDTO dto=list.get(0);
		Connection conn=this.getConnection(); //데이터를 저장할 db로 연결하는 stream 생성
		
		String sql="INSERT INTO gisaTBL VALUES (" ;
		StringBuffer sb=new StringBuffer(sql);
		sb.append(dto.getStdNo()+",'");
		sb.append(dto.getEmail()+"',");
		sb.append(dto.getKor()+",");
		sb.append(dto.getEng()+",");
		sb.append(dto.getMath()+",");
		sb.append(dto.getSci()+",");
		sb.append(dto.getHist()+",");
		sb.append(dto.getTotal()+",'");
		sb.append(dto.getMgrCode()+"','");
		sb.append(dto.getAccPoint()+"','");
		sb.append(dto.getLocalcode()+"')");
		sql=sb.toString(); //buffer는 string이 아님 => string으로 변환해주어야 함
		
		System.out.println(sql); //sql문 잘 입력되었는지 출력해보기
		
		Statement stmt=conn.createStatement();  //statement 생성해서 쿼리문 실행하기
		int affectedCount=stmt.executeUpdate(sql);
		if(affectedCount>0) {
			System.out.println("입력완료");
		}
		else {
			System.out.println("입력실패");
		}
		stmt.close();
		conn.close();
	}
	
	//방법2) PreparedStatement 활용  -> Statement보다 효율적인 방식
	public void insertQuery2() throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		this.makeGisaData();
		//System.out.println(list.size());

		Connection conn=this.getConnection(); //클라이언트-DB 연결하는 "전용" stream 생성
			
		String sql="INSERT INTO gisaTBL VALUES (?,?,?,?,?,?,?,?,?,?,?)" ; //?로 placeholder 표시 : 11개의 데이터 보낼거야~
		PreparedStatement pstmt=conn.prepareStatement(sql); //해야 할 작업을 명시하여 전용 stream 통로를 만듬
		
		for(StudentDTO dto :list) {
			//스키마 필드 타입에 맞추어 삽입될 각 데이터 전송 -> query1에서처럼 직접 ''해줄 필요 없음
			pstmt.setInt(1, dto.getStdNo());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMath());
			pstmt.setInt(6, dto.getSci());
			pstmt.setInt(7, dto.getHist());
			pstmt.setInt(8, dto.getTotal());
			pstmt.setString(9, dto.getMgrCode());
			pstmt.setString(10, dto.getAccPoint());
			pstmt.setString(11,dto.getLocalcode());
			
			int affectedCount=pstmt.executeUpdate();	
			if(affectedCount>0) {
				System.out.println("입력완료");
			}
			else {
				System.out.println("입력실패");
			}
		}
		pstmt.close();
		conn.close();
	}
	
	
/*gisaTBL 내용 조회*/
	//quiz1) 지역코드가 B인 튜플값 조회하는 쿼리
	public void selectQuery1() throws SQLException, ClassNotFoundException {
		String sql="select stdNo, email, kor, eng from gisaTBL "
					+ "where LocalCode=?";
		Connection con= this.getConnection();
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, "B");
		ResultSet rs=pstmt.executeQuery();	
		
		//java 콘솔창에서 결과 확인
		while(rs.next()) {
			int stdNo=rs.getInt("stdNo");
			String email=rs.getString("email");
			int kor=rs.getInt("kor");
			int eng=rs.getInt("eng");
			System.out.println(stdNo+","+email+","+kor+","+eng);
		}
		rs.close();
		pstmt.close();
		con.close();
	}
	//quiz2) 지역코드가 B이고 국어+영어 점수가 최대인 튜플 조회
	public void selectQuery2() throws SQLException, ClassNotFoundException {
		String sql="select max(kor+eng) from gisaTBL "
					+ "where LocalCode=?";
		Connection con= this.getConnection();
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, "B");
		ResultSet rs=pstmt.executeQuery();	
			
		//java 콘솔창에서 결과 확인
		while(rs.next()) {
			System.out.println("max value is "+rs.getInt(1));
		}
		rs.close();
		pstmt.close();
		con.close();
	}
	
	
	//csv 데이터 파일 list로 변환해서 저장하는 작업
	public void makeGisaData() throws NumberFormatException, IOException {
		//파일에 접속해서 해당 라인 읽어와서 아래와 같이 분리해서 리스트 저장
		File file = new File("C:\\dev\\data\\Abc1115.csv");
		if(file.exists()) {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine())!=null) {
				String temp = line;
				String[] data = temp.split(",");
				StudentDTO dto = new StudentDTO();
				int i = Integer.parseInt(data[0]);
				dto.setStdNo(i);
				dto.setEmail(data[1]);
				i = Integer.parseInt(data[2].trim()); //int형으로 변환하여 추출하기
				dto.setKor(i);
				dto.setEng(Integer.parseInt(data[3].trim()));
				dto.setMath(Integer.parseInt(data[4].trim()));
				dto.setSci(Integer.parseInt(data[5].trim()));
				dto.setHist(Integer.parseInt(data[6].trim()));
				dto.setTotal(Integer.parseInt(data[7].trim()));
				dto.setMgrCode(data[8]);
				dto.setAccPoint(data[9]);
				dto.setLocalcode(data[10]);
				list.add(dto);
			}
			br.close();
			fr.close();
		}
	}
	
	//db connection 수행
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String jdbcURL="jdbc:oracle:thin:@localhost:1521:xe";
		String driver="oracle.jdbc.OracleDriver";
		String id="hr";
		String pwd="1234";
		//드라이버 로딩
		Class.forName(driver);
		//커넥션 생성
		Connection con=DriverManager.getConnection(jdbcURL,id,pwd);
		return con;
	}
}