package com.cg.payroll.services;
import java.util.List;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.daoservices.AssociateDAO;
import com.cg.payroll.daoservices.AssociateDAOImpl;
import com.cg.payroll.exceptions.AssociateDetailNotfoundException;
public class PayrollServicesImpl implements PayrollServices {
	
private AssociateDAO associateDao = new AssociateDAOImpl();

	private AssociateDAO associateDAO;
	
	public PayrollServicesImpl() {
		associateDao=new AssociateDAOImpl();
	}
	public PayrollServicesImpl(AssociateDAO associateDAO) {
		super();
		this.associateDao=associateDAO;
	}
	public int acceptAssociateDetails(Associate associate) {
		associate = associateDao.save(associate);
		return associate.getAssociateId();
	}
//	public int calculateGrossSalary(int associateId) throws AssociateDetailNotfoundException {
//		Associate associate = getAssociateDetails(associateId);
//		associate.salary.setHra((associate.salary.getBasicSalary()*30)/100);	
//		associate.salary.setConveyenceAllowance((associate.salary.getBasicSalary()*30)/100);
//		associate.salary.setPersonalAllowance((associate.salary.getBasicSalary()*20)/100);
//		associate.salary.setOtherAllowance((associate.salary.getBasicSalary()*30)/100);
//		int annualGrossSalary = ((associate.getSalary().getBasicSalary()+associate.getSalary().getHra()+
//				associate.getSalary().getConveyenceAllowance()+associate.getSalary().getOtherAllowance()+ associate.getSalary().getPersonalAllowance()+ 
//				associate.salary.getCompanyPf()+ associate.salary.getEpf())* 12);
//		
//		return associate.getSalary().getGrossSalary();
//	}
	@Override
	public int calculateNetSalary(int associateId) throws AssociateDetailNotfoundException {
		
		Associate associate = getAssociateDetails(associateId);
		if(associateId!=associate.getAssociateId())
			throw new AssociateDetailNotfoundException("Associate details not found" +associateId);
//		associate.salary.setHra((associate.salary.getBasicSalary()*30)/100);	
//		associate.salary.setConveyenceAllowance((associate.salary.getBasicSalary()*30)/100);
//		associate.salary.setPersonalAllowance((associate.salary.getBasicSalary()*20)/100);
//		associate.salary.setOtherAllowance((associate.salary.getBasicSalary()*30)/100);
//		
//		associate.salary.setNetSalary(((associate.salary.getBasicSalary()+associate.salary.getHra()+associate.salary.getConveyenceAllowance()+associate.salary.getOtherAllowance()
//		+associate.salary.getPersonalAllowance())-(associate.salary.getCompanyPf()+associate.salary.getEpf()))*12);
//		return associate.salary.getNetSalary();
//	
		if(associateId!=associate.getAssociateId())
			throw new AssociateDetailNotfoundException("Associate Details Not Found for Id " + associateId);
	//	associate.getSalary().getBasicSalary()= associate.salary.getBasicSalary();
		associate.salary.setHra((associate.salary.getBasicSalary()*30)/100);	
		associate.salary.setConveyenceAllowance((associate.salary.getBasicSalary()*30)/100);
		associate.salary.setPersonalAllowance((associate.salary.getBasicSalary()*20)/100);
		associate.salary.setOtherAllowance((associate.salary.getBasicSalary()*30)/100);
		
		int annualGrossSalary = ((associate.getSalary().getBasicSalary()+associate.getSalary().getHra()+
				associate.getSalary().getConveyenceAllowance()+associate.getSalary().getOtherAllowance()+ associate.getSalary().getPersonalAllowance()+ 
				associate.salary.getCompanyPf()+ associate.salary.getEpf())* 12);
		int netSalary=0;
		int investment = associate.getYearlyInvestmentUnder80C()+associate.salary.getCompanyPf()+associate.salary.getEpf();
		if(investment >= 1500000)
			investment = 1500000;
		if(annualGrossSalary < 250000)
			netSalary = annualGrossSalary - associate.salary.getEpf() - associate.salary.getCompanyPf();
		else if(annualGrossSalary >= 250000 && annualGrossSalary < 500000)
			netSalary = (int) ((annualGrossSalary -((annualGrossSalary-investment)/100)*10) - associate.salary.getEpf()-associate.salary.getCompanyPf());
		else if(annualGrossSalary >= 500000 && annualGrossSalary < 1000000)
		{	int t2 = (int)(((annualGrossSalary - 500000)/100)*20);
			int t1 = (int)(((250000 - investment)/100)*10);
			netSalary = annualGrossSalary - t1 - t2 - associate.salary.getCompanyPf() - associate.salary.getEpf();
		}
		else 
		{	int t3 = (int)(((annualGrossSalary - 1000000)/100)*30);
			int t2 = 1000000;
			int t1 = (int)(((250000 - investment)/100)*10 );
			netSalary = annualGrossSalary - t1 - t2 -t3- associate.salary.getCompanyPf() - associate.salary.getEpf();
		}
			return associate.getSalary().setNetSalary(netSalary);
	}
	@Override
	public Associate getAssociateDetails(int associateId) throws AssociateDetailNotfoundException {
	Associate associate= associateDao.findOne(associateId);
	if(associateDao.findOne(associateId)==null)
		throw new AssociateDetailNotfoundException("Associate details not found" +associateId);
	return associate;
	}
	@Override
	public List<Associate> getAllAssociateDetails() {
		return associateDao.findAll();
}
	@Override
	public int calculateGrossSalary(int associateId) throws AssociateDetailNotfoundException {
		// TODO Auto-generated method stub
		return 0;
	}
	}
	

	
