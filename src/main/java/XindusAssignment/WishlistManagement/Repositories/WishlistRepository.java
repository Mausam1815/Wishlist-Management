package XindusAssignment.WishlistManagement.Repositories;

import XindusAssignment.WishlistManagement.Models.User;
import XindusAssignment.WishlistManagement.Models.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The WishlistRepository interface provides CRUD operations for the WishlistItem entity.
 */
@Repository
public interface WishlistRepository extends JpaRepository<WishlistItem, Integer> {
    /**
     * Retrieve wishlist items associated with a specific user.
     * @param user The user whose wishlist items are to be retrieved.
     * @return A list of wishlist items associated with the user.
     */
    List<WishlistItem> findByUser(User user);
}
