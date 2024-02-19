package XindusAssignment.WishlistManagement.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WishlistItemResponseDto represents the response DTO for a wishlist item.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishlistItemResponseDto {
    /**
     * The unique identifier of the wishlist item.
     */
    private int id;

    /**
     * The name of the wishlist item.
     */
    private String name;

    /**
     * The description of the wishlist item.
     */
    private String description;

    /**
     * The price of the wishlist item.
     */
    private double price;

    /**
     * The user information associated with the wishlist item.
     */
    private UserResponseDto userInfo;
}
