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
 * ProductDto
 */

@JsonTypeName("Product")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-29T22:21:35.694973+08:00[Asia/Shanghai]")
public class ProductDto {

  @JsonProperty("name")
  private String name;

  @JsonProperty("price")
  private Double price = null;

  @JsonProperty("image")
  private String image;

  @JsonProperty("categoryId")
  private Long categoryId = null;

  @JsonProperty("quantity")
  private Integer quantity;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("stock")
  private Boolean stock;

  public ProductDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductDto price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  
  @Schema(name = "price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public ProductDto image(String image) {
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

  public ProductDto categoryId(Long categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  /**
   * Get categoryId
   * @return categoryId
  */
  
  @Schema(name = "categoryId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public ProductDto quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * minimum: 0
   * @return quantity
  */
  @Min(0) 
  @Schema(name = "quantity", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public ProductDto id(Long id) {
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

  public ProductDto stock(Boolean stock) {
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
    ProductDto product = (ProductDto) o;
    return Objects.equals(this.name, product.name) &&
        Objects.equals(this.price, product.price) &&
        Objects.equals(this.image, product.image) &&
        Objects.equals(this.categoryId, product.categoryId) &&
        Objects.equals(this.quantity, product.quantity) &&
        Objects.equals(this.id, product.id) &&
        Objects.equals(this.stock, product.stock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, price, image, categoryId, quantity, id, stock);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDto {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

