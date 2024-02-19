package XindusAssignment.WishlistManagement.Services;

import XindusAssignment.WishlistManagement.DTOs.RequestDTOs.RegisterRequestDto;
import XindusAssignment.WishlistManagement.DTOs.ResponseDTOs.UserResponseDto;
import XindusAssignment.WishlistManagement.Models.User;
import XindusAssignment.WishlistManagement.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The UserInfoDetailsService class provides user-related operations.
 */
@Service
public class UserInfoDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Load user details by username.
     * @param username The email address of the user.
     * @return UserDetails representing the user.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Method implementation
        Optional<User> userInfo = userRepository.findByEmail(username);

        return userInfo.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }

    /**
     * Add a new user.
     * @param registerRequestDto The DTO containing information about the user to be added.
     * @return The DTO representing the added user.
     */
    public UserResponseDto addUser(RegisterRequestDto registerRequestDto) {
        // Method implementation
        registerRequestDto.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        User userInfo = modelMapper.map(registerRequestDto, User.class);
        userRepository.save(userInfo);
        return modelMapper.map(userInfo, UserResponseDto.class);
    }

    /**
     * Get details of the current logged-in user.
     * @return UserDetails representing the current user.
     */
    public UserDetails getCurrentUserDetails() {
        // Method implementation
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }

        return (UserDetails) authentication.getPrincipal();
    }
}
