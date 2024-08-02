package org.marco.poc.pocdbisolationlevels.rest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;
}
