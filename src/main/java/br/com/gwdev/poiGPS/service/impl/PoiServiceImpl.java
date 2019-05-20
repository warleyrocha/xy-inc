package br.com.gwdev.poiGPS.service.impl;

import br.com.gwdev.poiGPS.dao.PoiRepository;
import br.com.gwdev.poiGPS.model.Poi;
import br.com.gwdev.poiGPS.model.dto.FiltroDTO;
import br.com.gwdev.poiGPS.model.dto.PoiDTO;
import br.com.gwdev.poiGPS.service.PoiService;
import br.com.gwdev.poiGPS.util.PoiBusinessException;
import br.com.gwdev.poiGPS.util.mapper.PoiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoiServiceImpl implements PoiService {

    @Autowired
    private PoiRepository poiRepository;

    @Override
    public PoiDTO createPoi(PoiDTO poi) throws PoiBusinessException {
        validateIsUnique(poi);
        return PoiMapper.INSTANCE.poiToPoiDTO(poiRepository.save(PoiMapper.INSTANCE.poiDTOtoPoi(poi)));
    }

    @Override
    public List<PoiDTO> getPois() {
        List<Poi> pois = poiRepository.findAll();
        pois.sort(Comparator.comparing(Poi::getDescPoi));
        return PoiMapper.INSTANCE.poiToPoiDTO(pois);
    }

    @Override
    public List<String> findPoiByProximidade(FiltroDTO filtroPoi) {
        List<Poi> pois = poiRepository.findAll();
        return pois.stream().filter(poi -> isProximo(poi, filtroPoi)).map(Poi::getDescPoi).sorted().collect(Collectors.toList());
    }

    /**
     * Private Methods
     */

    private void validateIsUnique(PoiDTO poi) throws PoiBusinessException {
        if (poiRepository.findByDescPoiIgnoreCase(poi.getDescPoi()) != null) {
            throw new PoiBusinessException("Não é possível cadastrar um Ponto de Interesse com o mesmo nome.");
        }
        if (poiRepository.findByCoordenadaXAndCoordenadaY(poi.getCoordenadaX(), poi.getCoordenadaY()) != null) {
            throw new PoiBusinessException("Não é possível cadastrar um Ponto de Interesse no mesmo local de um já cadastrado.");
        }
    }

    private boolean isProximo(Poi poiDTO, FiltroDTO filtroPoi) {
        Integer percX = Math.abs(poiDTO.getCoordenadaX() - filtroPoi.getCoordenadaX());
        Integer percY = Math.abs(poiDTO.getCoordenadaY() - filtroPoi.getCoordenadaY());
        return (percX + percY <= filtroPoi.getMaxDistancia());
    }

}

