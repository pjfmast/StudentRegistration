package avd.informatica.studentregistration.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@lombok.Setter
@lombok.Getter
// todo workshop lesson 7-1: add one-to-one relation between course and coordinator
public class CourseCoordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
}
