/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.springframework.samples.petclinic.rest.api;

import org.springframework.samples.petclinic.rest.dto.ErrorDto;
import org.springframework.samples.petclinic.rest.dto.PatchProductRequestDto;
import org.springframework.samples.petclinic.rest.dto.ProductDto;
import org.springframework.samples.petclinic.rest.dto.ProductFieldsDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-07T14:41:19.617322700+08:00[Asia/Shanghai]")
@Validated
@Tag(name = "products", description = "the products API")
public interface ProductsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /products : Create a product
     * Creates a product.
     *
     * @param productFieldsDto The product (required)
     * @return Product created successfully. (status code 201)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or User not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "addProduct",
        summary = "Create a product",
        description = "Creates a product.",
        tags = { "product" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Product created successfully.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/products",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ProductDto> addProduct(
        @Parameter(name = "ProductFieldsDto", description = "The product", required = true) @Valid @RequestBody ProductFieldsDto productFieldsDto
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /products/{productId} : Delete a product by ID
     * Returns the product or a 404 error.
     *
     * @param productId The ID of the product. (required)
     * @return Product details found and returned. (status code 200)
     *         or Delete Ok (status code 204)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or Porduct not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "deleteProduct",
        summary = "Delete a product by ID",
        description = "Returns the product or a 404 error.",
        tags = { "product" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Product details found and returned.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "204", description = "Delete Ok"),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Porduct not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/products/{productId}",
        produces = { "application/json" }
    )
    default ResponseEntity<ProductDto> deleteProduct(
        @Parameter(name = "productId", description = "The ID of the product.", required = true, in = ParameterIn.PATH) @PathVariable("productId") Long productId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /products : List all products
     *
     * @return A paged array of products (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "listProducts",
        summary = "List all products",
        tags = { "products" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of products", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/products",
        produces = { "application/json" }
    )
    default ResponseEntity<List<ProductDto>> listProducts(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ null, null ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PATCH /products/{productId} : Change product&#39;s quantity
     *
     * @param productId The ID of the product. (required)
     * @param patchProductRequestDto The product details to use for the update. (required)
     * @return Product details found and returned. (status code 200)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or User not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "patchProduct",
        summary = "Change product's quantity",
        tags = { "product" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Product details found and returned.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/products/{productId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ProductDto> patchProduct(
        @Parameter(name = "productId", description = "The ID of the product.", required = true, in = ParameterIn.PATH) @PathVariable("productId") Long productId,
        @Parameter(name = "PatchProductRequestDto", description = "The product details to use for the update.", required = true) @Valid @RequestBody PatchProductRequestDto patchProductRequestDto
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /products/search : Info for a specific product
     *
     * @param productName The name of the product to retrieve (optional)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "searchProductByName",
        summary = "Info for a specific product",
        tags = { "products" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/products/search",
        produces = { "application/json" }
    )
    default ResponseEntity<List<ProductDto>> searchProductByName(
        @Parameter(name = "productName", description = "The name of the product to retrieve", in = ParameterIn.QUERY) @Valid @RequestParam(value = "productName", required = false) String productName
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ null, null ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /products/{productId} : Info for a specific product
     *
     * @param productId The id of the product to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "showProductById",
        summary = "Info for a specific product",
        tags = { "product" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/products/{productId}",
        produces = { "application/json" }
    )
    default ResponseEntity<ProductDto> showProductById(
        @Parameter(name = "productId", description = "The id of the product to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("productId") Long productId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /products/getByName/{productName} : Info for a specific product
     *
     * @param productName The name of the product to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "showProductByName",
        summary = "Info for a specific product",
        tags = { "products" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/products/getByName/{productName}",
        produces = { "application/json" }
    )
    default ResponseEntity<List<ProductDto>> showProductByName(
        @Parameter(name = "productName", description = "The name of the product to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("productName") String productName
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ null, null ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /products/{productId} : Update a product&#39;s details
     * Updates the product record with the specified details.
     *
     * @param productId The ID of the product. (required)
     * @param productFieldsDto The product details to use for the update. (required)
     * @return Product details found and returned. (status code 200)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or User not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "updateProduct",
        summary = "Update a product's details",
        description = "Updates the product record with the specified details.",
        tags = { "product" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Product details found and returned.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/products/{productId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ProductDto> updateProduct(
        @Parameter(name = "productId", description = "The ID of the product.", required = true, in = ParameterIn.PATH) @PathVariable("productId") Long productId,
        @Parameter(name = "ProductFieldsDto", description = "The product details to use for the update.", required = true) @Valid @RequestBody ProductFieldsDto productFieldsDto
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
