import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySql {
    private Connection connection = null;
    private Statement statement = null;
    private String server = "localhost";
    private String database = "tracker";//db name
    private String user_name = "root"; //id
    private String password = "bbang0105@@";//password

    //mysql driver loading
    public void DriverLoading() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //mysql 연결
    public void Connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&useUnicode=true&characterEncoding=utf8", user_name, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    //mysql 연결 끊기.
    public void DisConnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("정상적으로 해제되었습니다.");
            }
        } catch (SQLException e) {
        }
    }

    //기존에 남아있던 table 데이터 초기화.
    public void ResetTable() throws SQLException {
        //쿼리 수행을 위한 Statement 객체 생성.
        statement = connection.createStatement();
        //실행할 때 마다 새로운 데이터를 입력하기 위해 table 내에 남아있는 데이터 삭제.
        String removeSql = "delete from word_table";

        statement.executeUpdate(removeSql);
    }

    //table에 데이터 입력.
    public void Insert(String word, int frequency, String time) throws SQLException {
        //SQL 쿼리 작성.
        String insertSql = "insert into word_table (data1,data2,data3) values (" + "\"" + word + "\"" + ", " + frequency + "," + "\"" + time + "\"" + ")";
        //SQL 쿼리 실행.
        statement.executeUpdate(insertSql);

    }


}
