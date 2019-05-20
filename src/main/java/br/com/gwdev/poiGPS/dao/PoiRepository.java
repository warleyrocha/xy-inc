package br.com.gwdev.poiGPS.dao;

import br.com.gwdev.poiGPS.model.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoiRepository extends JpaRepository<Poi, Long> {

    Poi findByDescPoiIgnoreCase(String descPoi);

    Poi findByCoordenadaXAndCoordenadaY(Integer x, Integer y);

}
