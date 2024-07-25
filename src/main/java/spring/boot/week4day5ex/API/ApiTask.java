package spring.boot.week4day5ex.API;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiTask {
    private String message;
    private String status;
}
