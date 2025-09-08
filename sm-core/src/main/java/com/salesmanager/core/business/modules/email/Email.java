package com.salesmanager.core.business.modules.email;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.salesmanager.core.business.constants.SerializationConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email implements Serializable {

   private static final long serialVersionUID = SerializationConstants.EMAIL_SERIAL_VERSION_UID;
    private String from;
    private String fromEmail;
    private String to;
    private String subject;
    private String templateName;

    @Builder.Default
    private Map<String, String> templateTokens = new HashMap<>();
}