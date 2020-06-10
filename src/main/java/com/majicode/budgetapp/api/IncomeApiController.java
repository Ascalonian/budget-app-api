package com.majicode.budgetapp.api;

import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import com.majicode.budgetapp.entity.IncomeData;
import com.majicode.budgetapp.model.Income;
import com.majicode.budgetapp.service.IncomeService;
import com.majicode.budgetapp.util.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Controller
@Api(tags = {"income"})
public class IncomeApiController implements IncomeApi {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
    private final NativeWebRequest request;
    
    @Autowired
    private IncomeService incomeService;

    @org.springframework.beans.factory.annotation.Autowired
    public IncomeApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
    @Override
    public ResponseEntity<Income> addIncome(@ApiParam(value = "Income definition" ,required=true )  @Valid @RequestBody Income income) {
    	logger.info("Create income for {}", income.getName());
    	
    	logger.debug("Income data:");
    	logger.debug("Name: {}", income.getName());
    	logger.debug("Planned Amount: ${}", income.getPlannedAmount());
    	logger.debug("Received Amount: ${}", income.getReceivedAmount());
    	
    	logger.trace("Converting the income types");
    	final IncomeData savedIncome = new IncomeData();
    	savedIncome.setName(income.getName());
    	savedIncome.setPlannedAmount(income.getPlannedAmount());
    	savedIncome.setReceivedAmount(income.getReceivedAmount());
    	savedIncome.setDateCreated(new java.sql.Date(new Date().getTime()));
    	savedIncome.setDateUpdated(savedIncome.getDateCreated());
    	
    	logger.trace("Save the income");
    	final String savedIncomeId = incomeService.save(savedIncome);
    	
    	logger.debug("New Income ID: " + savedIncomeId);
    	
    	logger.trace("Convert the Income");
    	final Income result = convert(savedIncome);
    	
    	return new ResponseEntity<Income>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Income> findIncomeByDate(@NotNull @ApiParam(value = "Date the income was created", required = true) @Valid @RequestParam(value = "date", required = true) String date) {
    	logger.info(">>> findIncomeByDate");
    	
    	logger.debug("Parameters:");
    	logger.debug("Date: " + date);
    	
    	logger.trace("Call getIncomeByDate");
    	final List<IncomeData> incomes = incomeService.getIncomeByDate(DateUtils.createDateFromDateString(date));
    	
    	logger.info("Found " + incomes.size() + " results");
    	
    	logger.trace("Populate Income");
    	final Income income = new Income();
    	
    	if (incomes.isEmpty()) {
	    	income.setName("test name");
	    	income.setPlannedAmount(new Double("123.45"));
	    	income.setReceivedAmount(new Double("12.34"));
    	} else {
    		final IncomeData majIncome = incomes.get(0);
    		income.setName(majIncome.getName());
    		income.setPlannedAmount(majIncome.getPlannedAmount());
    		income.setReceivedAmount(majIncome.getReceivedAmount());
    	}
    	
    	logger.debug("Income data:");
    	logger.debug("Name: {}", income.getName());
    	logger.debug("Planned Amount: ${}", income.getPlannedAmount());
    	logger.debug("Received Amount: ${}", income.getReceivedAmount());
    	
    	return new ResponseEntity<Income>(income, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<List<Income>> listIncomes(@ApiParam(value = "How many items to return at one time (max 100)") @Valid @RequestParam(value = "limit", required = false, defaultValue="0") Optional<Integer> limit) {
//    	if (limit.isPresent()) {
//    		final Pageable limitIncomes = PageRequest.of(0,limit.get());
//    	}
    	
    	final List<IncomeData> fetchedIncomes = incomeService.getIncomes();
    	
    	final List<Income> incomes = convert(fetchedIncomes);
    	
    	return new ResponseEntity<List<Income>>(incomes, HttpStatus.OK);
    }
    
    private Income convert(final IncomeData income) {
    	final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
    	
    	final Income newIncome = new Income();
    	newIncome.setId(income.getIncomeId().toString());
		newIncome.setName(income.getName());
		newIncome.setPlannedAmount(income.getPlannedAmount());
		newIncome.setReceivedAmount(income.getReceivedAmount());
		newIncome.setDateCreated(dateFormat.format(income.getDateCreated()));
		newIncome.setDateUpdated(dateFormat.format(income.getDateUpdated()));
		
		return newIncome;
    }
    
    private List<Income> convert(final List<IncomeData> incomes) {
    	final List<Income> incomeList = new ArrayList<>();
    	
    	for (IncomeData income : incomes) {
    		final Income newIncome = convert(income);
    		
    		incomeList.add(newIncome);
    	}
    	
    	return incomeList;
    }
}
