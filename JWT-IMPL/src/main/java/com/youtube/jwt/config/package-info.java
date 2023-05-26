/**
 * 
 */
/**
 * @author sanjeevsoni
 *
 */
package com.youtube.jwt.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
Here is an overview of how JWT authentication works.

1. User logs in: The user provides their login credentials
	(e.g. username and password) to the server.
	
2. Server verifies credentials: The server verifies the user's credentials. 
	If the credentials are valid, the server creates a JWT token.

3. JWT token is returned to the client: The server sends the JWT token to the client.

4. Client sends requests with JWT token: The client includes the JWT token in the
	Authorization header of each subsequent API request.

5. Server verifies JWT token: The server verifies the JWT token on each request
	to ensure that the user is authorized to access the requested resource.

6. Response is sent to the client: If the JWT token is valid,
	the requested resource is returned to the client.

**/


/**

The JWT token itself contains three parts:

1. Header: The header typically contains information about the token such
 	as the type of token and the cryptographic algorithm used to sign the token.

2. Payload: The payload contains claims or statements about the user, 
	such as the user's ID, username, and other information.

3. Signature: The signature is created by encoding the header and payload,
 	and then signing the resulting string using a secret key. The signature is used to verify the authenticity of the JWT token.
**/



//CORS CONFIG
/**
1. CORS is a security feature that restricts web pages from making requests to a different
 domain than the one that served the original page.

2. The addCorsMappings method sets the allowed origins, headers, and methods for the application's endpoints.

3. Additionally, it allows cookies to be sent along with the request by setting the allowCredentials flag to true.
**/


//JwtAuthEntryPoint
/**
 The JwtAuthEntryPoint class overrides the commence method to handle unauthorized requests.
 The commence method is called by the framework when an unauthenticated user tries to access a protected resource 
 and the server will return an HTTP 401 response with the error message.
 **/

//JwtRequestFilter
/**
 * This class is intended to intercept incoming HTTP requests and perform some processing before passing the request to the next filter or the endpoint itself.
 * 
 * The purpose of this specific filter is to intercept incoming HTTP requests and check whether they contain a valid JSON Web Token (JWT) in the Authorization header. 
 * If the JWT is valid, the filter should allow the request to proceed to the endpoint or the next filter in the chain.
 * If the JWT is invalid or missing, the filter should return an error response.
 */

//WebSecurityConfig

/**
 * @EnableGlobalMethodSecurity(prePostEnabled = true)
 * The prePostEnabled parameter of the annotation specifies whether Spring Security should enable pre- and post-annotations for method-level security. 
 * For example, if a method is annotated with @PreAuthorize("hasRole('ADMIN')"),
 * 		it means that the method can only be executed by users who have the "ADMIN" role. 
 * 		Similarly, if a method is annotated with @PostAuthorize("returnObject.owner == authentication.principal.username"), 
 * 		it means that the method can only return objects whose "owner" property matches the username of the currently authenticated user.
 *
	//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

/** It means that the server will not create or use any session to store user-specific data. Each request is considered as a separate transaction and the server will not maintain any state information about the client across requests.
 * This is useful when building stateless services or APIs, where you want to keep the server stateless and avoid the overhead of managing sessions.
 */




