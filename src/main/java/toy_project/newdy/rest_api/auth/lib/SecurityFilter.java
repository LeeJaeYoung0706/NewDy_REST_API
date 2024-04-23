package toy_project.newdy.rest_api.auth.lib;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import toy_project.newdy.rest_api.auth.service.AuthService;
import toy_project.newdy.rest_api.common.lib.error_utils.ErrorCode;
import toy_project.newdy.rest_api.common.lib.error_utils.ErrorResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    @Value("${spring.jwt.header}")
    private String SPRING_JWT_HEADER;
    private final JwtTokenProvider jwtTokenProvider;
    private final ErrorResponse errorResponse;
    private final AuthService authService;

    /**
     * url 체크 어떠한 url로 접근했는지
     * @param uriList 원하는 url
     * @param request 실제 접근한 url
     * @return
     */
    private boolean checkURL(List<String> uriList , HttpServletRequest request) {
        if (!uriList.isEmpty()) {
            List<RequestMatcher> antPathRequestMatchers = uriList.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
            OrRequestMatcher orRequestMatcher = new OrRequestMatcher(antPathRequestMatchers);
            return orRequestMatcher.matches(request);
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 모두 접근 가능
        // h2 테스트 데이터 베이스 접근 , 스웨거 접근 , 로그인 및 회원 가입 접근
        final List<String> authURIList = List.of("/auth/**" , "/swagger-ui/**" , "/v3/api-docs/**", "/h2-console/**");

        // 크롬 OPTIONS 요청 처리
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        if (checkURL(authURIList , request)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = request.getHeader(SPRING_JWT_HEADER);

        //region 토큰 검증
        if(token == null){
            errorResponse.error(ErrorCode.INVALID_TOKEN);
            return;
        }

        try {
            if (!jwtTokenProvider.validateToken(token)) {
                // 만료된 토큰 처리
                errorResponse.error(ErrorCode.EXPIRED_TOKEN);
                return;
            }
        } catch (Exception e) {
            // 유효하지 않은토큰 처리
            errorResponse.error(ErrorCode.INVALID_TOKEN);
            return;
        }
        //endregion

        if (token != null) {
            // 토큰으로 아이디 정보 찾을 수 있는 메소드
            String signInId = jwtTokenProvider.getSignInId(token);
            SecurityMemberDetails member = authService.findSecurityMemberDetailsBySigninId(signInId);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( member, null,  member.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
