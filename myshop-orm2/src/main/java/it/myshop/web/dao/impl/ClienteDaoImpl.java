package it.myshop.web.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import it.myshop.web.dao.ClienteDao;
import it.myshop.web.model.Cliente;

public class ClienteDaoImpl implements ClienteDao
{
	//oggetto di hibernate
	@PersistenceContext
	private EntityManager em;


	
	@Override
	@Transactional
	public void add(Cliente c)
	{
		// TODO Auto-generated method stub
		em.persist(c);
	}



	@Override
	public Cliente getById(int id)
	{
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id);
	}

	
	
}
