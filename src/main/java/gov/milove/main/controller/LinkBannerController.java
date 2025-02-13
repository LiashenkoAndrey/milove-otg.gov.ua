package gov.milove.main.controller;

import gov.milove.main.dto.LinkBannerDto;
import gov.milove.main.dto.request.LinkBannerCreateRequest;
import gov.milove.main.dto.request.LinkBannerUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.validation.annotation.Validated;

@Tag(
    name = "Link banner controller",
    description = "Provides functionality: get, create, update and delete link banners"
)
@Validated
public interface LinkBannerController {

  @Operation(
      summary = "Get all banners",
      description = "Gets a full list of link banners ordered by creation date",
      security = @SecurityRequirement(name = "No auth requirements")
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get full list"),
      @ApiResponse(responseCode = "500", description = "Internal server exception"),
  })
  List<LinkBannerDto> findAll();

  @Operation(
      summary = "Creates a new link banner",
      security = @SecurityRequirement(name = "Requires an admin rights")
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Banner added successfully"),
      @ApiResponse(responseCode = "401", description = "Authentication issue"),
      @ApiResponse(responseCode = "422",
          description = "Field validation failed. One or several do not meet the conditions"),
      @ApiResponse(responseCode = "400", description = "Invalid body provided")
  })
  ResponseEntity<LinkBannerDto> addBanner(@Valid LinkBannerCreateRequest request, Principal user);

  @Operation(
      summary = "Fully updates link banner",
      security = @SecurityRequirement(name = "Requires an admin rights")
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Banner updated successfully"
      ),
      @ApiResponse(responseCode = "401", description = "Authentication issue"),
      @ApiResponse(responseCode = "404", description = "Banner with provided id not found"),
      @ApiResponse(responseCode = "422",
          description = "Field validation failed. One or several do not meet the conditions"),
      @ApiResponse(responseCode = "400", description = "Invalid body provided")
  })
  ResponseEntity<LinkBannerDto> update(@Valid LinkBannerUpdateRequest request);

  @Operation(
      summary = "Deletes link banner",
      security = @SecurityRequirement(name = "Requires an admin rights")
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "204",
          description = "Banner deleted successfully"
      ),
      @ApiResponse(responseCode = "401", description = "Authentication issue"),
      @ApiResponse(responseCode = "404", description = "Banner with provided id not found"),
      @ApiResponse(responseCode = "400", description = "Invalid id type provided, in must be int")
  })
  ResponseEntity<?> delete(Long id);
}
