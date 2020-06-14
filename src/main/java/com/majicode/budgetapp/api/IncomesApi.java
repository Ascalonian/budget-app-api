/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.majicode.budgetapp.api;

import com.majicode.budgetapp.model.Error;
import com.majicode.budgetapp.model.Income;
import java.util.UUID;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Api(value = "Incomes", description = "the Incomes API")
public interface IncomesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /incomes : Add a new income to the budget
     * Adds a new Income for a specific amount on a given date
     *
     * @param income Income object that needs to be added to the budget (required)
     * @return Expected response to a valid request (status code 200)
     *         or Income information is invalid. (status code 400)
     *         or Invalid input (status code 405)
     */
    @ApiOperation(value = "Add a new income to the budget", nickname = "addIncome", notes = "Adds a new Income for a specific amount on a given date", response = Income.class, tags={ "incomes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = Income.class),
        @ApiResponse(code = 400, message = "Income information is invalid.", response = Error.class),
        @ApiResponse(code = 405, message = "Invalid input", response = Error.class) })
    @RequestMapping(value = "/incomes",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Income> addIncome(@ApiParam(value = "Income object that needs to be added to the budget" ,required=true )  @Valid @RequestBody Income income) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"Pentagon paycheck\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"plannedAmount\" : 4000.0, \"receivedAmount\" : 3900.0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /incomes : Delete an Income
     * Delete an existing Income from the budget
     *
     * @param incomeId Income id to delete (required)
     * @return The specified resource was not found (status code 404)
     */
    @ApiOperation(value = "Delete an Income", nickname = "deleteIncome", notes = "Delete an existing Income from the budget", tags={ "incomes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class) })
    @RequestMapping(value = "/incomes",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteIncome(@ApiParam(value = "Income id to delete",required=true) @PathVariable("incomeId") UUID incomeId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /incomes/findByDate : Find income by specific date
     *
     * @param date Date the income was created (required)
     * @return successful operation (status code 200)
     *         or Invalid date provided (status code 400)
     *         or The specified resource was not found (status code 404)
     */
    @ApiOperation(value = "Find income by specific date", nickname = "findIncomeByDate", notes = "", response = Income.class, tags={ "incomes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Income.class),
        @ApiResponse(code = 400, message = "Invalid date provided", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class) })
    @RequestMapping(value = "/incomes/findByDate",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Income> findIncomeByDate(@NotNull @ApiParam(value = "Date the income was created", required = true) @Valid @RequestParam(value = "date", required = true) String date) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"Pentagon paycheck\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"plannedAmount\" : 4000.0, \"receivedAmount\" : 3900.0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /incomes/{incomeId} : Info for a specific Income
     *
     * @param incomeId The id of the income to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     *         or Invalid ID provided (status code 400)
     *         or The specified resource was not found (status code 404)
     */
    @ApiOperation(value = "Info for a specific Income", nickname = "findIncomeById", notes = "", response = Income.class, tags={ "incomes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = Income.class),
        @ApiResponse(code = 400, message = "Invalid ID provided", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class) })
    @RequestMapping(value = "/incomes/{incomeId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Income> findIncomeById(@ApiParam(value = "The id of the income to retrieve",required=true) @PathVariable("incomeId") String incomeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"Pentagon paycheck\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"plannedAmount\" : 4000.0, \"receivedAmount\" : 3900.0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /incomes : List all Incomes
     * Retrieves the full list of all the Incomes in the budget app
     *
     * @param limit Max records to return (optional, default to 0)
     * @return A list of Incomes (status code 200)
     *         or Unexpected error (status code 200)
     */
    @ApiOperation(value = "List all Incomes", nickname = "listIncomes", notes = "Retrieves the full list of all the Incomes in the budget app", response = Income.class, responseContainer = "List", tags={ "incomes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of Incomes", response = Income.class, responseContainer = "List"),
        @ApiResponse(code = 200, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/incomes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Income>> listIncomes(@ApiParam(value = "Max records to return", defaultValue = "0") @Valid @RequestParam(value = "limit", required = false, defaultValue="0") Optional<Integer> limit) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"Pentagon paycheck\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"plannedAmount\" : 4000.0, \"receivedAmount\" : 3900.0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /incomes : Update an existing Income
     * Update an existing Income
     *
     * @param income Income object that needs to be updated in the budget (required)
     * @return Invalid ID supplied (status code 400)
     *         or The specified resource was not found (status code 404)
     *         or Validation exception (status code 405)
     */
    @ApiOperation(value = "Update an existing Income", nickname = "updateIncome", notes = "Update an existing Income", tags={ "incomes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Error.class) })
    @RequestMapping(value = "/incomes",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> updateIncome(@ApiParam(value = "Income object that needs to be updated in the budget" ,required=true )  @Valid @RequestBody Income income) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
