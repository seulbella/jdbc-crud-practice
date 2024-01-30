package com.ohgiraffers.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0; // insert 된 row의 개수를 반환받을 변수

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/job-query.xml"));
            String query = prop.getProperty("insertJob");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "J8");
            pstmt.setString(2, "인턴");

            /* 조회 시에는 executeQuery() : ResultSet 메소드를 사용하지만
             * 삽입, 수정, 삭제 시에는 executeUpdate() : int 메소드를 사용하여 수행 된 행의 갯수를 리턴 받는다. */
            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        System.out.println("result : " + result);
    }

}
