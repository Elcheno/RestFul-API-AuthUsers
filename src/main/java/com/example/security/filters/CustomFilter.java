package com.example.security.filters;

import com.example.model.entity.UserEntity;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class CustomFilter extends org.springframework.web.filter.OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Enumeration<String> headerName = request.getHeaderNames();

        System.out.println("----------- HEADERS -----------");
        while (headerName.hasMoreElements()) {
            String name = headerName.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " : " + value);
        }


//        System.out.println("----------- USER ENTITY -----------");
//        ServletInputStream inputStream = request.getInputStream();
//        System.out.println(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));

//        try {
//            UserEntity userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
//            System.out.println(userEntity);
//
//        } catch (StreamReadException e) {
//            throw new RuntimeException(e);
//
//        } catch (DatabindException e) {
//            throw new RuntimeException(e);
//
//        } finally {
//            if (request.getInputStream() != null) {
//                System.out.println("----------- CLOSING INPUT STREAM -----------");
//                request.getInputStream().close();
//            }
//        }


        filterChain.doFilter(request, response);
    }
}
