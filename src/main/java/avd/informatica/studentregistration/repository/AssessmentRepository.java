package avd.informatica.studentregistration.repository;

import avd.informatica.studentregistration.domain.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
