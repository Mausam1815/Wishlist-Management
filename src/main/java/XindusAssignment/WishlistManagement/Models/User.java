package XindusAssignment.WishlistManagement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * The User class represents a user entity in the system.
 * It is mapped to the 'users' table in the database.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email address of the user. This is a unique field.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The password of the user.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The roles assigned to the user. This can be used for role-based access control.
     */
    private String roles;

    /**
     * The list of wishlist items associated with the user.
     */
    @OneToMany(mappedBy = "user")
    private List<WishlistItem> wishlistItems = new ArrayList<>();


}
