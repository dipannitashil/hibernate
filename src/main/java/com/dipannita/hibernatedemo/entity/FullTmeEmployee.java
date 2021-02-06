package com.dipannita.hibernatedemo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class FullTmeEmployee extends Employee {

	private BigDecimal salary;

	public FullTmeEmployee() {
		super();
	}

	public FullTmeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
