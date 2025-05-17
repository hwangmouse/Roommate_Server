package hackathon25.roommate.repository;

import hackathon25.roommate.domain.PersonalityProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityProfileRepository extends JpaRepository<PersonalityProfile, Long> {
}
