package in.bushansirgur.expensetrackerapi.entity.dto.exception;

import lombok.Data;

import java.util.List;

@Data
public class ErrorBodyDto {

    public ErrorBodyDto(String fileName, String methodName, Integer lineNumber, List<String> message) {
        this.fileName = fileName;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.message = message;
    }

    private String fileName;
    private String methodName;
    private Integer lineNumber;
    private List<String> message;

}
