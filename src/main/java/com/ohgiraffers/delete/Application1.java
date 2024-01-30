package com.ohgiraffers.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 직책 코드 : ");
        String jobCode = sc.nextLine();

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/job-query.xml"));
            String query = prop.getProperty("deleteJob");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, jobCode);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if(result > 0) {
            System.out.println("직책 삭제가 완료 되었습니다.");
        } else {
            System.out.println("직책 삭제를 실패했습니다. 다시 확인해주세요.");
        }


    }
}