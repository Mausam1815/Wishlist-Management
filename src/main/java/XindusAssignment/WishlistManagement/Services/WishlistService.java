package XindusAssignment.WishlistManagement.Services;

import XindusAssignment.WishlistManagement.DTOs.RequestDTOs.WishlistItemRequestDto;
import XindusAssignment.WishlistManagement.DTOs.ResponseDTOs.UserResponseDto;
import XindusAssignment.WishlistManagement.DTOs.ResponseDTOs.WishlistItemResponseDto;
import XindusAssignment.WishlistManagement.Models.User;
import XindusAssignment.WishlistManagement.Models.WishlistItem;
import XindusAssignment.WishlistManagement.Exceptions.CustomException;
import XindusAssignment.WishlistManagement.Repositories.UserRepository;
import XindusAssignment.WishlistManagement.Repositories.WishlistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The WishlistService class provides methods to manage wishlist items.
 */
@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserInfoDetailsService userInfoDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Add a new wishlist item.
     * @param wishlistItemRequestDto The DTO containing information about the wishlist item to be added.
     * @return The DTO representing the added wishlist item.
     * @throws CustomException If there is an error adding the wishlist item.
     */
    public WishlistItemResponseDto addWishlistItem(WishlistItemRequestDto wishlistItemRequestDto) throws CustomException {
        // Method implementation

        WishlistItem wishlistItem = modelMapper.map(wishlistItemRequestDto, WishlistItem.class);
        User currUser = getCurrentUser();

        wishlistItem.setUser(currUser);

        wishlistRepository.save(wishlistItem);

        UserResponseDto userResponseDto = modelMapper.map(currUser, UserResponseDto.class);

        WishlistItemResponseDto wishlistItemResponseDto = modelMapper.map(wishlistItem, WishlistItemResponseDto.class);
        wishlistItemResponseDto.setUserInfo(userResponseDto);

        return wishlistItemResponseDto;
    }

    /**
     * Get all wishlist items of the current user.
     * @return A list of DTOs representing the wishlist items.
     * @throws CustomException If there is an error retrieving the wishlist items.
     */
    public List<WishlistItemResponseDto> getWishlist() throws CustomException {
        // Method implementation
        User currUser = getCurrentUser();

        List<WishlistItem> wishlistItems = wishlistRepository.findByUser(currUser);
        List<WishlistItemResponseDto> wishlistItemResponseDtoList = new ArrayList<>();

        UserResponseDto userResponseDto = modelMapper.map(currUser, UserResponseDto.class);

        for(WishlistItem item : wishlistItems){
            WishlistItemResponseDto wishlistItemResponseDto = modelMapper.map(item, WishlistItemResponseDto.class);
            wishlistItemResponseDto.setUserInfo(userResponseDto);
            wishlistItemResponseDtoList.add(wishlistItemResponseDto);
        }

        return wishlistItemResponseDtoList;
    }

    /**
     * Delete a wishlist item by its ID.
     * @param itemId The ID of the wishlist item to be deleted.
     * @throws CustomException If there is an error deleting the wishlist item.
     */
    public void deleteWishlistItemById(int itemId) throws CustomException {
        // Method implementation
        WishlistItem itemToBeDelete = wishlistRepository.findById(itemId).orElseThrow(() -> new CustomException("Item does not exists"));

        User itemUser = itemToBeDelete.getUser();

        User currUser = getCurrentUser();

        if(itemUser.getEmail().equals(currUser.getEmail())){
            wishlistRepository.delete(itemToBeDelete);
        }else{
            throw new CustomException("Item not present in wishlist");
        }
    }

    /**
     * Get the current user.
     * @return The current user.
     * @throws CustomException If there is an error retrieving the current user.
     */
    public User getCurrentUser() throws CustomException {
        // Method implementation
        UserDetails userDetails = userInfoDetailsService.getCurrentUserDetails();
        if(userDetails == null){
            throw new CustomException("Please login");
        }

        String userEmail = userDetails.getUsername();

        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exists"));

        return user;
    }
}
