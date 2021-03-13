import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import park.ParkApplication;
import park.controllers.ParkController;
import park.domain.Park;
import park.exception.ParkNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextTest.class, ParkApplication.class})
@WebAppConfiguration
public class ParkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkController parkController;

    @Test
    public void findById_ShouldReturnHttpStatusCode404() throws Exception {
        when(parkController.one(1L)).thenThrow(new ParkNotFoundException(1L));

        mockMvc.perform(get("/parks/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(parkController, times(1)).one(1L);
        verifyNoMoreInteractions(parkController);
    }
    @Test
    public void givenParks_whenGetParks_thenReturnJsonArray()
            throws Exception {

        Park park = new Park("Test park");

        List<Park> allParks = Arrays.asList(park);

        given(parkController.all()).willReturn(allParks);

        mockMvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(park.getName())));
    }
}
