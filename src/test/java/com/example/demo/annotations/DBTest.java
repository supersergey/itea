package com.example.demo.annotations;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.spring.api.DBRider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@SpringBootTest
@ActiveProfiles("test")
@DBRider
@DBUnit(caseSensitiveTableNames = true, schema = "public")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DBTest {
}
