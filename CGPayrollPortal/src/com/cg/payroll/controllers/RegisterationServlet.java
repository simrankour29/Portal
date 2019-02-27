package com.cg.payroll.controllers;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.services.PayrollServices;
import com.cg.payroll.services.PayrollServicesImpl;
@WebServlet("/registeration")
public class RegisterationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PayrollServices services;
	@Override
	public void init()throws ServletException{
		services=new PayrollServicesImpl();
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String firstName =request.getParameter("firstName"); 
	String lastName =request.getParameter("lastName"); 	
	String department =request.getParameter("department "); 
	String designation =request.getParameter("designation "); 
	String pancard =request.getParameter("pancard "); 
	String emailId =request.getParameter("emailId"); 
	int yearlyInvestmentUnder80c =Integer.parseInt(request.getParameter("yearlyInvestmentUnder80c")); 
	int basicSalary =Integer.parseInt(request.getParameter("basicSalary")); 
	int hra =Integer.parseInt(request.getParameter("hra")); 
	int epf =Integer.parseInt(request.getParameter("epf")); 
	int companyPf=Integer.parseInt(request.getParameter("companyPf"));
	String bankName =request.getParameter("bankName");
	String ifscCode =request.getParameter("ifscCode");
	int accountNumber=Integer.parseInt(request.getParameter("accountNumber"));
	
	
	int associateId=services.acceptAssociateDetails(new Associate(yearlyInvestmentUnder80c, firstName, lastName, department, designation, pancard, emailId, basicSalary, epf, companyPf, accountNumber, bankName, ifscCode, new Salary(basicSalary, epf, companyPf), new BankDetails(accountNumber, bankName, ifscCode)));
	request.setAttribute("associateId", associateId);
	request.getRequestDispatcher("registrationSuccessPage.jsp").forward(request, response);
	}

}
