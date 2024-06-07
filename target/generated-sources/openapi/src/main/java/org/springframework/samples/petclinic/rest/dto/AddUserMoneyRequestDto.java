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
 * AddUserMoneyRequestDto
 */

@JsonTypeName("addUserMoney_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-07T14:41:19.617322700+08:00[Asia/Shanghai]")
public class AddUserMoneyRequestDto {

  @JsonProperty("money")
  private Double money = null;

  public AddUserMoneyRequestDto money(Double money) {
    this.money = money;
    return this;
  }

  /**
   * Get money
   * @return money
  */
  
  @Schema(name = "money", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddUserMoneyRequestDto addUserMoneyRequest = (AddUserMoneyRequestDto) o;
    return Objects.equals(this.money, addUserMoneyRequest.money);
  }

  @Override
  public int hashCode() {
    return Objects.hash(money);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddUserMoneyRequestDto {\n");
    sb.append("    money: ").append(toIndentedString(money)).append("\n");
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

