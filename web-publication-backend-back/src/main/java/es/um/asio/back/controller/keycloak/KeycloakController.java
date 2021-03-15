package es.um.asio.back.controller.keycloak;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(KeycloakController.Mappings.BASE)
public class KeycloakController {

	@GetMapping(KeycloakController.Mappings.IS_LOGIN)
	public boolean islogin() {
		AccessToken accessToken = getAccessToken();

		if (accessToken != null) {
			return accessToken.isActive();
		} else {
			return false;
		}

	}

	private AccessToken getAccessToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KeycloakPrincipal keycloakPrincipal = null;
		if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
			keycloakPrincipal = (KeycloakPrincipal) authentication.getPrincipal();
			return keycloakPrincipal.getKeycloakSecurityContext().getToken();
		} else {
			return null;
		}

	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/keycloak";

		/**
		 * Mapping for isLogin.
		 */
		protected static final String IS_LOGIN = "/isActive";

	}
}
