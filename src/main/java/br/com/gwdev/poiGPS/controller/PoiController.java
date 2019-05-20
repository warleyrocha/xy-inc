package br.com.gwdev.poiGPS.controller;

import br.com.gwdev.poiGPS.model.dto.FiltroDTO;
import br.com.gwdev.poiGPS.model.dto.PoiDTO;
import br.com.gwdev.poiGPS.service.PoiService;
import br.com.gwdev.poiGPS.util.PoiBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/api/")
public class PoiController {

    @Autowired
    private PoiService poiService;

    @ResponseBody
    @PostMapping(path = "Poi")
    public PoiDTO createPoi(@Valid @RequestBody PoiDTO poi) throws PoiBusinessException {
       return poiService.createPoi(poi);
    }

    @ResponseBody
    @GetMapping(path = "Pois")
    public List<PoiDTO> getPois() {
        return poiService.getPois();
    }

    @ResponseBody
    @GetMapping(path = "Poi/{coordenadaX}/{coordenadaY}/{maxDistancia}")
    public List<String> findPoiByProximidade(
            @PathVariable("coordenadaX") @NotNull Integer x,
            @PathVariable("coordenadaY") @NotNull Integer y,
            @PathVariable("maxDistancia") @NotNull Integer maxDistancia) {
        return poiService.findPoiByProximidade(new FiltroDTO(x,y,maxDistancia));
    }

}
