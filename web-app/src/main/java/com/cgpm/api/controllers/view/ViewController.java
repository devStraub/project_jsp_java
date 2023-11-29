package com.cgpm.api.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author michel.pech
 */

@Controller
public class ViewController  {
	
	@RequestMapping("/")
    public String redirectToProjeto() {
        return "redirect:/projeto/list";
    }
      
}
