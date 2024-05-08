package com.hm.school.execution;

import javax.print.DocFlavor;
import javax.xml.transform.Result;
import java.net.URI;
import java.net.URL;
import java.sql.*;

/**
 * 데이터베이스 접속 테스트 클래스
 * 1. JDBC 드라이버 로딩 : Class.forName("oracle.jdbc.OracleDriver");
 * 2. 접속 정보 설정 : DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "school", "1234"); *
 */
public class StudentDBConnect {
    // 오라클 DB에 접속해서 하기 위한 정보
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "school", "1234");
            System.out.println("DB 접속 성공");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if (conn != null) conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }// end of main

    private static void displayStudents(Connection conn){
        System.out.println("등록된 학생 목록:");
        String sql = "SELECT s.student_id, s.name, s.year, s.address, s.department_id " +
                "FROM student s " +
                "ORDER BY s.student_id";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String studentId = rs.getString("student_id");
                String name = rs.getString("name");
                int year = rs.getInt("year");
                String address = rs.getString("address");
                int departmentId = rs.getInt("department_id");
                System.out.println(studentId + "\t" + name + "\t" + year + "\t" +
                        address + "\t" + departmentId);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------------------");
    }
}