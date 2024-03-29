package live.smoothing.auth.user.service;

import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.dto.LoginRequest;

public interface UserService {
    void login(LoginRequest request, User user);

    User getUser(String userId);
}
