package avd.informatica.studentregistration.controller;

import avd.informatica.studentregistration.domain.Course;
import avd.informatica.studentregistration.service.RegistrationDto;
import avd.informatica.studentregistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    ResponseEntity<RegistrationDto> register(@RequestBody RegistrationDto registrationDto) {
        boolean canRegister = registrationService.addRegistration(registrationDto.getStudentId(), registrationDto.getCourseId());

        if (canRegister) {
            return new ResponseEntity<>(registrationDto, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
