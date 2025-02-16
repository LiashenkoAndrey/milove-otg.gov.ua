package gov.milove.main.handler;

import static gov.milove.main.constants.Constants.TRACE_ID_HEADER_NAME;
import static gov.milove.main.constants.Constants.TRACE_ID_KEY;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class RestCallInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler) {
    String traceId = request.getHeader(TRACE_ID_HEADER_NAME);
    if (traceId == null) {
      traceId = UUID.randomUUID().toString();
      log.info("Generated a new trace id: {}", traceId);
    }

    MDC.put(TRACE_ID_KEY, traceId);
    response.addHeader(TRACE_ID_HEADER_NAME, traceId);

    log.info("Endpoint called: {}", request.getRequestURI());
    return true;
  }

  @Override
  public void afterCompletion(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler,
      Exception ex) throws Exception {
    log.info("Endpoint response status is: {}", response.getStatus());
    MDC.clear();
  }
}
