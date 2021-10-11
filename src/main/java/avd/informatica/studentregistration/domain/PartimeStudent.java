package avd.informatica.studentregistration.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@lombok.Setter
@lombok.Getter
public class PartimeStudent extends Student {
    private String workingEnvironment;

    public PartimeStudent(String name, LocalDate dayOfBirth, String workingEnvironment) {
        super(name, dayOfBirth);
        this.workingEnvironment = workingEnvironment;
    }

    public PartimeStudent() {
        super();
    }
}
