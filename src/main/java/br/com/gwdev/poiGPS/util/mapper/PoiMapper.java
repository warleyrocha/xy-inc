package br.com.gwdev.poiGPS.util.mapper;

import br.com.gwdev.poiGPS.model.Poi;
import br.com.gwdev.poiGPS.model.dto.PoiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PoiMapper {

    PoiMapper INSTANCE = Mappers.getMapper(PoiMapper.class);

    PoiDTO poiToPoiDTO(Poi poi);

    List<PoiDTO> poiToPoiDTO(List<Poi> poi);

    @Mapping(target = "id", ignore = true)
    Poi poiDTOtoPoi(PoiDTO poi);
}
