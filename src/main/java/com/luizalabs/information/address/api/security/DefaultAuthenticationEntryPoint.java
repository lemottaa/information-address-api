package com.luizalabs.information.address.api.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.google.gson.Gson;
import com.luizalabs.information.address.api.dto.error.ErrorDTO;
import com.luizalabs.information.address.api.enums.ErrorCodeEnum;
import com.luizalabs.information.address.api.factory.ResponseBodyFactory;

import lombok.Generated;

@Generated
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json");
		response.getWriter().append(new Gson()
				.toJson(ResponseBodyFactory.with(ErrorDTO.builder()
						.developerMessage("Unauthorized - make sure the header parameter "
								+ "Authorization is valid")
						.userMessage("You are not authorized to perform this operation")
						.errorCode(ErrorCodeEnum.UNAUTHORIZED.getCode()).build())));
    }
}
