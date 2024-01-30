package com.ohgiraffers.insert;

import com.ohgiraffers.model.dto.JobDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        /* 사용자 입력 값으로 insert */
        Scanner sc = new Scanner(System.in);
        System.out.print("직책 코드 입력 (J+숫자) : ");
        String jobCode = sc.nextLine();
        System.out.print("추가할 직책 입력 : ");
        String jobName = sc.nextLine();

        /* 다른 클래스의 메소드 호출 등에서 값을 뭉쳐서 보내기 위해서 DTO에 담고 전송 */
        JobDTO newJob = new JobDTO();
        newJob.setJobCode(jobCode);
        newJob.setJobName(jobName);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0; // insert 된 row의 개수를 반환받을 변수

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/job-query.xml"));
            String query = prop.getProperty("insertJob");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newJob.getJobCode());
            pstmt.setString(2, newJob.getJobName());

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

        if(result > 0) {
            System.out.println("직책 등록이 완료되었습니다.");
        } else {
            System.out.println("직책 등록을 실패했습니다. 다시 확인해주세요.");
        }

    }
}