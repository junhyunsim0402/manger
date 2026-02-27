package example.controller;

import example.model.dao.VacationDao;
import example.model.dto.VacationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VacationController {

    @Autowired
    private VacationDao vacationDao;

    // 휴가신청
    @PostMapping("/dboard")
    public boolean add(@RequestBody VacationDto vacationDto){
        boolean result = vacationDao.add(vacationDto);
        return result;
    }
    // 휴가취소
    @DeleteMapping("/dboard")
    public boolean delete(@RequestParam int leave_code){
        boolean result = vacationDao.delete(leave_code);
        return result;
    }


    // 휴가조회
    @GetMapping("/dboard")
    public List<VacationDto>FindAll(){
        List<VacationDto>list = vacationDao.FindAll();
        return list;
    }



}
