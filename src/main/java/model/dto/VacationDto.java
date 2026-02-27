package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacationDto {
    int leave_code;
    String start_date;
    String end_date;
    String leave_reason;


}
