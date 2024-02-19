package XindusAssignment.WishlistManagement.DTOs.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequestDto {
    private String name;

    private String email;

    private String password;

    private String roles;
}
