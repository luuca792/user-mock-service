package ctu.se.oda.model11.usermockservice.api;

import ctu.se.oda.model11.usermockservice.dto.UserDTO;
import ctu.se.oda.model11.usermockservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(name = "Authorization") String authorizationHeader) {
        try {
            String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded);

            final String[] values = credentials.split(":", 2);
            String username = values[0];

            List<UserDTO> users = userService.getAllUser();

            Optional<UserDTO> authenticatedUser = users.stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst();

            return authenticatedUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        }
        catch (IOException e) {
            throw new RuntimeException("Error reading user file", e);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable String userId) {
        try {

            List<UserDTO> users = userService.getAllUser();
            Optional<UserDTO> userInfo = users.stream()
                    .filter(user -> user.getUserId().equals(UUID.fromString(userId)))
                    .findFirst();
            return userInfo.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        } catch (IOException e) {
            throw new RuntimeException("Error reading user file", e);
        }
    }

}
