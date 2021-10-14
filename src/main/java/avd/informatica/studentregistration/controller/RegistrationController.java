package avd.informatica.studentregistration.controller;

import avd.informatica.studentregistration.service.RegistrationDto;
import avd.informatica.studentregistration.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    ResponseEntity<RegistrationDto> remove(@RequestBody RegistrationDto registrationDto) {
        boolean canRemove = registrationService.removeRegistration(registrationDto.getStudentId(), registrationDto.getCourseId());

        if (canRemove) {
            return new ResponseEntity<>(registrationDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
