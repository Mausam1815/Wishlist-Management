package XindusAssignment.WishlistManagement.DTOs.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WishlistItemRequestDto represents the request DTO for creating or updating a wishlist item.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishlistItemRequestDto {
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
}
