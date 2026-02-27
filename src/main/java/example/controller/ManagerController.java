package example.controller;

import example.model.dao.ManagerDao;
import example.model.dto.ManagerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {
    @Autowired private ManagerDao managerDao;

    @GetMapping("/dboard")   // localhost:8080/manager     // 해당 메소드의 HTTP 메소드와 주소 정의
    public List<ManagerDto> findAll(){                    // 매개변수 정의
        List<ManagerDto> result=managerDao.findAll();       // DAO 호출하여 결과 받기
        return result;                                  // DAO결과로 응답
    }

    // 사원 등록
    @PostMapping("/dboard")
    public boolean add(@RequestBody ManagerDto managerDto){
        boolean result= managerDao.add(managerDto);
        return result;
    }

    @PutMapping("/dboard")
    public boolean update(@RequestBody ManagerDto managerDto){
        boolean result= managerDao.update(managerDto);
        return result;
    }
    @DeleteMapping("/dboard")
    public boolean delete(@RequestParam int emp_code){
        boolean result= managerDao.delete(emp_code);
        return result;
    }
}
