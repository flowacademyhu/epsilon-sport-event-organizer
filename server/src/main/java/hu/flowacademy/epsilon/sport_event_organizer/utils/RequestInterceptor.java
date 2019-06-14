package hu.flowacademy.epsilon.sport_event_organizer.utils;

import hu.flowacademy.epsilon.sport_event_organizer.repository.TokenRepository;
import hu.flowacademy.epsilon.sport_event_organizer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class RequestInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private TokenRepository tokenRepository;

    @Transactional
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");

        logger.info("Got a request: token is ({})", authorization);

        if (authorization == null) {
            throw new RuntimeException("Missing access token!");
        }

        tokenRepository.findByAccessToken(authorization).orElseThrow(() -> new RuntimeException("Invalid access token!"));

        // TODO renew token

        return super.preHandle(request, response, handler);
    }
}
