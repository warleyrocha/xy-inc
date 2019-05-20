package br.com.gwdev.poiGPS.service;

import br.com.gwdev.poiGPS.model.dto.FiltroDTO;
import br.com.gwdev.poiGPS.model.dto.PoiDTO;
import br.com.gwdev.poiGPS.util.PoiBusinessException;

import java.util.List;

public interface PoiService {

    PoiDTO createPoi(PoiDTO poi) throws PoiBusinessException;

    List<PoiDTO> getPois();

    List<String> findPoiByProximidade(FiltroDTO filtroPoi);
}
