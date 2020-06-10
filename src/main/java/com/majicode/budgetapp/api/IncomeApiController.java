package com.majicode.budgetapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import com.majicode.budgetapp.model.Income;
import com.majicode.budgetapp.model.InlineResponse200;
import com.majicode.budgetapp.service.IncomeService;
import com.majicode.budgetapp.util.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    public ResponseEntity<InlineResponse200> createIncome(@ApiParam(value = "Income definition" ,required=true )  @Valid @RequestBody Income income) {
    	logger.info("Create income for {}", income.getName());
    	
    	logger.debug("Income data:");
    	logger.debug("Name: {}", income.getName());
    	logger.debug("Planned Amount: ${}", income.getPlannedAmount());
    	logger.debug("Received Amount: ${}", income.getReceivedAmount());
    	
    	logger.trace("Converting the income types");
    	final com.majicode.budgetapp.entity.Income savedIncome = new com.majicode.budgetapp.entity.Income();
    	savedIncome.setName(income.getName());
    	savedIncome.setPlannedAmount(income.getPlannedAmount());
    	savedIncome.setReceivedAmount(income.getReceivedAmount());
    	savedIncome.setDateCreated(new java.sql.Date(new Date().getTime()));
    	savedIncome.setDateUpdated(savedIncome.getDateCreated());
    	
    	logger.trace("Save the income");
    	final Long savedIncomeId = incomeService.save(savedIncome);
    	
    	logger.trace("Set the return value");
    	final InlineResponse200 response = new InlineResponse200();
    	response.setIncomeId(String.valueOf(savedIncomeId));
    	
    	return new ResponseEntity<InlineResponse200>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Income> getIncomeByDate(@NotNull @ApiParam(value = "Date the income was created", required = true) @Valid @RequestParam(value = "date", required = true) String date) {
    	List<com.majicode.budgetapp.entity.Income> incomes = incomeService.getIncomeByDate(DateUtils.createDateFromDateString(date));
    	
    	System.out.println("Found " + incomes.size() + " results");
    	
    	final Income income = new Income();
    	
    	if (incomes.isEmpty()) {
	    	income.setName("test name");
	    	income.setPlannedAmount(new Double("123.45"));
	    	income.setReceivedAmount(new Double("12.34"));
    	} else {
    		com.majicode.budgetapp.entity.Income majIncome = incomes.get(0);
    		income.setName(majIncome.getName());
    		income.setPlannedAmount(majIncome.getPlannedAmount());
    		income.setReceivedAmount(majIncome.getReceivedAmount());
    	}
    	
    	return new ResponseEntity<Income>(income, HttpStatus.OK);
    }
}
