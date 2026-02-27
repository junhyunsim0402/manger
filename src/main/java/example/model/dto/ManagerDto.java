package example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerDto {
    Integer emp_code;
    String emp_name;
    Integer dept_key;
    String dept_name;
    String position;
}
