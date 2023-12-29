import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


public class LeadController {
    private final LeadService leadService;
    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    public ResponseEntity<String> createLead(@Valid @RequestBody Lead lead) {
        leadService.saveLead(lead);
        return new ResponseEntity<>("Lead created successfully", HttpStatus.CREATED);
    }
}
