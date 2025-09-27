package com.salesmanager.core.business.modules.email;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import com.salesmanager.core.business.constants.CoreBusinessConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailConfig implements JSONAware {

    private String host;
    private String port;
    private String protocol;
    private String username;
    private String password;
    private boolean smtpAuth = false;
    private boolean starttls = false;
    private String emailTemplatesPath;

    @Override
    @SuppressWarnings("unchecked")
    public String toJSONString() {
        JSONObject data = new JSONObject();
        data.put(CoreBusinessConstants.HOST, this.host);
        data.put(CoreBusinessConstants.PORT, this.port);
        data.put(CoreBusinessConstants.PROTOCOL, this.protocol);
        data.put(CoreBusinessConstants.USERNAME, this.username);
        data.put(CoreBusinessConstants.SMTP_AUTH, this.smtpAuth);
        data.put(CoreBusinessConstants.STARTTLS, this.starttls);
        data.put(CoreBusinessConstants.PASSWORD, this.password);
        return data.toJSONString();
    }
}