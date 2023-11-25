package ctu.se.oda.model11.usermockservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ctu.se.oda.model11.usermockservice.dto.UserDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    public List<UserDTO> getAllUser() throws IOException {
        Resource resource = new ClassPathResource("data/user.txt");
        InputStream inputStream = resource.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(inputStream, UserDTO[].class));
    }
}
