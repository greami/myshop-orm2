package it.myshop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.myshop.web.dao.ClienteDao;
import it.myshop.web.model.Cliente;

@Controller
@RequestMapping("/")
public class ClienteController
{
	//variabile di acccesso ai servizi dao
	
	@Autowired
	private ClienteDao clienteService;
	
	@ResponseBody  // stampa direttamente nel browser, no page
	@GetMapping("/")
	public String index()
	{
		//clienteService.add(null)
		return "cliente home!";	
		
	}
	
	
	@ResponseBody  // stampa direttamente nel browser, no page
	@GetMapping("/add")
	public String add()
	{
		//clienteService.add(null)
		Cliente c = new Cliente();
		c.setNome("nome_ami");
		c.setCognome("cognome_gre");
		
		clienteService.add(c);
		
		return "cliente aggiunto";	
		
	}
	
	
	
	@ResponseBody  // stampa direttamente nel browser, no page
	@GetMapping("/getbyid")
	public String getById()
	{
		//clienteService.add(null)
		Cliente c = clienteService.getById(3);
		return c.getNome() + "-" + c.getCognome();	
		
	}
	

}
