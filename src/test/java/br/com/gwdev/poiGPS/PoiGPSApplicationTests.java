package br.com.gwdev.poiGPS;

import br.com.gwdev.poiGPS.dao.PoiRepository;
import br.com.gwdev.poiGPS.model.Poi;
import br.com.gwdev.poiGPS.model.dto.FiltroDTO;
import br.com.gwdev.poiGPS.model.dto.PoiDTO;
import br.com.gwdev.poiGPS.service.impl.PoiServiceImpl;
import br.com.gwdev.poiGPS.util.PoiBusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PoiGPSApplicationTests {

    @Mock
    PoiRepository poiRepositoryMock;

    @InjectMocks
    PoiServiceImpl poiService;

    @Test
    public void testFindAll() {
        when(poiRepositoryMock.findAll()).thenReturn(getFindAllResult());
        assertEquals(getFindAllDTOResult(), poiService.getPois());
    }

    @Test(expected = PoiBusinessException.class)
    public void testCreatePoiWithSameName() throws PoiBusinessException {
        when(poiRepositoryMock.findByDescPoiIgnoreCase("Lanchonete"))
                .thenReturn(returnPoi("Lanchonete", 27, 12));
        poiService.createPoi(returnPoiDTO("Lanchonete", 12, 10));
    }

    @Test(expected = PoiBusinessException.class)
    public void testCreatePoiWithSameLocation() throws PoiBusinessException {
        when(poiRepositoryMock.findByCoordenadaXAndCoordenadaY(27, 12))
                .thenReturn(returnPoi("Lanchonete", 27, 12));
        poiService.createPoi(returnPoiDTO("Casa", 27, 12));
    }

    @Test
    public void TestPoiByProximidade() {
        when(poiRepositoryMock.findAll()).thenReturn(getFindAllResult());
        assertEquals(Arrays.asList("Joalheria","Lanchonete", "Pub", "SuperMercado"),
                poiService.findPoiByProximidade(returnFiltroDTO()));
    }

    /**
     * Private Methods
     */

    private List<Poi> getFindAllResult() {
        List<Poi> findAll = new ArrayList<>();
        findAll.add(returnPoi("Lanchonete", 27, 12));
        findAll.add(returnPoi("Posto", 31, 18));
        findAll.add(returnPoi("Joalheria", 15, 12));
        findAll.add(returnPoi("Floricultura", 19, 21));
        findAll.add(returnPoi("Pub", 12, 8));
        findAll.add(returnPoi("SuperMercado", 23, 6));
        findAll.add(returnPoi("Churrascaria", 28, 2));
        return findAll;
    }

    private List<PoiDTO> getFindAllDTOResult() {
        List<PoiDTO> findAll = new ArrayList<>();
        findAll.add(returnPoiDTO("Lanchonete", 27, 12));
        findAll.add(returnPoiDTO("Posto", 31, 18));
        findAll.add(returnPoiDTO("Joalheria", 15, 12));
        findAll.add(returnPoiDTO("Floricultura", 19, 21));
        findAll.add(returnPoiDTO("Pub", 12, 8));
        findAll.add(returnPoiDTO("SuperMercado", 23, 6));
        findAll.add(returnPoiDTO("Churrascaria", 28, 2));
        findAll.sort(Comparator.comparing(PoiDTO::getDescPoi));
        return findAll;
    }

    private Poi returnPoi(String desc, Integer x, Integer y) {
        Poi result = new Poi();
        result.setDescPoi(desc);
        result.setCoordenadaX(x);
        result.setCoordenadaY(y);
        return result;
    }

    private PoiDTO returnPoiDTO(String desc, Integer x, Integer y) {
        PoiDTO result = new PoiDTO();
        result.setDescPoi(desc);
        result.setCoordenadaX(x);
        result.setCoordenadaY(y);
        return result;
    }

    private FiltroDTO returnFiltroDTO() {
        FiltroDTO filtro = new FiltroDTO();
        filtro.setCoordenadaX(20);
        filtro.setCoordenadaY(10);
        filtro.setMaxDistancia(10);
        return filtro;
    }

}