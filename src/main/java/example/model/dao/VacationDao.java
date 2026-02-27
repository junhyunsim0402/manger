package example.model.dao;

import example.model.dto.VacationDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class VacationDao {
    public VacationDao(){connect();}


    String url = "jdbc:mysql://localhost:3306/CompanyDB";
    String password="1234";
    String user="root";

    private Connection conn;

    private void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 라이브러리 객체 메모리할당 /불러오기
            conn = DriverManager.getConnection(url,user,password); // mysql구현체로 db연동후 연동 인터페이스에 반환
            System.out.println("데이터베이스 연동 성공");
        }catch (Exception e){
            System.out.println("DB연동 실패");
            e.printStackTrace();
        }
    }

    // 휴가 신청
    public boolean add(VacationDto vacationDto){
        try{
            String sql="insert into Employee_Leave(emp_code, start_date, end_date, leave_reason) values((select emp_code from Employee where emp_name = ? ) ,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,vacationDto.getEmp_name());
            ps.setString(2,vacationDto.getStart_date());
            ps.setString(3,vacationDto.getEnd_date());
            ps.setString(4,vacationDto.getLeave_reason());

            int count = ps.executeUpdate();

            if (count==1){return true;}
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;

    } // m end

    // 휴가 조회
    public List<VacationDto>FindAll(){
        List<VacationDto>list = new ArrayList<>();
        try{
            String sql="select n.emp_name, e.start_date,e.end_date,e.leave_reason from Employee_Leave e inner join Employee n on e.emp_code=n.emp_code";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                VacationDto vacationDto = VacationDto
                        .builder()
                        .emp_name(rs.getString("emp_name"))
                        .start_date(rs.getString("start_date"))
                        .end_date(rs.getString("end_date"))
                        .leave_reason(rs.getString("leave_reason"))
                        .build();

                list.add(vacationDto);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }

    // 휴가 취소
    public boolean delete(int leave_code){
        try{
            String sql="delete from Employee_Leave where leave_code=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,leave_code);
            int count = ps.executeUpdate();
            if (count==1){return true;}
        }catch (Exception e){
            System.out.println("e = " + e);
            e.printStackTrace();
        }
        return false;
    }


}
