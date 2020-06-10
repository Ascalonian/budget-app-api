package com.majicode.budgetapp.repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.majicode.budgetapp.entity.IncomeData;

@Repository
public interface IncomeRepository extends CrudRepository<IncomeData, UUID> {
	Iterable<IncomeData> findIncomeByDateCreated(Date date);
	
	List<IncomeData> findByOrderByDateCreatedAsc();
}
