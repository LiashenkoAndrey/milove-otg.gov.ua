package gov.milove.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LinkBannerNotFoundException extends RuntimeException {

  public LinkBannerNotFoundException(String message) {
    super(message);
  }
}
