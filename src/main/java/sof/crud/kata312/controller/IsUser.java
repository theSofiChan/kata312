package sof.crud.kata312.controller;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize(value = "hasRole('ROLE_USER')" + "and authentication.principal.username.equals(#username) ")
public @interface IsUser { }