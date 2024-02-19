package XindusAssignment.WishlistManagement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The WishlistItem class represents an item in a user's wishlist.
 * It is mapped to the 'wishlist' table in the database.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "wishlist")
public class WishlistItem {
    /**
     * The unique identifier for the wishlist item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the wishlist item.
     */
    @Column(name = "item_name")
    private String name;

    /**
     * The description of the wishlist item.
     */
    private String description;

    /**
     * The price of the wishlist item.
     */
    @Column(name = "item_price")
    private double price;

    /**
     * The user who owns the wishlist item.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
