package com.majicode.budgetapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.majicode.budgetapp.entity.Income;
import com.majicode.budgetapp.repository.IncomeRepository;

/**
 * Service to interact with the Income data
 *
 */
@Service
public class IncomeService {
	private final IncomeRepository incomeRepository;
	
	@Autowired
	public IncomeService(IncomeRepository incomeRepository) {
		this.incomeRepository = incomeRepository;
	}

	/**
	 * Get all incomes in descending date order
	 * 
	 * @return list of Incomes
	 */
	public List<Income> getIncomes() {
		final Iterable<Income> incomes = incomeRepository.findAll();

		final List<Income> incomeResults = new ArrayList<>();
		incomes.forEach(incomeResults::add);

		incomeResults.sort((i1, i2) -> {
			if (i1.getDateCreated().before(i2.getDateCreated())) {
				return -1;
			}
			
			return 1;
		});
		
		return incomeResults;
	}

	/**
	 * Find all income created on a specific date
	 * 
	 * @param date when the income was added
	 * @return list of Income
	 */
	public List<Income> getIncomeByDate(final Date date) {
		final List<Income> incomeResults = new ArrayList<>();

		final Iterable<Income> incomes = incomeRepository.findIncomeByDateCreated(new java.sql.Date(date.getTime()));
		incomes.forEach(incomeResults::add);

		return incomeResults;
	}
	
	public Long save(final Income income) {
		Income savedIncome = incomeRepository.save(income);
		
		return savedIncome.getIncomeId();
	}
}
