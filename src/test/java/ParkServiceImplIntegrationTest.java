import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import park.domain.Park;
import park.repository.ParkRepository;
import park.service.ParkService;
import park.service.impl.ParkServiceImpl;

@RunWith(SpringRunner.class)
public class ParkServiceImplIntegrationTest {

    @TestConfiguration
    static class ParkServiceImplTestContextConfiguration {
        @Bean
        public ParkService parkService() {
            return new ParkServiceImpl();
        }
    }

    @Autowired
    private ParkService parkService;

    @MockBean
    private ParkRepository parkRepository;

    @Before
    public void setUp() {
        Park parkTest1 = new Park("test1");
        parkTest1.setCode(11L);

        Park parkTest2 = new Park("test2");
        parkTest2.setCode(21L);


        Park parkTest3 = new Park("test3");

        List<Park> allParks = Arrays.asList(parkTest1,parkTest2,parkTest3);

        Mockito.when(parkRepository.findByName(parkTest1.getName())).thenReturn(parkTest1);
        Mockito.when(parkRepository.findByName(parkTest2.getName())).thenReturn(parkTest2);
        Mockito.when(parkRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(parkRepository.findById(parkTest1.getCode())).thenReturn(Optional.of(parkTest1));
        Mockito.when(parkRepository.findAll()).thenReturn(allParks);
        Mockito.when(parkRepository.findById(-99L)).thenReturn(Optional.empty());
    }



    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        Park fromDb = parkService.one(11L);
        assertThat(fromDb.getName()).isEqualTo("test1");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenParkShouldNotBeFound() {
        Park fromDb = parkService.one(-99L);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
    public void given3Employees_whengetAll_thenReturn3Records() {
        Park park1 = new Park("park1");
        Park park2 = new Park("park2");
        Park park3 = new Park("park3");

        List<Park> allEmployees = parkService.all();
        verifyFindAllEmployeesIsCalledOnce();
        assertThat(allEmployees).hasSize(3).extracting(Park::getName).contains(park1.getName(), park2.getName(), park3.getName());
    }



    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(parkRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(parkRepository);
    }

    private void verifyFindAllEmployeesIsCalledOnce() {
        Mockito.verify(parkRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(parkRepository);
    }
}
