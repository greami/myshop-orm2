package it.myshop.web.dao;

import it.myshop.web.model.Cliente;

public interface ClienteDao
{
	public void add(Cliente c);
	public Cliente getById(int id);
}
