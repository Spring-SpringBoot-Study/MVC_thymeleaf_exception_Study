package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    // Exception을 처리해서 정상 흐름처럼 변경하는 것
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");

                // 기존 IllegalArgumentException은 500에러로 호출이 되었는데, 해당 try-catch 문에어 500 에러를 먹고, 400 에러로 바꿔서 발생시켜주는 것
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage()); // 400 에러임

                return new ModelAndView();
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }
        return null;
    }

}
