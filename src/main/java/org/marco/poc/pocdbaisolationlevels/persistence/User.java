package org.marco.poc.pocdbaisolationlevels.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;

}
