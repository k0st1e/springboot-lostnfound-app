package com.example.lostnfoundrevamped.controllers;

import com.example.lostnfoundrevamped.entities.UserEntity;
import com.example.lostnfoundrevamped.entities.dto.LoginDto;
import com.example.lostnfoundrevamped.entities.dto.RegisterDto;
import com.example.lostnfoundrevamped.entities.dto.UserDto;
import com.example.lostnfoundrevamped.services.ItemService;
import com.example.lostnfoundrevamped.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    ItemService itemService;

    // Uses .dologin of UserService.
    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("loginDTO") LoginDto dto,
                          ModelMap modelMap,
                          HttpSession session) {
        UserDto loggedInUser = userService.dologin(dto.username(), dto.password());
        if (loggedInUser == null) {
            modelMap.addAttribute("message", "Wrong Credentials!");
            return "login";
        } else {
            session.setAttribute("loggedinuser", loggedInUser);
            modelMap.addAttribute("allitems", itemService.getAllItems());
            return "redirect:/home";
        }
    }

    // Converts the User that is registering, the registerDTO to an Entity.
    // Encodes the password -> Converts the registerDTO to an Entity -> Hashes and Registers :D
    @PostMapping("/doregister")
    public String doRegister(@ModelAttribute("registerDTO") RegisterDto dto,
                             ModelMap modelMap,
                             RedirectAttributes redirectAttributes) {

        if (dto.username().isEmpty()
                || dto.realname().isEmpty()
                || dto.password().isEmpty()
                || dto.confirmpassword().isEmpty()) {
            modelMap.addAttribute("message", "You're incompetent.");
            return "register";
        }

        if (!dto.password().equals(dto.confirmpassword())) {
            modelMap.addAttribute("message", "Your passwords do not match!");
            return "register";
        }

        String hashedPassword = passwordEncoder.encode(dto.password());
        UserEntity user = userService.convertDtoToEntity(dto);
        user.setPassword(hashedPassword);
        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("message", "Registration Complete. Please log-in!");
        return "redirect:/";
    }
}