package in.bushansirgur.expensetrackerapi.entity.dto.exception;

import in.bushansirgur.expensetrackerapi.entity.dto.exception.ErrorBodyDto;
import lombok.Data;

import java.util.Date;

@Data
public class ErrorObjectDto {
    private Integer statusCode;
    private ErrorBodyDto errorBodyDto;
    private Date timestamp;
}
