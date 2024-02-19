package XindusAssignment.WishlistManagement;

import XindusAssignment.WishlistManagement.Controllers.UserController;
import XindusAssignment.WishlistManagement.DTOs.RequestDTOs.AuthRequestDto;
import XindusAssignment.WishlistManagement.DTOs.RequestDTOs.RegisterRequestDto;
import XindusAssignment.WishlistManagement.DTOs.ResponseDTOs.UserResponseDto;
import XindusAssignment.WishlistManagement.Services.UserInfoDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTests {

    @Mock
    private UserInfoDetailsService userInfoDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserController userController;

    @Test
    void testAddNewUser() {
        // Given
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("testName", "test@example.com", "testPassword", "USER");
        UserResponseDto expectedResponseDto = new UserResponseDto("testName", "test@example.com");
        when(userInfoDetailsService.addUser(registerRequestDto)).thenReturn(expectedResponseDto);

        // When
        ResponseEntity<UserResponseDto> responseEntity = userController.addNewUser(registerRequestDto);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponseDto, responseEntity.getBody());
    }

    @Test
    void testLoginAndGetToken_Success() {
        // Given
        AuthRequestDto authRequestDto = new AuthRequestDto("test@example.com", "testPassword");
        String expectedToken = "testToken";
        when(authenticationManager.authenticate(null)).thenReturn(null);
        when(authenticationManager.authenticate(null)).thenReturn(null);

        // When
        String actualToken = userController.loginAndGetToken(authRequestDto);

        // Then
        assertEquals(expectedToken, actualToken);
    }

    @Test
    void testLoginAndGetToken_InvalidCredentials() {
        // Given
        AuthRequestDto authRequestDto = new AuthRequestDto("invalid@example.com", "invalidPassword");
        when(authenticationManager.authenticate(null)).thenThrow(new UsernameNotFoundException("Invalid credentials"));

        // When/Then
        try {
            userController.loginAndGetToken(authRequestDto);
        } catch (UsernameNotFoundException e) {
            assertEquals("Invalid credentials", e.getMessage());
        }
    }
}
