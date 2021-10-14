package avd.informatica.studentregistration.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@lombok.Setter
@lombok.Getter
public class CourseCoordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String email;
}
