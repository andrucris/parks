import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import park.controllers.ParkController;

/**
 * @author
 */
@Configuration
public class ContextTest {
    @Bean
    public ParkController parkController() {
        return Mockito.mock(ParkController.class);
    }
}
