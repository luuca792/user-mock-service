package ctu.se.oda.model11.usermockservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String access_token;
    private UUID userId;
    private String username;
    private String role;
}
