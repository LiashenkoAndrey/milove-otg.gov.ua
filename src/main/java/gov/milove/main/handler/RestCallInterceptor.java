package gov.milove.main.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

@Log4j2
public class RestCallInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler) {
    log.info("Endpoint called: {}", request.getRequestURI());
    return true;
  }

  /**
   * Callback after completion of request processing, that is, after rendering the view. Will be
   * called on any outcome of handler execution, thus allows for proper resource cleanup.
   * <p>Note: Will only be called if this interceptor's {@code preHandle}
   * method has successfully completed and returned {@code true}!
   * <p>As with the {@code postHandle} method, the method will be invoked on each
   * interceptor in the chain in reverse order, so the first interceptor will be the last to be
   * invoked.
   * <p><strong>Note:</strong> special considerations apply for asynchronous
   * request processing. For more details see {@link AsyncHandlerInterceptor}.
   * <p>The default implementation is empty.
   *
   * @param request  current HTTP request
   * @param response current HTTP response
   * @param handler  the handler (or {@link HandlerMethod}) that started asynchronous execution, for
   *                 type and/or instance examination
   * @param ex       any exception thrown on handler execution, if any; this does not include
   *                 exceptions that have been handled through an exception resolver
   * @throws Exception in case of errors
   */
  @Override
  public void afterCompletion(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler,
      Exception ex) throws Exception {
    log.info("Endpoint response status is: {}", response.getStatus());
  }
}
