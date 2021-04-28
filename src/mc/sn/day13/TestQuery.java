package mc.sn.day13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestQuery {
	public static void main(String[] args) {	
		TestQuery tq=new TestQuery();
		try {
			tq.query1();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//쿼리 실행 예제
	public void query1() throws ClassNotFoundException, SQLException {
		Connection con=this.getConnection();
		//1. 테이블 값 삽입
		String sql="INSERT INTO testTBL2(id, userName,age) "
				+ "VALUES(6, 'kim',28)";
		//2. id=2인 username을 ku로 변경하는 쿼리 작성
		String sql2="UPDATE testTBL2 SET username='ku' WHERE id=2";
		
		//3. username이 kim인 자료를 모두 삭제
		String sql3= "DELETE FROM testTBL2 WHERE username='kim'";
		
		Statement stmt=con.createStatement();
		int affectedCount=stmt.executeUpdate(sql3);
		if(affectedCount>0) {
			System.out.println("입력완료");
		}
		else {
			System.out.println("입력실패");
		}
		stmt.close();
		con.close();
	}
	
	//db connection 수행
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String jdbcURL="jdbc:oracle:thin:@localhost:1521:xe";
		String driver="oracle.jdbc.OracleDriver";
		String id="hr";
		String pwd="1234";
		Class.forName(driver);
		Connection con=DriverManager.getConnection(jdbcURL,id,pwd);
		return con;
	}
}
