package com.salesmanager.admin;

import com.salesmanager.admin.controller.ControllerConstants;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by umesh on 3/2/17.
 */
@Controller
public class Welcome {

    @PreAuthorize("hasRole('AUTH')")
    @RequestMapping("/admin/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        return ControllerConstants.Tiles.adminDashboard;
    }
}

