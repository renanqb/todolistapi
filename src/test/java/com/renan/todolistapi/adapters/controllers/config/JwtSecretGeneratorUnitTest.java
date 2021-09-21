package com.renan.todolistapi.adapters.controllers.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JwtSecretGeneratorUnitTest {
    
    @Test
    @DisplayName("validate JWT singleton configuration")
    public void validateJwtSingletonConfiguration() {
        JwtSecretGenerator secretGenerator = JwtSecretGenerator.Instance();
        assertThat(secretGenerator, is(samePropertyValuesAs(JwtSecretGenerator.Instance())));
    }
}
