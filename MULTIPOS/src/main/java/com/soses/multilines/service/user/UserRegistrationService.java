package com.soses.multilines.service.user;

import com.soses.multilines.api.user.UserRegistrationRequest;
import com.soses.multilines.api.user.UserRegistrationResponse;

public interface UserRegistrationService {

	UserRegistrationResponse registerUser(UserRegistrationRequest request);
}
