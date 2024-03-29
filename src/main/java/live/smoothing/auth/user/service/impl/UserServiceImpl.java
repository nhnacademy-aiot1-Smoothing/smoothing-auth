package live.smoothing.auth.user.service.impl;

import live.smoothing.auth.user.adapter.UserAdapter;
import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.dto.LoginRequest;
import live.smoothing.auth.user.dto.SimpleUserResponse;
import live.smoothing.auth.user.exeption.LoginFailException;
import live.smoothing.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserAdapter userAdapter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void login(LoginRequest request, User user) {

        if (!user.getUserPassword().equals(passwordEncoder.encode(request.getUserPassword()))) {
            throw new LoginFailException("비밀번호 틀림");
        }
    }

    @Override
    public User getUser(String userId) {

        Optional<SimpleUserResponse> userResponse;
        try {
            userResponse = userAdapter.getSimpleUser(userId);
        } catch (Exception e) {
            throw new LoginFailException("유저 서버 오류");
        }
        if (userResponse.isEmpty()) {
            throw new LoginFailException("유저 정보 없음");
        }
        return userResponse.get().toEntity();
    }
}
