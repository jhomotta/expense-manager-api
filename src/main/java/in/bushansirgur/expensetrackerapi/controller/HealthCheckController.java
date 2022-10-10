package in.bushansirgur.expensetrackerapi.controller;

import in.bushansirgur.expensetrackerapi.entity.VersionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @GetMapping("")
    public ResponseEntity<VersionModel> version() {
        VersionModel vm = new VersionModel();
        vm.setVersion("1.0");
        return new ResponseEntity<VersionModel>(vm, HttpStatus.OK);
    }
}
