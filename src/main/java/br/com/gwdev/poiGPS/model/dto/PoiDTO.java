package br.com.gwdev.poiGPS.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
@ToString
public class PoiDTO {

    @Size(min = 3, message = "Deve preencher com o mínimo de 3 caracteres")
    private String descPoi;

    @Min(value = 0L, message = "A coordenada X deve ser preenchida com o um valor positivo")
    private Integer coordenadaX;

    @Min(value = 0L, message = "A coordenada Y deve ser preenchida com o um valor positivo")
    private Integer coordenadaY;

}
