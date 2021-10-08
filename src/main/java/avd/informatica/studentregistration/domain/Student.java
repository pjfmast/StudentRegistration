package avd.informatica.studentregistration.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PartimeStudent.class, name = "PARTTIME"),
        @JsonSubTypes.Type(value = FulltimeStudent.class, name = "FULLTIME")
})
public abstract class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @lombok.Setter
    @lombok.Getter
    private Long id;

    @lombok.Setter
    @lombok.Getter
    private String name;

    @lombok.Setter
    @lombok.Getter
    private LocalDate dayOfBirth;

    public Student(String name, LocalDate dayOfBirth) {
        this.name = name;
        this.dayOfBirth = dayOfBirth;
    }

    public Student() {
    }
}
