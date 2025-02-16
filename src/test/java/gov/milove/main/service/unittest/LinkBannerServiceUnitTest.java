package gov.milove.main.service.unittest;

import gov.milove.main.domain.LinkBanner;
import gov.milove.main.dto.request.LinkBannerUpdateRequest;
import gov.milove.main.exception.LinkBannerNotFoundException;
import gov.milove.main.repository.jpa.LinkBannerRepository;
import gov.milove.main.service.impl.LinkBannerServiceImpl;
import gov.milove.main.util.mapper.LinkBannerMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LinkBannerServiceUnitTest {

  @Mock
  private LinkBannerRepository linkBannerRepository;

  @Mock
  private LinkBannerMapper linkBannerMapper;

  @InjectMocks
  private LinkBannerServiceImpl underTest;

  @Test
  @DisplayName("Should return all banners sorted by createdOn descending")
  void shouldReturnAllBannersSorted() {
    List<LinkBanner> expectedBanners = List.of(new LinkBanner(), new LinkBanner());
    when(linkBannerRepository.findAll(Sort.by("createdOn").descending())).thenReturn(
        expectedBanners);

    List<LinkBanner> actualBanners = underTest.findAllBanners();

    assertIterableEquals(expectedBanners, actualBanners);
    verify(linkBannerRepository).findAll(Sort.by("createdOn").descending());
  }

  @Test
  @DisplayName("Should save link banner when valid banner provided")
  void shouldSaveLinkBanner() {
    LinkBanner linkBanner = new LinkBanner();
    when(linkBannerRepository.save(any(LinkBanner.class))).thenReturn(linkBanner);

    LinkBanner savedBanner = underTest.save(linkBanner);

    assertNotNull(savedBanner);
    verify(linkBannerRepository).save(linkBanner);
  }

  @Nested
  @DisplayName("Update Link Banner")
  class UpdateLinkBanner {

    private final LinkBannerUpdateRequest request = new LinkBannerUpdateRequest(1L,
        "New Text", "http://newurl.com", LocalDateTime.now(), LocalDate.now());

    @Test
    @DisplayName("Should update link banner when valid update request provided")
    void shouldUpdateLinkBanner() {
      LinkBanner linkBanner = new LinkBanner();
      when(linkBannerRepository.findById(request.id())).thenReturn(Optional.of(linkBanner));
      when(linkBannerRepository.save(any(LinkBanner.class))).thenReturn(linkBanner);

      LinkBanner updatedBanner = underTest.update(request);

      assertNotNull(updatedBanner);
      verify(linkBannerMapper).updateLinkBannerFromDto(request, linkBanner);
      verify(linkBannerRepository).save(linkBanner);
    }

    @Test
    @DisplayName("Should throw exception when banner not found")
    void shouldThrowExceptionWhenBannerNotFound() {
      when(linkBannerRepository.findById(request.id())).thenReturn(Optional.empty());

      assertThrows(LinkBannerNotFoundException.class, () -> underTest.update(request));
      verify(linkBannerRepository, never()).save(any(LinkBanner.class));
    }
  }

  @Nested
  @DisplayName("Delete Link Banner")
  class DeleteLinkBanner {

    private final Long id = 1L;

    @Test
    @DisplayName("Should delete link banner when valid id provided")
    void shouldDeleteLinkBanner() {
      LinkBanner linkBanner = new LinkBanner();
      when(linkBannerRepository.findById(id)).thenReturn(Optional.of(linkBanner));

      underTest.deleteById(id);

      verify(linkBannerRepository).deleteById(id);
    }

    @Test
    @DisplayName("Should throw exception when banner to delete not found")
    void shouldThrowExceptionWhenBannerToDeleteNotFound() {
      when(linkBannerRepository.findById(id)).thenReturn(Optional.empty());

      assertThrows(LinkBannerNotFoundException.class, () -> underTest.deleteById(id));
      verify(linkBannerRepository, never()).deleteById(any());
    }
  }
}