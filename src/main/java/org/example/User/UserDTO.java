package org.example.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private Integer id;
    private String name;
    private String username;
    private String email;
    public UserDTO UserDTO(Users users) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setName(users.getFirstName()+ " " + users.getLastName());
        userDTO.setUsername(users.getUsername());
        userDTO.setEmail(users.getEmail());
        return userDTO;
    }

}
