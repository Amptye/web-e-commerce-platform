package org.springframework.samples.petclinic.rest.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TaxFieldsDto
 */

@JsonTypeName("TaxFields")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-29T22:21:35.694973+08:00[Asia/Shanghai]")
public class TaxFieldsDto {

  @JsonProperty("tax")
  private Boolean tax;

  @JsonProperty("percentage")
  private Integer percentage;

  public TaxFieldsDto tax(Boolean tax) {
    this.tax = tax;
    return this;
  }

  /**
   * Get tax
   * @return tax
  */
  @NotNull 
  @Schema(name = "tax", requiredMode = Schema.RequiredMode.REQUIRED)
  public Boolean getTax() {
    return tax;
  }

  public void setTax(Boolean tax) {
    this.tax = tax;
  }

  public TaxFieldsDto percentage(Integer percentage) {
    this.percentage = percentage;
    return this;
  }

  /**
   * Get percentage
   * minimum: 0
   * maximum: 100
   * @return percentage
  */
  @Min(0) @Max(100) 
  @Schema(name = "percentage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Integer getPercentage() {
    return percentage;
  }

  public void setPercentage(Integer percentage) {
    this.percentage = percentage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaxFieldsDto taxFields = (TaxFieldsDto) o;
    return Objects.equals(this.tax, taxFields.tax) &&
        Objects.equals(this.percentage, taxFields.percentage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tax, percentage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaxFieldsDto {\n");
    sb.append("    tax: ").append(toIndentedString(tax)).append("\n");
    sb.append("    percentage: ").append(toIndentedString(percentage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

