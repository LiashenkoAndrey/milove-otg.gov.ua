package gov.milove.main.controller.integrationtest;

import static gov.milove.main.constants.Constants.CONTENT_MANAGER_ROLE;
import static gov.milove.testdata.AuhenticationTestData.PROTECTED_API;
import static gov.milove.testdata.LinkBannerTestData.LINK_BANNER_FOR_DELETION_ID;
import static gov.milove.testdata.LinkBannerTestData.LINK_BANNER_ID;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gov.milove.config.IntegrationTest;
import gov.milove.main.dto.LinkBannerDto;
import gov.milove.main.dto.request.LinkBannerCreateRequest;
import gov.milove.main.dto.request.LinkBannerUpdateRequest;
import gov.milove.main.repository.jpa.LinkBannerRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
@Log4j2
@Sql({"classpath:/testdata/link-banner-test-data.sql"})
@DisplayName("Link banner controller integration test")
class LinkBannerControllerIntegrationTest extends IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private LinkBannerRepository linkBannerRepository;

  @Test
  @DisplayName("Find all banners should return list with one element")
  void findAllBanners_shouldReturnOneElement() throws Exception {
    int expectedListSize = (int) linkBannerRepository.count();

    mockMvc.perform(get("/api/link-banners"))
        .andExpectAll(
            status().isOk(),
            jsonPath("$").isArray(),
            jsonPath("$", hasSize(expectedListSize)),
            jsonPath("$[0].id").isNumber(),
            jsonPath("$[0].createdOn").isNotEmpty(),
            jsonPath("$[0].lastUpdated").exists(),
            jsonPath("$[0].text").isNotEmpty(),
            jsonPath("$[0].url").isNotEmpty()
        );
  }

  @Nested
  @DisplayName("Add link banner")
  @WithMockUser(roles = CONTENT_MANAGER_ROLE, username = "user123")
  class AddLinkBanner {

    @Test
    @DisplayName("Should save link banner when valid params provided")
    void addBanner_shouldSave_whenValidParams() throws Exception {


      var request = new LinkBannerCreateRequest("https://www.linkedin.com", "Some text");
      String content = objectMapper.writeValueAsString(request);

      MvcResult result = mockMvc.perform(post(PROTECTED_API + "/link-banners")
              .contentType(MediaType.APPLICATION_JSON)
              .content(content)
          )  .andExpectAll(
              status().isCreated(),
              jsonPath("$.id").isNumber(),
              jsonPath("$.createdOn").isNotEmpty(),
              jsonPath("$.text").value(request.text()),
              jsonPath("$.url").value(request.url())
          ).andReturn();
      result.getResponse().getContentAsString();
      LinkBannerDto dto = objectMapper.readValue(result.getResponse().getContentAsString(),
          LinkBannerDto.class);

      assertTrue(linkBannerRepository.existsById(dto.id()));
    }

    @ParameterizedTest
    @MethodSource("invalidParams")
    @DisplayName("Should return 400 when invalid input")
    void addBanner_shouldReturn400_whenInvalidInput(String url, String text) throws Exception {
      var request = new LinkBannerCreateRequest(url, text);
      String content = objectMapper.writeValueAsString(request);

      mockMvc.perform(post(PROTECTED_API + "/link-banners")
              .contentType(MediaType.APPLICATION_JSON)
              .content(content))
          .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidParams() {
      String validText = "Some text";
      String validUrl = "https://www.linkedin.com";
      return Stream.of(
          Arguments.of("invalid url", validText),
          Arguments.of("", validText),
          Arguments.of(null, validText),
          Arguments.of(validUrl, "22"),
          Arguments.of(validUrl, null),
          Arguments.of(validUrl, "")
      );
    }
  }

  @Nested
  @DisplayName("Fully update link banner")
  @WithMockUser(roles = CONTENT_MANAGER_ROLE, username = "user123")
  class UpdateLinkBanner {

    @Test
    @DisplayName("Should update link banner")
    void shouldReturn200_whenValidInput() throws Exception {
      LinkBannerUpdateRequest request = new LinkBannerUpdateRequest(
          LINK_BANNER_ID, "https://www.newwebsite.com", "Updated text",
          LocalDateTime.now(), LocalDate.now());

      String content = objectMapper.writeValueAsString(request);

      mockMvc.perform(put(PROTECTED_API + "/link-banners")
          .contentType(MediaType.APPLICATION_JSON)
          .content(content)
      ).andExpectAll(
          status().isOk(),
          jsonPath("$.id").value(request.id()),
          jsonPath("$.createdOn").exists(),
          jsonPath("$.lastUpdated").exists(),
          jsonPath("$.text").value(request.text()),
          jsonPath("$.url").value(request.url())
      );
    }

    @Test
    @DisplayName("Should return 400 (bad request) when invalid input")
    void shouldReturn400_whenInvalidInput() throws Exception {
      LinkBannerUpdateRequest request = new LinkBannerUpdateRequest(
          LINK_BANNER_ID, "invalid", "Updated text",
          LocalDateTime.now(), LocalDate.now());

      String content = objectMapper.writeValueAsString(request);

      mockMvc.perform(put(PROTECTED_API + "/link-banners")
              .contentType(MediaType.APPLICATION_JSON)
              .content(content))
          .andExpect(status().isBadRequest());
    }
  }

  @Nested
  @DisplayName("Delete link banner")
  @WithMockUser(roles = CONTENT_MANAGER_ROLE, username = "user123")
  class DeleteLinkBanner {

    @Test
    @DisplayName("Should return 204 (no content) when deleted")
    void shouldReturn204_whenDeleted() throws Exception {
      assertTrue(linkBannerRepository.existsById(LINK_BANNER_FOR_DELETION_ID));

      mockMvc.perform(delete(PROTECTED_API + "/link-banners/" + LINK_BANNER_FOR_DELETION_ID))
          .andExpect(status().isNoContent());

      assertFalse(linkBannerRepository.existsById(LINK_BANNER_FOR_DELETION_ID));
    }

    @Test
    @DisplayName("Should return 404 (not found) when no banner with provided id")
    void shouldReturn404_whenNotFound() throws Exception {
      mockMvc.perform(delete(PROTECTED_API + "/link-banners/623"))
          .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return 400 (bad request) when invalid id format (text)")
    void shouldReturn400_whenInvalidId() throws Exception {
      mockMvc.perform(delete(PROTECTED_API + "/link-banners/invalid"))
          .andExpect(status().isBadRequest());
    }
  }

}
