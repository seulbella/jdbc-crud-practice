package com.ohgiraffers.common;

import com.mysql.cj.jdbc.Driver;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {
        /* Properties: 키(key)와 값(value)의 쌍으로 이루어진 속성을 저장하는 데 사용된다.
        * 주로 구성 파일이나 설정 파일에서 데이터를 읽거나 쓰는 데 사용된다. */
        Properties prop = new Properties();

        /* 자바에서는 지역 변수를 선언과 동시에 초기화 초기화 해줘야 한다.
        * 그 이유는 만약 코드가 특정 조건에 따라 getConnection 메서드를 호출하지 못하거나
        * 예외가 발생하여 con에 할당되지 않을 수도 있기 때문이다.  */
        Connection con = null;

        try {
            /* FileReader: 'java.io.Reader' 클래스의 하위 클래스로, 파일에서 문자 단위로 읽을 때 사용 된다.
             * 자바에서 입출력 작업을 할 때 입출력 메소드가 'IOException'을 던질 수 있기 때문에 해당 클래스도 예외처리를 해주어야 한다.
             * 그래서 try/catch 블록을 사용해 예외처리를 한다. */
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            /* driver와 url를 변수화 */
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            /* JDBC 데이터베이스에 연결 전, 아래 코드를 호출하면 지정된 드라이버 클래스를 동적으로 로드할 수 있다.
            * 로드하면 해당 드라이버의 정적 블록이 실행되어 해당 드라이버가 DriverManager에 등록되고 사용할 수 있게 된다. */
            Class.forName(driver);

            /* JDBC를 사용하여 데이터베이스에 연결하는 코드, 즉 아래 메서드는 데이터베이스에 연결된
            * Connection 객체를 반환한다. (매개변수에 url, prop(아이디, 비밀번호 등)가 설정하여 데이터베이스에 정보를 전달하여 연결할 수 있다) */
            con = DriverManager.getConnection(url, prop);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /* return을 쓰는 이유: 메소드가 성공적으로 실행되고 나면 더 이상의 코드를 실행하지 않고, 해당 메소드를 호출한 부분으로 결과를 반환하기 위함이다. */
        return con;
    }

    public static void close(Connection con) {
        try {
            /* null체크:  null인 객체를 참조하려고 할 때 NullPointerException이 발생하기 때문에,
            객체를 참조하기 전에 항상 null 여부를 확인하는 것은 좋은 프로그래밍 습관 중 하나이다. */
            if(con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset) {
        try {
            if(rset != null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
