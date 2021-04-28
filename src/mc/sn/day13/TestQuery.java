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

	//���� ���� ����
	public void query1() throws ClassNotFoundException, SQLException {
		Connection con=this.getConnection();
		//1. ���̺� �� ����
		String sql="INSERT INTO testTBL2(id, userName,age) "
				+ "VALUES(6, 'kim',28)";
		//2. id=2�� username�� ku�� �����ϴ� ���� �ۼ�
		String sql2="UPDATE testTBL2 SET username='ku' WHERE id=2";
		
		//3. username�� kim�� �ڷḦ ��� ����
		String sql3= "DELETE FROM testTBL2 WHERE username='kim'";
		
		Statement stmt=con.createStatement();
		int affectedCount=stmt.executeUpdate(sql3);
		if(affectedCount>0) {
			System.out.println("�Է¿Ϸ�");
		}
		else {
			System.out.println("�Է½���");
		}
		stmt.close();
		con.close();
	}
	
	//db connection ����
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
