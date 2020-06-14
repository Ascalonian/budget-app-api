package com.majicode.budgetapp.api;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import com.majicode.budgetapp.entity.IncomeData;
import com.majicode.budgetapp.model.Error;
import com.majicode.budgetapp.model.Income;
import com.majicode.budgetapp.repository.IncomeRepository;
import com.majicode.budgetapp.util.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Controller
@Api(tags = {"incomes"})
public class IncomesApiController implements IncomesApi {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
    private final NativeWebRequest request;
    
    @Autowired
    private IncomeRepository repository;

    @org.springframework.beans.factory.annotation.Autowired
    public IncomesApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
    @Override
    public ResponseEntity<Income> addIncome(@ApiParam(value = "Income definition" ,required=true )  @Valid @RequestBody Income income) {
    	logger.info(">>> addIncome");
    	
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
    	
    	logger.debug("Save the income");
    	final IncomeData incomeData = repository.save(savedIncome);
    	
    	final UUID savedIncomeId = incomeData.getIncomeId();
    	
    	logger.debug("New Income ID: {}", savedIncomeId);
    	
    	logger.trace("Convert the Income");
    	final Income result = convert(savedIncome);
    	
    	return new ResponseEntity<Income>(result, HttpStatus.OK);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public ResponseEntity deleteIncome(@ApiParam(value = "Income id to delete",required=true) @PathVariable("incomeId") UUID incomeId) {
    	logger.info(">>> deleteIncome");
    	
    	logger.debug("Parameters:");
    	logger.debug("incomeId: {}", incomeId);
    	
    	logger.debug("Calling delete");
    	final long initialCount = repository.count();
    	repository.deleteById(incomeId);
    	final boolean isDeleted = repository.count() == initialCount ? false : true;
    	
    	logger.debug("isDeleted? {}", isDeleted);
    	
    	if (isDeleted) {
    		return ResponseEntity.ok().build();
    	}
    	
    	logger.error("ID was not found: {}", incomeId);
		Error error = new Error();
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setMessage("Date was null");
		
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public ResponseEntity findIncomeByDate(@NotNull @ApiParam(value = "Date the income was created", required = true) @Valid @RequestParam(value = "date", required = true) String date) {
    	logger.info(">>> findIncomeByDate");
    	
    	logger.debug("Parameters:");
    	logger.debug("Date: {}", date);
    	
    	logger.debug("Perform data NULL check");
    	if (date == null) {
    		logger.error("Date was NULL");
    		Error error = new Error();
    		error.setCode(HttpStatus.BAD_REQUEST.value());
    		error.setMessage("Date was null");
    		
    		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    	}
    	
    	logger.debug("Call getIncomeByDate");
    	final List<IncomeData> incomes = new ArrayList<>();

    	final Date searchDate = DateUtils.createDateFromDateString(date);
		final Iterable<IncomeData> incomeResults = repository.findIncomeByDateCreated(new java.sql.Date(searchDate.getTime()));
		incomeResults.forEach(incomes::add);
    	
    	logger.info("Found {} results", incomes.size());
    	
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
    public ResponseEntity<List<Income>> listIncomes(@ApiParam(value = "How many Incomes to return at one time (max 100)", defaultValue = "0") @Valid @RequestParam(value = "limit", required = false, defaultValue="0") Optional<Integer> limit) {
    	logger.info(">>> listIncomes");
    	
    	logger.debug("Parameters:");
    	logger.debug("Limit: {}", limit.isPresent() ? limit.get() : "none provided");
    	
    	// TODO: Need to implement
//    	if (limit.isPresent()) {
//    		final Pageable limitIncomes = PageRequest.of(0,limit.get());
//    	}
    	
    	logger.debug("Call getIncomes");
    	final List<IncomeData> fetchedIncomes = repository.findByOrderByDateCreatedAsc();
    	
    	logger.trace("Convert the incomes");
    	final List<Income> incomes = convert(fetchedIncomes);
    	
    	return new ResponseEntity<List<Income>>(incomes, HttpStatus.OK);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public ResponseEntity findIncomeById(@ApiParam(value = "The id of the income to retrieve",required=true) @PathVariable("incomeId") String incomeId) {
    	logger.info(">>> findIncomeById");
    	
    	logger.debug("Parameters:");
    	logger.debug("incomeId: {}", incomeId);
    	
    	logger.debug("Validate UUID");
    	// Check to see if input is in the proper UUID format
    	final boolean isUuid = ApiUtil.isValidUUID(incomeId);
    	
    	if (!isUuid) {
    		logger.error("Not valid UUID");
    		Error error = new Error();
    		error.setCode(HttpStatus.BAD_REQUEST.value());
    		error.setMessage("Income ID was not a valid UUID");
    		
    		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    	}
    	
    	logger.debug("Call findIncomeById");
    	final Optional<IncomeData> fetchedData = repository.findById(UUID.fromString(incomeId));
    	
    	if (fetchedData.isPresent()) {
    		logger.debug("Found income");
    		final IncomeData incomeData = fetchedData.get();
    		
    		logger.trace("Convert the income");
    		final Income income = convert(incomeData);
    		
    		return new ResponseEntity<Income>(income, HttpStatus.OK);
    	}
    	
    	// Income was not found
    	logger.error("Income was not found with UUID: {}", incomeId);
    	Error error = new Error();
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setMessage("Income was not found with id: " + incomeId);
		
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }
    
    private Income convert(final IncomeData income) {
    	final Income newIncome = new Income();
    	newIncome.setId(income.getIncomeId());
		newIncome.setName(income.getName());
		newIncome.setPlannedAmount(income.getPlannedAmount());
		newIncome.setReceivedAmount(income.getReceivedAmount());
		
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
