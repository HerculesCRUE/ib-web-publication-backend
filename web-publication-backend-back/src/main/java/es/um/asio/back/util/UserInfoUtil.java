package es.um.asio.back.util;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserInfoUtil {

	public String getPreferredUsername(HttpServletRequest httpServletRequest) {
		AccessToken accessToken = getAccessToken();
		return accessToken.getPreferredUsername();
	}

	public String getUserGuid() {
		return getAccessToken().getSubject();
	}

	private AccessToken getAccessToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) authentication.getPrincipal();
		return keycloakPrincipal.getKeycloakSecurityContext().getToken();
	}
}
