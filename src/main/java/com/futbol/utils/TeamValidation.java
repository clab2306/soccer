package com.futbol.utils;

import com.futbol.entities.Team;
import com.futbol.exceptions.BusinessException;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.util.Map;

public class TeamValidation {

    static Map<Integer, Integer> capacidad = Map.of(1, 50000, 2, 10000, 3, 3000);

    private TeamValidation() {

    }

    public static void validateIdTeam(Team team) throws BusinessException {

        if (Strings.isBlank(team.getId())) {
            throw new BusinessException("El id es obligatorio");
        }

    }

    public static void validateTeam(Team team) throws BusinessException {
        validatePositive(team.getNumeroJugadores(), "El número de jugadores");
        validatePositive(team.getCapacidadEstadio(), "La capacidad del estadio");
        validateCreationDate(team.getFechaCreacion());

        validateDivisionAndCapacity(team);
    }

    private static void validateCreationDate(LocalDateTime fechaCreacion) throws BusinessException {
        if (LocalDateTime.now().minusDays(1).isBefore(fechaCreacion)) {
            throw new BusinessException("La fecha de creación no es válida");
        }
    }

    private static void validateDivisionAndCapacity(Team team) throws BusinessException {
        Integer minimumCapacity = capacidad.get(team.getDivision());
        if (minimumCapacity == null) {
            throw new BusinessException("La división no es válida");
        } else {
            if (team.getCapacidadEstadio() <= minimumCapacity) {
                throw new BusinessException("La capacidad del estadio debe ser mayor a ".concat(String.valueOf(minimumCapacity)));
            }
        }
    }

    private static void validatePositive(Integer numberToValidate, String message) throws BusinessException {
        if (numberToValidate < 0) {
            throw new BusinessException(message.concat(" debe ser positivo"));
        }
    }
}
