package live.smoothing.auth.user.service.impl;

import live.smoothing.auth.user.adapter.UserAdapter;
import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.dto.LoginRequest;
import live.smoothing.auth.user.dto.SimpleUserResponse;
import live.smoothing.auth.user.exeption.PasswordNotValid;
import live.smoothing.auth.user.exeption.UserNotFound;
import live.smoothing.auth.user.exeption.UserServerError;
import live.smoothing.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 서비스 클래스의 구현체
 *
 * @author 우혜승
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserAdapter userAdapter;
    private final PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void login(LoginRequest request, User user) {
        if (!passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
            throw new PasswordNotValid();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(String userId) {

        Optional<SimpleUserResponse> userResponse;
        try {
            userResponse = userAdapter.getSimpleUser(userId);
        } catch (Exception e) {
            throw new UserServerError();
        }
        if (userResponse.isEmpty()) {
            throw new UserNotFound();
        }
        return userResponse.get().toEntity();
    }
}
