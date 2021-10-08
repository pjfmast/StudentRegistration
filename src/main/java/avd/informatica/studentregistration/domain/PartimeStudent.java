package avd.informatica.studentregistration.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PartimeStudent extends Student {
    @lombok.Setter
    @lombok.Getter
    private String workingEnvironment;

    public PartimeStudent(String name, LocalDate dayOfBirth, String workingEnvironment) {
        super(name, dayOfBirth);
        this.workingEnvironment = workingEnvironment;
    }

    public PartimeStudent() {
        super();
    }
}
