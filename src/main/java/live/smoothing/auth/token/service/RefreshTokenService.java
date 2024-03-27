package live.smoothing.auth.token.service;

import live.smoothing.auth.token.dto.ReissueResponse;

public interface RefreshTokenService {

    void delete(String userId, String refreshToken);

    ReissueResponse reissue(String userId, String refreshToken);
}
