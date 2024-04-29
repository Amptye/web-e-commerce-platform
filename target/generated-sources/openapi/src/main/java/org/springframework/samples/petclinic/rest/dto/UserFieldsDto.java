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
 * UserFieldsDto
 */

@JsonTypeName("UserFields")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-25T06:32:19.341577700+08:00[Asia/Shanghai]")
public class UserFieldsDto {

  @JsonProperty("name")
  private String name;

  @JsonProperty("email")
  private String email;

  @JsonProperty("pass")
  private String pass;

  @JsonProperty("money")
  private Double money = null;

  @JsonProperty("address1")
  private String address1;

  @JsonProperty("address2")
  private String address2;

  @JsonProperty("contact")
  private String contact;

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("footer")
  private String footer;

  @JsonProperty("image")
  private String image;

  public UserFieldsDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the user.
   * @return name
  */
  @NotNull @Pattern(regexp = "^[a-zA-Z]*$") @Size(min = 1, max = 30) 
  @Schema(name = "name", example = "George", description = "The name of the user.", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserFieldsDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * The email of the user.
   * @return email
  */
  @Size(min = 1, max = 255) 
  @Schema(name = "email", example = "abc@ef.com", description = "The email of the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserFieldsDto pass(String pass) {
    this.pass = pass;
    return this;
  }

  /**
   * The password of the user.
   * @return pass
  */
  @Size(min = 1, max = 80) 
  @Schema(name = "pass", example = "123456789", description = "The password of the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public UserFieldsDto money(Double money) {
    this.money = money;
    return this;
  }

  /**
   * The money of the user.
   * @return money
  */
  
  @Schema(name = "money", description = "The money of the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  public UserFieldsDto address1(String address1) {
    this.address1 = address1;
    return this;
  }

  /**
   * Get address1
   * @return address1
  */
  @Pattern(regexp = "^[0-9]*$") @Size(min = 1, max = 20) 
  @Schema(name = "address1", example = "10086", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public UserFieldsDto address2(String address2) {
    this.address2 = address2;
    return this;
  }

  /**
   * Get address2
   * @return address2
  */
  @Pattern(regexp = "^[0-9]*$") @Size(min = 1, max = 20) 
  @Schema(name = "address2", example = "10087", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public UserFieldsDto contact(String contact) {
    this.contact = contact;
    return this;
  }

  /**
   * Get contact
   * @return contact
  */
  @Pattern(regexp = "^\\d{0,20}$") 
  @Schema(name = "contact", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public UserFieldsDto symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  /**
   * Get symbol
   * @return symbol
  */
  
  @Schema(name = "symbol", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public UserFieldsDto footer(String footer) {
    this.footer = footer;
    return this;
  }

  /**
   * Get footer
   * @return footer
  */
  
  @Schema(name = "footer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getFooter() {
    return footer;
  }

  public void setFooter(String footer) {
    this.footer = footer;
  }

  public UserFieldsDto image(String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  */
  
  @Schema(name = "image", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserFieldsDto userFields = (UserFieldsDto) o;
    return Objects.equals(this.name, userFields.name) &&
        Objects.equals(this.email, userFields.email) &&
        Objects.equals(this.pass, userFields.pass) &&
        Objects.equals(this.money, userFields.money) &&
        Objects.equals(this.address1, userFields.address1) &&
        Objects.equals(this.address2, userFields.address2) &&
        Objects.equals(this.contact, userFields.contact) &&
        Objects.equals(this.symbol, userFields.symbol) &&
        Objects.equals(this.footer, userFields.footer) &&
        Objects.equals(this.image, userFields.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, pass, money, address1, address2, contact, symbol, footer, image);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserFieldsDto {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    pass: ").append(toIndentedString(pass)).append("\n");
    sb.append("    money: ").append(toIndentedString(money)).append("\n");
    sb.append("    address1: ").append(toIndentedString(address1)).append("\n");
    sb.append("    address2: ").append(toIndentedString(address2)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    footer: ").append(toIndentedString(footer)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
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

