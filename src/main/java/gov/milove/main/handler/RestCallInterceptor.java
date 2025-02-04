package gov.milove.main.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

@Log4j2
public class RestCallInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull Object handler) {
    log.info("Endpoint called: {}", request.getRequestURI());
    return true;
  }
}
