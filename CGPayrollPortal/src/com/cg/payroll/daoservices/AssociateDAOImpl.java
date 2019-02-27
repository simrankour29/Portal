package com.cg.payroll.daoservices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.util.PayrollDBUtil;

public class AssociateDAOImpl implements AssociateDAO{

	private EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
	
	
	
	@Override
	public Associate save(Associate associate) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(associate);
		entityManager.getTransaction().commit();
		entityManager.close();
		return associate;
	}

	@Override
	public boolean update(Associate associate) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(associate);
		entityManager.getTransaction().commit();
		entityManager.close();
		return false;
	}

	@Override
	public Associate findOne(int associateId) {
		/*EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.find(Associate.class, associateId);*/
		return entityManagerFactory.createEntityManager().find(Associate.class, associateId);
		
	}@SuppressWarnings("unchecked")
	  @Override
	public List<Associate> findAll() {
		Query query=entityManagerFactory.createEntityManager().createQuery("from Associate a",Associate.class);
		return(List<Associate>) query.getResultList();
	}

	@Override
	public List<Associate> getAllAssociatesDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	



}


