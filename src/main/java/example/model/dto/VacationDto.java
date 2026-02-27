package example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacationDto {
    Integer leave_code;
    String start_date;
    String end_date;
    String leave_reason;
    String emp_code;
    String emp_name;


}
