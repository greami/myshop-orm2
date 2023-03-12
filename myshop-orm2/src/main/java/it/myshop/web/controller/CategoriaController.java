package it.myshop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.myshop.web.model.Categoria;
import it.myshop.web.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController
{
	@Autowired
	private CategoriaRepository repository;
	
	@ResponseBody
	@GetMapping("/add")
	public String add()
	{
		Categoria c = new Categoria();
		c.setNome("prova_cat");
		c.setDescrizione("prove_descr");
		
		repository.save(c);
		
		return "categoria inserita";
	}
	
	
	@ResponseBody
	@GetMapping("/")
	public String get()
	{
		Iterable<Categoria> cat = repository.findAll();
		
		cat.forEach(c -> {
			System.out.println(c.getNome());
			System.out.println(c.getDescrizione());
			System.out.println(("--------"));
		});
		
		return "test get - categoria";
	}
	
	
	@ResponseBody
	@GetMapping("/getbyid")
	public String getByID()
	{
		java.util.Optional<Categoria> cate = repository.findById(2);
		
		return cate.get().getNome() + "-" +cate.get().getDescrizione();	
	}
	
	
	@ResponseBody
	@GetMapping("/getbynome")
	public String getByNome()
	{
		Categoria c1 = repository.findByNome("BOOK");
		Categoria c2  =repository.findByNome("E-BOOK");
		
		System.out.println(c1.getNome() + "-" + c1.getDescrizione());
		System.out.println(c2.getNome() + "-" + c2.getDescrizione());
		return "ecco le categoria per nome";
	}
	
	
	@ResponseBody
	@GetMapping("/getbynomelike")
	public String getByNomeLike()
	{
		List<Categoria>  catList= repository.getByNomeLike("%BOOK%");
		
		catList.forEach(c -> {
			
			System.out.println(c.getNome() + "-" + c.getDescrizione());
		});
		
		return "ecco le categoria per nome like";
	}
	

}
