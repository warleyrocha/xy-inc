package br.com.gwdev.poiGPS.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class FiltroDTO {

    private Integer coordenadaX;
    private Integer coordenadaY;
    private Integer maxDistancia;

    public FiltroDTO(Integer coordenadaX, Integer coordenadaY, Integer maxDistancia) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.maxDistancia = maxDistancia;
    }

}
