package gov.milove.testdata;

import gov.milove.main.repository.jpa.LinkBannerRepository;
import gov.milove.main.service.impl.LinkBannerServiceImpl;
import gov.milove.main.util.mapper.LinkBannerMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LinkBannerServiceUnitTest {

  @Mock
  private LinkBannerRepository linkBannerRepository;

  @Mock
  private LinkBannerMapper linkBannerMapper;

  @InjectMocks
  private LinkBannerServiceImpl underTest;




}
