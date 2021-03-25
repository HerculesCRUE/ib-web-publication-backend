package es.um.asio.back.controller.keycloak;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.back.config.security.Roles;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(KeycloakController.Mappings.BASE)
public class KeycloakController {

	@Value("${keycloak.resource}")
	private String resource;

	@GetMapping(KeycloakController.Mappings.IS_LOGIN)
	public boolean islogin() {
		AccessToken accessToken = getAccessToken();

		if (accessToken != null) {
			return accessToken.isActive();
		} else {
			return false;
		}

	}

	/**
	 * Method isAdmin
	 * 
	 * @return true/false for get rol Admin for token access
	 */
	@GetMapping(KeycloakController.Mappings.IS_ADMIN)
	public boolean isAdmin() {
		AccessToken accessToken = getAccessToken();

		boolean isAdmin = false;

		for (String per : accessToken.getResourceAccess(resource).getRoles()) {
			if (Roles.ROLE_ADMIN.equals(per)) {
				isAdmin = true;
			}
		}
		return isAdmin;
	}

	@GetMapping(KeycloakController.Mappings.GET_NAME)
	public String getName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		KeycloakPrincipal keycloakPrincipal = null;

		if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
			keycloakPrincipal = (KeycloakPrincipal) authentication.getPrincipal();
			return keycloakPrincipal.getName();
		}

		return null;
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

		protected static final String GET_NAME = "/getName";

		/**
		 * Mapping for isAdmin.
		 */
		protected static final String IS_ADMIN = "/isAdmin";

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
