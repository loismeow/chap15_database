package com.hm.school.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 데이터베이스 접속 전담 클래스
 * - Singleton Pattern 적용 : 프로젝트에서 단 하나의 객체 생성하고 공유한다.
 * - Connection 객체를 반환하는 getConnection() 메소드를 가지고 있다.
 */

public class DatabaseConnection {
    // 자신 타입의 정적 필드 선언
    private static Connection instance;

    // 생성자를 private으로 선언하여 외부에서 인스턴스를 생성하지 못하도록 한다.
    private DatabaseConnection() { }
    // Connection 객체를 반환하는 정적 메소드
    public static Connection getConnection() {
        if (instance == null) {
            try {   // 객체 생성 작업
                // JDBC 드라이버 클래스의 경로
                String driverClassName = "oracle.jdbc.OracleDriver";
                // 드라이버 클래스를 동적으로 로드, 로딩된 드라이버는 자동으로 DriverManager에 등록됨.
                Class.forName(driverClassName);

                // 연결할 데이터베이스의 정보
                String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl"; // 데이터베이스 URL(연결문자
                String username = "school"; // 오라클 계정명(사용자명)
                String password = "1234"; // 오라클 비번

                // DriverManager를 통해 Connection 객체를 생성
                // 매개변수로 연결할 데이터베이스의 URL, 사용자 이름, 비밀번호를 전달
                instance = DriverManager.getConnection(dbUrl, username, password);

                // 연결 성공 메시지 출력
                System.out.println("데이터베이스 커넥션 객체 생성 성공"); // DB 연결 성공

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}