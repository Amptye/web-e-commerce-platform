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
 * ProductAllOfDto
 */

@JsonTypeName("Product_allOf")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-29T22:21:35.694973+08:00[Asia/Shanghai]")
public class ProductAllOfDto {

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("stock")
  private Boolean stock;

  public ProductAllOfDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductAllOfDto stock(Boolean stock) {
    this.stock = stock;
    return this;
  }

  /**
   * Get stock
   * @return stock
  */
  
  @Schema(name = "stock", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Boolean getStock() {
    return stock;
  }

  public void setStock(Boolean stock) {
    this.stock = stock;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductAllOfDto productAllOf = (ProductAllOfDto) o;
    return Objects.equals(this.id, productAllOf.id) &&
        Objects.equals(this.stock, productAllOf.stock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, stock);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductAllOfDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    stock: ").append(toIndentedString(stock)).append("\n");
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

