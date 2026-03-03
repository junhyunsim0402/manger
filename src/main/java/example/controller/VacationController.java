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
    @PostMapping("/vacation")
    public boolean add(@RequestBody VacationDto vacationDto){
        boolean result = vacationDao.add(vacationDto);
        return result;
    }
    // 휴가취소
    @DeleteMapping("/vacation")
    public boolean delete(@RequestParam int leave_code){
        boolean result = vacationDao.delete(leave_code);
        return result;
    }


    // 휴가조회
    @GetMapping("/vacation")
    public List<VacationDto>FindAll(){
        List<VacationDto>list = vacationDao.FindAll();
        return list;
    }


    // 사원 목록 조회
    @GetMapping("/fmember")
    public List<VacationDto>fmember(){
        return vacationDao.fmember();
    }



}
