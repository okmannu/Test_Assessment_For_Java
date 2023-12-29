import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LeadRepository extends JpaRepository<Lead, Long> {
 
}