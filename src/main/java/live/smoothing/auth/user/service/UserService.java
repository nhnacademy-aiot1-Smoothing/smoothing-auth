package live.smoothing.auth.user.service;

import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.dto.LoginRequest;

public interface UserService {
    User login(LoginRequest request);
}
