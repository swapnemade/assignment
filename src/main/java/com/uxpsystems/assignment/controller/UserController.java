package com.uxpsystems.assignment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uxpsystems.assignment.model.Emp;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.SecurityService;
import com.uxpsystems.assignment.service.UserService;
import com.uxpsystems.assignment.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }
    
    @RequestMapping("/empform")    
    public String showform(Model m){    
        m.addAttribute("command", new Emp());  
        return "empform";   
    }   
    
    @RequestMapping("/viewemp")    
    public String viewemp(Model m){    
        List<Emp> list= userService.getEmployees();    
        m.addAttribute("list",list);  
        return "viewemp";    
    }   
    
    @RequestMapping(value="/save",method = RequestMethod.POST)    
    public String save(@ModelAttribute("emp") Emp emp){    
    	userService.saveEmployee(emp);    
        return "redirect:/viewemp"; 
    }
    
    @RequestMapping(value="/editemp/{id}")    
    public String edit(@PathVariable int id, Model m){    
        Emp emp=userService.getEmpById(id);    
        m.addAttribute("command",emp);  
        return "empeditform";    
    } 
    
    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave(@ModelAttribute("emp") Emp emp){    
        userService.updateEmployee(emp);    
        return "redirect:/viewemp";    
    }    
    /* It deletes record for the given id in URL and redirects to /viewemp */    
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)    
    public String delete(@PathVariable int id){    
    	userService.deleteEmployee(id);    
        return "redirect:/viewemp";    
    } 

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
    	System.out.println("model"+ model);
        return "welcome";
    }
    
    @ModelAttribute("statusList")
    public Map<String, String> getstatusList() {
       Map<String, String> statusList = new HashMap<String, String>();
       statusList.put("Active", "Active");
       statusList.put("Inactive", "Inactive");
      
       return statusList;
    }
    
}