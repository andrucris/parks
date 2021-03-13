package park.domain;



import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.UUID;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Park {

    private @Id @GeneratedValue Long code;
    private String name;
    public Park() {
    }


    public Park(  String name) {
        this.name = name;
    }
}
