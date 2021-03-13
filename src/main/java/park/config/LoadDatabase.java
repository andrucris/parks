package park.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import park.domain.Park;
import park.repository.ParkRepository;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ParkRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Park("Flowers Park")));
            log.info("Preloading " + repository.save(new Park("Winter Park")));
        };
    }
}
