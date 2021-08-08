package com.futbol.utils;

import com.futbol.entities.Team;
import com.futbol.exceptions.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamValidationTest {

    @ParameterizedTest
    @MethodSource("provideTeamValidations")
    void teamValidationsMustReturnTheExpectedMessage(Team team, String expected) {
        try {
            TeamValidation.validateTeam(team);
        } catch (BusinessException e) {
            assertEquals(expected, e.getMessage());
        }
    }

    private static Stream<Arguments> provideTeamValidations() {
        return Stream.of(
                Arguments.of(new Team("","", "", "", -1, -1, "", -1, LocalDateTime.now()), "El número de jugadores debe ser positivo"),
                Arguments.of(new Team("","", "", "", -1, -1, "", 2, LocalDateTime.now()), "La capacidad del estadio debe ser positivo"),
                Arguments.of(new Team("","", "", "", 2, -1, "", 2, LocalDateTime.now()), "La fecha de creación no es válida"),
                Arguments.of(new Team("","", "", "", 2, -1, "", 2, LocalDateTime.now().minusDays(2)), "La división no es válida"),
                Arguments.of(new Team("","", "", "", 2, 1, "", 2, LocalDateTime.now().minusDays(2)), "La capacidad del estadio debe ser mayor a 50000")
       );
    }

    @Test
    void testIdTeam() {
        try {
            Team team = new Team("","", "", "", 2, 1, "", 2, LocalDateTime.now().minusDays(2));
            TeamValidation.validateIdTeam(team);
        } catch (BusinessException e) {
            assertEquals("El id es obligatorio", e.getMessage());
        }
    }
}
