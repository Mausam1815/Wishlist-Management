package XindusAssignment.WishlistManagement.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserResponseDto represents the response DTO for user information.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDto {
    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email address of the user.
     */
    private String email;
}
