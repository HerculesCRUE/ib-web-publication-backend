package es.um.asio.back.controller.keycloack;

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
@RequestMapping(KeycloackController.Mappings.BASE)
public class KeycloackController {

	@GetMapping(KeycloackController.Mappings.IS_LOGIN)
	public boolean islogin() {
		AccessToken accessToken = getAccessToken();
		return accessToken.isActive();
	}

	private AccessToken getAccessToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) authentication.getPrincipal();
		return keycloakPrincipal.getKeycloakSecurityContext().getToken();
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/keycloack";

		/**
		 * Mapping for isLogin.
		 */
		protected static final String IS_LOGIN = "/isActive";

	}
}
