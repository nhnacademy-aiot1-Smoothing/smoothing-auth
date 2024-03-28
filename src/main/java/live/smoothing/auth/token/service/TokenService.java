package live.smoothing.auth.token.service;

import live.smoothing.auth.token.dto.LoginTokenResponse;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.user.domain.User;

public interface TokenService {

    LoginTokenResponse issue(User user);

    void delete(String userId, String refreshToken);

    ReissueResponse reissue(String userId, String refreshToken);
}
