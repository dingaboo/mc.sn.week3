package mc.sn.day13;

/*
  Statement : ���� ����
   - Ư���� ���, �ݺ� �۾��� �ƴҶ� ���
   
  PreparedStatement : ��ü���� ������ ������ �����͸� ����
   - ��� ������ �̸� ������ ���ѳ���, �����͸� ����, ��ü �����͵� ���� �� �ִ�.
   - ��Ű������ ������ ������ Ÿ���� �䱸�� �°� �״�� ���� �� �ִ�. -> �ѹ��� �����͸� �ɷ��ִ� ����(ex.setInt, setString)
  
   <db�� ������ �����ϴ� ����>
   1. Connection : database�� client���� ���(stream)�� ����
   2. connection�� ������ ��, (prepared)Statement�� query�� �����Ѵ�.
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
	
/*������ ���� ���� �ۼ�*/
	//���1) Statement Ȱ���Ͽ� ���� �����ϱ�
	public void insertQuery1() throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		this.makeGisaData();
		System.out.println(list.size());
		
		//gisaTBL�� �� ���� ���� ����
		StudentDTO dto=list.get(0);
		Connection conn=this.getConnection(); //�����͸� ������ db�� �����ϴ� stream ����
		
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
		sql=sb.toString(); //buffer�� string�� �ƴ� => string���� ��ȯ���־�� ��
		
		System.out.println(sql); //sql�� �� �ԷµǾ����� ����غ���
		
		Statement stmt=conn.createStatement();  //statement �����ؼ� ������ �����ϱ�
		int affectedCount=stmt.executeUpdate(sql);
		if(affectedCount>0) {
			System.out.println("�Է¿Ϸ�");
		}
		else {
			System.out.println("�Է½���");
		}
		stmt.close();
		conn.close();
	}
	
	//���2) PreparedStatement Ȱ��  -> Statement���� ȿ������ ���
	public void insertQuery2() throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		this.makeGisaData();
		//System.out.println(list.size());

		Connection conn=this.getConnection(); //Ŭ���̾�Ʈ-DB �����ϴ� "����" stream ����
			
		String sql="INSERT INTO gisaTBL VALUES (?,?,?,?,?,?,?,?,?,?,?)" ; //?�� placeholder ǥ�� : 11���� ������ �����ž�~
		PreparedStatement pstmt=conn.prepareStatement(sql); //�ؾ� �� �۾��� ����Ͽ� ���� stream ��θ� ����
		
		for(StudentDTO dto :list) {
			//��Ű�� �ʵ� Ÿ�Կ� ���߾� ���Ե� �� ������ ���� -> query1����ó�� ���� ''���� �ʿ� ����
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
				System.out.println("�Է¿Ϸ�");
			}
			else {
				System.out.println("�Է½���");
			}
		}
		pstmt.close();
		conn.close();
	}
	
	
/*gisaTBL ���� ��ȸ*/
	//quiz1) �����ڵ尡 B�� Ʃ�ð� ��ȸ�ϴ� ����
	public void selectQuery1() throws SQLException, ClassNotFoundException {
		String sql="select stdNo, email, kor, eng from gisaTBL "
					+ "where LocalCode=?";
		Connection con= this.getConnection();
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, "B");
		ResultSet rs=pstmt.executeQuery();	
		
		//java �ܼ�â���� ��� Ȯ��
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
	//quiz2) �����ڵ尡 B�̰� ����+���� ������ �ִ��� Ʃ�� ��ȸ
	public void selectQuery2() throws SQLException, ClassNotFoundException {
		String sql="select max(kor+eng) from gisaTBL "
					+ "where LocalCode=?";
		Connection con= this.getConnection();
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, "B");
		ResultSet rs=pstmt.executeQuery();	
			
		//java �ܼ�â���� ��� Ȯ��
		while(rs.next()) {
			System.out.println("max value is "+rs.getInt(1));
		}
		rs.close();
		pstmt.close();
		con.close();
	}
	
	
	//csv ������ ���� list�� ��ȯ�ؼ� �����ϴ� �۾�
	public void makeGisaData() throws NumberFormatException, IOException {
		//���Ͽ� �����ؼ� �ش� ���� �о�ͼ� �Ʒ��� ���� �и��ؼ� ����Ʈ ����
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
				i = Integer.parseInt(data[2].trim()); //int������ ��ȯ�Ͽ� �����ϱ�
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
	
	//db connection ����
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String jdbcURL="jdbc:oracle:thin:@localhost:1521:xe";
		String driver="oracle.jdbc.OracleDriver";
		String id="hr";
		String pwd="1234";
		//����̹� �ε�
		Class.forName(driver);
		//Ŀ�ؼ� ����
		Connection con=DriverManager.getConnection(jdbcURL,id,pwd);
		return con;
	}
}