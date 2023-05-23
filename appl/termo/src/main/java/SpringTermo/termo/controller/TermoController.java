package SpringTermo.termo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import SpringTermo.termo.model.TermoModel;
import SpringTermo.termo.persistence.Daotermo;

@Controller
public class TermoController {
	@Autowired
	Daotermo dtm;
	
	@RequestMapping(name = "fsin", value = "/fsin", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		return new ModelAndView("fsin");
	}
	
	@RequestMapping(name = "fsin", value="/fsin", method = RequestMethod.POST)
	public ModelAndView init(ModelMap model, @RequestParam Map<String,String>
	allParam) {
		String bt = allParam.get("bt");
		List<TermoModel> lm = new ArrayList<>();
		try {
			String dt = allParam.get("ipdt");
			double h = Double.parseDouble(allParam.get("iph"));
			double p = Double.parseDouble(allParam.get("ipp"));
			double t = Double.parseDouble(allParam.get("ipt"));
			TermoModel tms = new TermoModel();
			tms.sethUMIDITY(h);
			tms.setpRESSURE(p);
			tms.settEMPERATURE(t);
			tms.setlDT(dt);
			if(bt != null) {
			if(bt.equalsIgnoreCase("Inserir")) {
				dtm.Inserir(tms);
			}
			if(bt.equalsIgnoreCase("Atualizar")) {
				dtm.Atualizar(tms);
			}
			if(bt.equalsIgnoreCase("Deletar")) {
				dtm.Excluir(tms);
			}
			if(bt.equalsIgnoreCase("Buscar")) {
				lm.add(dtm.Busca(tms));
			}
			if(bt.equalsIgnoreCase("Listar")) {
				lm = dtm.Lista();
			}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			model.addAttribute("lst", lm);
		}
		return new ModelAndView("fsin");
		
	}
}
