package XindusAssignment.WishlistManagement.Controllers;

import XindusAssignment.WishlistManagement.DTOs.RequestDTOs.WishlistItemRequestDto;
import XindusAssignment.WishlistManagement.DTOs.ResponseDTOs.GeneralResponseDto;
import XindusAssignment.WishlistManagement.DTOs.ResponseDTOs.WishlistItemResponseDto;
import XindusAssignment.WishlistManagement.Exceptions.CustomException;
import XindusAssignment.WishlistManagement.Services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * WishlistController handles API endpoints related to wishlist management.
 */
@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    /**
     * Adds a new wishlist item into the database.
     * @param wishlistItemRequestDto The DTO containing details of the wishlist item to be added.
     * @return ResponseEntity containing the newly added wishlist item and HTTP status code.
     * @throws CustomException if an error occurs during the operation.
     */
    @PostMapping("/add-item")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<WishlistItemResponseDto> addWishlistItem(@RequestBody WishlistItemRequestDto wishlistItemRequestDto) throws CustomException {
        WishlistItemResponseDto newWishlistItem = wishlistService.addWishlistItem(wishlistItemRequestDto);
        return new ResponseEntity<>(newWishlistItem, HttpStatus.CREATED);
    }

    /**
     * Retrieves the wishlist of the current user.
     * @return ResponseEntity containing the user's wishlist and HTTP status code.
     * @throws CustomException if an error occurs during the operation.
     */
    @GetMapping("/get-wishlist")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<WishlistItemResponseDto>> getWishlist() throws CustomException {
        List<WishlistItemResponseDto> userWishlist= wishlistService.getWishlist();
        return new ResponseEntity<>(userWishlist, HttpStatus.OK);
    }

    /**
     * Deletes a wishlist item by its ID.
     * @param itemId The ID of the wishlist item to be deleted.
     * @return ResponseEntity containing a confirmation message and HTTP status code.
     * @throws CustomException if an error occurs during the operation.
     */
    @DeleteMapping("/delete-item/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<GeneralResponseDto> deleteWishlistItemById(@PathVariable("id") int itemId) throws CustomException {
        wishlistService.deleteWishlistItemById(itemId);
        return new ResponseEntity<>(new GeneralResponseDto("Item deleted successfully", true), HttpStatus.OK);
    }
}
