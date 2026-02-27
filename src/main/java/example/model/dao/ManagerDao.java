package example.model.dao;

import example.model.dto.ManagerDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerDao {
    public ManagerDao(){connect();}
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
    // [1] 사원 전체 목록 조회
    public List<ManagerDto> findAll(){
        List<ManagerDto> list=new ArrayList<>();
        try{
            String sql="SELECT e.emp_code, e.emp_name, e.dept_key, d.dept_name, e.position " +
                    "FROM Employee e " +
                    "JOIN Department d ON e.dept_key = d.dept_key";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ManagerDto managerDto=new ManagerDto(
                        rs.getInt("emp_code"),rs.getString("emp_name"),
                        rs.getInt("dept_key"),rs.getString("dept_name"),rs.getString("position")
                );
                list.add(managerDto);
            }
        }catch (Exception e){
            System.out.println("출력오류"+e);
        }
        return list;
    }

    // [2] 게시물등록
    public boolean add(ManagerDto managerDto){
        try{
            String sql="INSERT INTO Employee(emp_code, emp_name, dept_key, position) VALUES(?, ?, ?, ?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, managerDto.getEmp_code());
            ps.setString(2,managerDto.getEmp_name());
            ps.setInt(3,managerDto.getDept_key());
            ps.setString(4,managerDto.getPosition());
            int count=ps.executeUpdate();
            if(count==1)return true;
        }catch (Exception e){ System.out.println("등록오류"+e); }
        return false;                                                       // 아니면 실패
    }

    // [3] 사원 수정
    public boolean update(ManagerDto managerDto) {
        try {
            // emp_code를 조건으로 나머지 정보를 변경합니다.
            String sql = "UPDATE Employee SET emp_name = ?, dept_key = ?, position = ? WHERE emp_code = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, managerDto.getEmp_name());
            ps.setInt(2, managerDto.getDept_key());
            ps.setString(3, managerDto.getPosition());
            ps.setInt(4, managerDto.getEmp_code());

            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println("수정 오류: " + e);
        }
        return false;
    }

    // [4] 사원 삭제
    public boolean delete(int emp_code){
        try {
            String sql = "delete from Employee where emp_code=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,emp_code);
            int count=ps.executeUpdate();
            if(count==1)return true;
        }catch (Exception e){ System.out.println("삭제 오류"+ e); }
        return false;
    }
}
