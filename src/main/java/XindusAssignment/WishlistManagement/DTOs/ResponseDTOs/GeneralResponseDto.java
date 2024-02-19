package XindusAssignment.WishlistManagement.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GeneralResponseDto represents a generic response DTO for API responses.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GeneralResponseDto {
    /**
     * The message associated with the response.
     */
    private String message;

    /**
     * A flag indicating the success of the operation.
     */
    private boolean success;
}
