package in.bushansirgur.expensetrackerapi.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

@Data
public class VersionModel {
    private String version;
}
