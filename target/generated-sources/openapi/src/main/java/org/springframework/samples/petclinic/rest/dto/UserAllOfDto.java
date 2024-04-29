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
 * UserAllOfDto
 */

@JsonTypeName("User_allOf")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-29T22:21:35.694973+08:00[Asia/Shanghai]")
public class UserAllOfDto {

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("cartId")
  private Long cartId = null;

  public UserAllOfDto id(Long id) {
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

  public UserAllOfDto cartId(Long cartId) {
    this.cartId = cartId;
    return this;
  }

  /**
   * Get cartId
   * @return cartId
  */
  
  @Schema(name = "cartId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getCartId() {
    return cartId;
  }

  public void setCartId(Long cartId) {
    this.cartId = cartId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAllOfDto userAllOf = (UserAllOfDto) o;
    return Objects.equals(this.id, userAllOf.id) &&
        Objects.equals(this.cartId, userAllOf.cartId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cartId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserAllOfDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cartId: ").append(toIndentedString(cartId)).append("\n");
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

