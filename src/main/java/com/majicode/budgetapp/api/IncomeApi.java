/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.majicode.budgetapp.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import com.majicode.budgetapp.model.Error;
import com.majicode.budgetapp.model.Income;
import com.majicode.budgetapp.model.InlineResponse200;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Validated
@Api(value = "Income", description = "the Income API")
public interface IncomeApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /income : Add a new income to the budget
     * adds a new Income for a specific amount on a given date
     *
     * @param income Income object that needs to be added to the budget (required)
     * @return Returns ApplicationInstanceUID (status code 200)
     *         or Income information is invalid. (status code 400)
     *         or Invalid input (status code 405)
     */
    @ApiOperation(value = "Add a new income to the budget", nickname = "addIncome", notes = "adds a new Income for a specific amount on a given date", response = InlineResponse200.class, tags={ "income", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns ApplicationInstanceUID", response = InlineResponse200.class),
        @ApiResponse(code = 400, message = "Income information is invalid.", response = String.class),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/income",
        produces = { "application/json", "text/plain" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<InlineResponse200> addIncome(@ApiParam(value = "Income object that needs to be added to the budget" ,required=true )  @Valid @RequestBody Income income) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"income-id\" : \"income-id\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /income/findByDate : Find income by specific date
     *
     * @param date Date the income was created (required)
     * @return successful operation (status code 200)
     *         or Invalid date provided (status code 400)
     *         or Income not found (status code 404)
     */
    @ApiOperation(value = "Find income by specific date", nickname = "findIncomeByDate", notes = "", response = Income.class, tags={ "income", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Income.class),
        @ApiResponse(code = 400, message = "Invalid date provided"),
        @ApiResponse(code = 404, message = "Income not found") })
    @RequestMapping(value = "/income/findByDate",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Income> findIncomeByDate(@NotNull @ApiParam(value = "Date the income was created", required = true) @Valid @RequestParam(value = "date", required = true) String date) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"Pentagon paycheck\", \"plannedAmount\" : 4000.0, \"receivedAmount\" : 3900.0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /income : List all Incomes
     * Retrieves the full list of all the Incomes in the budget app
     *
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A list of Incomes (status code 200)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "List all Incomes", nickname = "listIncomes", notes = "Retrieves the full list of all the Incomes in the budget app", response = Income.class, responseContainer = "List", tags={ "income", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of Incomes", response = Income.class, responseContainer = "List"),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
    @RequestMapping(value = "/income",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Income>> listIncomes(@ApiParam(value = "How many items to return at one time (max 100)") @Valid @RequestParam(value = "limit", required = false) Optional<Integer> limit) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"Pentagon paycheck\", \"plannedAmount\" : 4000.0, \"receivedAmount\" : 3900.0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /income/{incomeId} : Info for a specific Income
     *
     * @param incomeId The id of the income to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     */
    @ApiOperation(value = "Info for a specific Income", nickname = "showIncomeById", notes = "", response = Income.class, tags={ "income", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = Income.class) })
    @RequestMapping(value = "/income/{incomeId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Income> showIncomeById(@ApiParam(value = "The id of the income to retrieve",required=true) @PathVariable("incomeId") String incomeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"Pentagon paycheck\", \"plannedAmount\" : 4000.0, \"receivedAmount\" : 3900.0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /income : Update an existing Income
     *
     * @param income Income object that needs to be updated in the budget (required)
     * @return Invalid ID supplied (status code 400)
     *         or Income not found (status code 404)
     *         or Validation exception (status code 405)
     */
    @ApiOperation(value = "Update an existing Income", nickname = "updateIncome", notes = "", tags={ "income", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Income not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/income",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> updateIncome(@ApiParam(value = "Income object that needs to be updated in the budget" ,required=true )  @Valid @RequestBody Income income) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
