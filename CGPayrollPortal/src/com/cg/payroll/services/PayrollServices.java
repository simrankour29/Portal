package com.cg.payroll.services;

import java.util.List;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailNotfoundException;

public interface PayrollServices {
	
	public int acceptAssociateDetails(Associate associate);
	int calculateNetSalary(int associateId) throws AssociateDetailNotfoundException;
int calculateGrossSalary(int associateId) throws AssociateDetailNotfoundException;
	Associate getAssociateDetails(int associateId) throws AssociateDetailNotfoundException;
	
	List<Associate> getAllAssociateDetails();
	
}