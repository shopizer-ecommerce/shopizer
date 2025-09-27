package com.salesmanager.core.model.system.credentials;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password") // prevents password from leaking in logs
public abstract class Credentials {
    
    private String userName;
    private String password;
}
