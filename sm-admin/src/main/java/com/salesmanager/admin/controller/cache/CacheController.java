package com.salesmanager.admin.controller.cache;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CacheController {


    @GetMapping(value = "/admin/cache/cacheManagement")
    @Secured({"ROLE_STORE"})
    public String cacheManagement(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        request.setAttribute("action", "READ");
        return "cache/CacheManagement";
    }
}
