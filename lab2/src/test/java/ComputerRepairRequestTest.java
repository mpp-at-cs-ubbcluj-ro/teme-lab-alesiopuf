import model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {
    
    @Test
    @DisplayName("Test for ComputerRepairRequest")
    public void testComputerRepairRequestConstructor() {
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest(1, "John Doe", "Bucharest", "0722222222", "Dell", "2021-03-01", "The computer doesn't start");
        assertEquals(1, computerRepairRequest.getID());
        assertEquals("John Doe", computerRepairRequest.getOwnerName());
        assertEquals("Bucharest", computerRepairRequest.getOwnerAddress());
        assertEquals("0722222222", computerRepairRequest.getPhoneNumber());
        assertEquals("Dell", computerRepairRequest.getModel());
        assertEquals("2021-03-01", computerRepairRequest.getDate());
        assertEquals("The computer doesn't start", computerRepairRequest.getProblemDescription());
    }

    @Test
    @DisplayName("Test for ComputerRepairRequest setters")
    public void testComputerRepairRequestSetters() {
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest(1, "John Doe", "Bucharest", "0722222222", "Dell", "2021-03-01", "The computer doesn't start");
        computerRepairRequest.setID(2);
        computerRepairRequest.setOwnerName("Jane Doe");
        computerRepairRequest.setOwnerAddress("Cluj-Napoca");
        computerRepairRequest.setPhoneNumber("0733333333");
        computerRepairRequest.setModel("HP");
        computerRepairRequest.setDate("2021-03-02");
        computerRepairRequest.setProblemDescription("The computer is slow");
        assertEquals(2, computerRepairRequest.getID());
        assertEquals("Jane Doe", computerRepairRequest.getOwnerName());
        assertEquals("Cluj-Napoca", computerRepairRequest.getOwnerAddress());
        assertEquals("0733333333", computerRepairRequest.getPhoneNumber());
        assertEquals("HP", computerRepairRequest.getModel());
        assertEquals("2021-03-02", computerRepairRequest.getDate());
        assertEquals("The computer is slow", computerRepairRequest.getProblemDescription());
    }
}
