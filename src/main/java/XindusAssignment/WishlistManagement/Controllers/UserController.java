package XindusAssignment.WishlistManagement.Controllers;

import XindusAssignment.WishlistManagement.DTOs.RequestDTOs.AuthRequestDto;
import XindusAssignment.WishlistManagement.DTOs.RequestDTOs.RegisterRequestDto;
import XindusAssignment.WishlistManagement.DTOs.ResponseDTOs.UserResponseDto;
import XindusAssignment.WishlistManagement.JWT.JwtHelper;
import XindusAssignment.WishlistManagement.Services.UserInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController handles authentication-related API endpoints.
 */
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserInfoDetailsService userInfoDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Registers a new user.
     * @param registerRequestDto The DTO containing user registration details.
     * @return ResponseEntity containing the newly registered user details and HTTP status code.
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> addNewUser(@RequestBody RegisterRequestDto registerRequestDto){
        UserResponseDto newUser = userInfoDetailsService.addUser(registerRequestDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Logs in a user and generates a JWT token.
     * @param authRequestDto The DTO containing user authentication details (email and password).
     * @return JWT token generated upon successful authentication.
     * @throws UsernameNotFoundException if the provided credentials are invalid.
     */
    @PostMapping("/login")
    public String loginAndGetToken(@RequestBody AuthRequestDto authRequestDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        if(authentication != null && authentication.isAuthenticated()){
            return jwtHelper.generateToken(authRequestDto.getEmail());
        }
        else{
            throw new UsernameNotFoundException("Invalid user request!!");
        }
    }

}
