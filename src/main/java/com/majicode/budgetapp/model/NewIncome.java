package com.majicode.budgetapp.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

/**
 * NewIncome
 */

public class NewIncome   {
  @JsonProperty("name")
  private String name;

  @JsonProperty("plannedAmount")
  private Double plannedAmount;

  @JsonProperty("receivedAmount")
  private Double receivedAmount;

  public NewIncome name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(example = "Pentagon paycheck", required = true, value = "")
  @NotNull

@Size(min=1) 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NewIncome plannedAmount(Double plannedAmount) {
    this.plannedAmount = plannedAmount;
    return this;
  }

  /**
   * Get plannedAmount
   * @return plannedAmount
  */
  @ApiModelProperty(example = "4000.0", value = "")


  public Double getPlannedAmount() {
    return plannedAmount;
  }

  public void setPlannedAmount(Double plannedAmount) {
    this.plannedAmount = plannedAmount;
  }

  public NewIncome receivedAmount(Double receivedAmount) {
    this.receivedAmount = receivedAmount;
    return this;
  }

  /**
   * Get receivedAmount
   * @return receivedAmount
  */
  @ApiModelProperty(example = "3900.0", value = "")


  public Double getReceivedAmount() {
    return receivedAmount;
  }

  public void setReceivedAmount(Double receivedAmount) {
    this.receivedAmount = receivedAmount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewIncome newIncome = (NewIncome) o;
    return Objects.equals(this.name, newIncome.name) &&
        Objects.equals(this.plannedAmount, newIncome.plannedAmount) &&
        Objects.equals(this.receivedAmount, newIncome.receivedAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, plannedAmount, receivedAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewIncome {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    plannedAmount: ").append(toIndentedString(plannedAmount)).append("\n");
    sb.append("    receivedAmount: ").append(toIndentedString(receivedAmount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

