import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

public class Lead {
    private Long id;
    private Integer lead;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNumber;
    private String gender;
    private Date dob;
    private String email;
}
	