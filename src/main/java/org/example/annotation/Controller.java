package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
//유지기간.
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
