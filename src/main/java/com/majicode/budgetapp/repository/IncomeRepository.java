package com.majicode.budgetapp.repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.majicode.budgetapp.entity.IncomeData;

@Repository
public interface IncomeRepository extends CrudRepository<IncomeData, UUID> {
	List<IncomeData> findAll(Pageable pageable);
	List<IncomeData> findByOrderByDateCreatedAsc();
	List<IncomeData> findByOrderByDateCreatedAsc(Pageable pageable);
	
	List<IncomeData> findIncomeByDateCreated(Date date);
}
