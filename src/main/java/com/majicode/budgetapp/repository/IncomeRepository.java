package com.majicode.budgetapp.repository;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.majicode.budgetapp.entity.Income;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long> {
	Iterable<Income> findIncomeByDateCreated(Date date);
}
