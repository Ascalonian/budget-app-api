package com.majicode.budgetapp.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

/**
 * Income information
 */
@ApiModel(description = "Income information")

public class Income   {
  @JsonProperty("id")
  private UUID id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("plannedAmount")
  private Double plannedAmount;

  @JsonProperty("receivedAmount")
  private Double receivedAmount;

  @JsonProperty("dateCreated")
  private String dateCreated;

  @JsonProperty("dateUpdated")
  private String dateUpdated;

  public Income id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Income name(String name) {
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

  public Income plannedAmount(Double plannedAmount) {
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

  public Income receivedAmount(Double receivedAmount) {
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

  public Income dateCreated(String dateCreated) {
    this.dateCreated = dateCreated;
    return this;
  }

  /**
   * Get dateCreated
   * @return dateCreated
  */
  @ApiModelProperty(example = "2020-10-31T00:00:00Z", value = "")


  public String getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(String dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Income dateUpdated(String dateUpdated) {
    this.dateUpdated = dateUpdated;
    return this;
  }

  /**
   * Get dateUpdated
   * @return dateUpdated
  */
  @ApiModelProperty(example = "2020-11-01T00:00:00Z", value = "")


  public String getDateUpdated() {
    return dateUpdated;
  }

  public void setDateUpdated(String dateUpdated) {
    this.dateUpdated = dateUpdated;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Income income = (Income) o;
    return Objects.equals(this.id, income.id) &&
        Objects.equals(this.name, income.name) &&
        Objects.equals(this.plannedAmount, income.plannedAmount) &&
        Objects.equals(this.receivedAmount, income.receivedAmount) &&
        Objects.equals(this.dateCreated, income.dateCreated) &&
        Objects.equals(this.dateUpdated, income.dateUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, plannedAmount, receivedAmount, dateCreated, dateUpdated);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Income {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    plannedAmount: ").append(toIndentedString(plannedAmount)).append("\n");
    sb.append("    receivedAmount: ").append(toIndentedString(receivedAmount)).append("\n");
    sb.append("    dateCreated: ").append(toIndentedString(dateCreated)).append("\n");
    sb.append("    dateUpdated: ").append(toIndentedString(dateUpdated)).append("\n");
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

