/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.majicode.budgetapp.api;

import com.majicode.budgetapp.model.Income;
import com.majicode.budgetapp.model.InlineResponse200;
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
@Api(value = "Income", description = "the Income API")
public interface IncomeApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /income : Create a new Income
     * Creates a new Income for a specific amount on a given date
     *
     * @param income Income definition (required)
     * @return Returns ApplicationInstanceUID (status code 200)
     *         or Income information is invalid. (status code 400)
     */
    @ApiOperation(value = "Create a new Income", nickname = "createIncome", notes = "Creates a new Income for a specific amount on a given date", response = InlineResponse200.class, tags={ "income", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns ApplicationInstanceUID", response = InlineResponse200.class),
        @ApiResponse(code = 400, message = "Income information is invalid.", response = String.class) })
    @RequestMapping(value = "/income",
        produces = { "application/json", "text/plain" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<InlineResponse200> createIncome(@ApiParam(value = "Income definition" ,required=true )  @Valid @RequestBody Income income) {
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
    @ApiOperation(value = "Find income by specific date", nickname = "getIncomeByDate", notes = "", response = Income.class, tags={ "income", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Income.class),
        @ApiResponse(code = 400, message = "Invalid date provided"),
        @ApiResponse(code = 404, message = "Income not found") })
    @RequestMapping(value = "/income/findByDate",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Income> getIncomeByDate(@NotNull @ApiParam(value = "Date the income was created", required = true) @Valid @RequestParam(value = "date", required = true) String date) {
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

}
