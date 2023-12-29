import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class LeadControllerTest {

    private MockMvc mockMvc;
    private LeadService leadService;

    public void createLead_Success() throws Exception {
        Lead lead = new Lead();
        lead.setLead(5678);
        lead.setFirstName("Vineet");
        lead.setLastName("KV");
        lead.setMobileNumber("8877887788");
        lead.setGender("Male");

        ObjectMapper objectMapper = new ObjectMapper();
        String leadJson = objectMapper.writeValueAsString(lead);

        Mockito.when(leadService.saveLead(Mockito.any(Lead.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/leads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(leadJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("Created Leads Successfully"));
    }

    @Test
    public void createLead_LeadExists_ErrorResponse() throws Exception {
        Lead lead = new Lead();
        lead.setLead(5678);
        lead.setFirstName("Vineet");
        lead.setLastName("KV");
        lead.setMobileNumber("8877887788");
        lead.setGender("Male");

        ObjectMapper objectMapper = new ObjectMapper();
        String leadJson = objectMapper.writeValueAsString(lead);

        Mockito.when(leadService.saveLead(Mockito.any(Lead.class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/leads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(leadJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.code").value("E10010"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.messages[0]").value("Lead Already Exists in the database with the lead id"));
    }
}
