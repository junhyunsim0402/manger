package example.model.dao;

import example.model.dto.CategoryDto;
import example.model.dto.ManagerDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDao {
    public CategoryDao(){connect();}
    private String url = "jdbc:mysql://localhost:3306/CompanyDB";
    private String user = "root"; private String password = "simjunhyun1@";
    // 2) 연동 인터페이스 변수 선언
    private Connection conn;
    // 3) 연동 함수 정의 , dao에 생성자에서 함수 실행 ( dao 싱글톤이 생성되면서 db연동 실행 )
    private void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 라이브러리 객체 메모리할당/불러오기
            conn = DriverManager.getConnection( url , user , password ); // mysql 구현체 로 db연동후 연동 인터페이스에 반환
            System.out.println("[시스템준비] 데이터베이스 연동 성공");
        }catch ( Exception e ){
            System.out.println("[시스템경고] 데이터베이스 연동 실패 : 관리자에게 문의");
        }
    }
    // [1] 부서 전체 목록 조회
    public List<CategoryDto> findAll(){
        List<CategoryDto> list=new ArrayList<>();
        try{
            String sql="select * from Department";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                CategoryDto categoryDto=new CategoryDto(
                        rs.getInt("dept_key"),rs.getString("dept_name")
                );
                list.add(categoryDto);
            }
        }catch (Exception e){
            System.out.println("출력오류"+e);
        }
        return list;
    }
    // [2] 부서 등록
    public boolean add(ManagerDto managerDto){
        try{
            String sql="insert into Department values (?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            // 이어서
            int count=ps.executeUpdate();
            if(count==1)return true;
        }catch (Exception e){ System.out.println("등록오류"+e); }
        return false;                                                       // 아니면 실패
    }
}
