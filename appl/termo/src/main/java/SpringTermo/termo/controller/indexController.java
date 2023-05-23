package SpringTermo.termo.controller;

import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


public class indexController {
	@RequestMapping(name = "index", value = "/index", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		return new ModelAndView("index");
	}
	
	@RequestMapping(name = "index", value="/index", method = RequestMethod.POST)
	public ModelAndView init(ModelMap model, @RequestParam Map<String,String>
	allParam) {
		return new ModelAndView("index");	
	}
}
