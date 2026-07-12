package com.transitops.validator;
import java.util.Locale;
import org.springframework.stereotype.Component;
@Component
public class EmailValidator { public String normalize(String email) { return email.trim().toLowerCase(Locale.ROOT); } }
