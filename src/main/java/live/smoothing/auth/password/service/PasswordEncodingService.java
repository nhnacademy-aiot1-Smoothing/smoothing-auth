package live.smoothing.auth.password.service;

import live.smoothing.auth.password.dto.PasswordDto;

public interface PasswordEncodingService {

    PasswordDto encodePassword(PasswordDto originalPassword);
}
