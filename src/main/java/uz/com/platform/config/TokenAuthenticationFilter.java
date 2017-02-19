package uz.com.platform.config;

import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.com.platform.app.users.User;
import uz.com.platform.app.users.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.StringTokenizer;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String AUTH_TOKEN_HEADER_NAME = "X-AUTH-TOKEN";
    @Setter
    private UserService userService;
    @Setter
    private String basicAuthUrlPattern;
    @Setter
    private String tokenUrlPattern;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (new AntPathMatcher().match(basicAuthUrlPattern, request.getRequestURI())) {
            String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
            if (header != null) {
                StringTokenizer st = new StringTokenizer(header);
                if ("Basic".equals(st.nextToken())) {
                    st = new StringTokenizer(new String(DatatypeConverter.parseBase64Binary(st.nextToken())), ":");
                    String username = st.nextToken();
                    String password = st.nextToken();
                    User user = userService.findByUsername(username);
                    if (user != null) {
                        if (user.isEnabled()) {
                            if (password.equals(user.getPassword())) {
                                SecurityContextHolder.getContext().setAuthentication(user);
                                filterChain.doFilter(request, response);
                            } else {
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Incorrect password.");
                            }
                        } else {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not enabled.");
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid basic authentication header.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Basic authentication is required.");
            }
        } else if (new AntPathMatcher().match(tokenUrlPattern, request.getRequestURI())) {
            String header = request.getHeader(AUTH_TOKEN_HEADER_NAME);
            if (header != null) {
                User user = userService.findByTokenUuid(header);
                if (user != null) {
                    if (user.isEnabled()) {
                        SecurityContextHolder.getContext().setAuthentication(user);
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not enabled.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is not valid.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token authentication is required.");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
