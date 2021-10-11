package avd.informatica.studentregistration.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@lombok.Setter
@lombok.Getter
public class FulltimeStudent extends Student{
    private String sloTeacher;

    public FulltimeStudent(String name, LocalDate dayOfBirth, String sloTeacher) {
        super(name, dayOfBirth);
        this.sloTeacher = sloTeacher;
    }

    public FulltimeStudent() {
        super();
    }
}
