package sof.crud.kata312.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sof.crud.kata312.model.Role;
import sof.crud.kata312.model.User;
import sof.crud.kata312.service.UserService;
import java.util.List;


@Controller
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String index1(Model model) {
        model.addAttribute("users", userService.index());
        return "index1";
    }

    @GetMapping("admin/users")
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "index";
    }

    @GetMapping("admin/users/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "show";
    }

    @GetMapping("admin/users/new")
    public String newPerson(@ModelAttribute("user") User user) {

        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("admin/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        User user = userService.get(id);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);

        return "edit";
    }


    @PatchMapping("admin/users/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping("{username}")
    @IsUser
    public String user(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.show(userService.findByUserName(username).getId()));
        return "user";
    }



}
