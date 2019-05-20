
package br.com.gwdev.poiGPS;

import br.com.gwdev.poiGPS.controller.PoiController;
import br.com.gwdev.poiGPS.model.dto.PoiDTO;
import br.com.gwdev.poiGPS.util.PoiBusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PoiGPSIntegrationTests {

    @Autowired
    PoiController poiController;

    @Test
    public void testFindAll() {
        assertEquals(getFindAllDTOResult(), poiController.getPois());
    }

    @Test
    public void testCreatePoi() throws PoiBusinessException {
        assertEquals(returnPoiDTO("Casa", 10, 10),
                poiController.createPoi(returnPoiDTO("Casa", 10, 10)));
    }

    @Test(expected = PoiBusinessException.class)
    public void testCreatePoiWithSameName() throws PoiBusinessException {
        poiController.createPoi(returnPoiDTO("Lanchonete", 12, 10));
    }

    @Test(expected = PoiBusinessException.class)
    public void testCreatePoiWithSameLocation() throws PoiBusinessException {
        poiController.createPoi(returnPoiDTO("Casa", 27, 12));
    }

    @Test
    public void TestPoiByProximidade() {
        assertEquals(Arrays.asList("Joalheria","Lanchonete", "Pub", "SuperMercado"),
                poiController.findPoiByProximidade(20,10,10));
    }



    /**
     * Private Methods
     */

    private List<PoiDTO> getFindAllDTOResult() {
        List<PoiDTO> findAll = new ArrayList<>();
        findAll.add(returnPoiDTO("Lanchonete", 27, 12));
        findAll.add(returnPoiDTO("Posto", 31, 18));
        findAll.add(returnPoiDTO("Joalheria", 15, 12));
        findAll.add(returnPoiDTO("Floricultura", 19, 21));
        findAll.add(returnPoiDTO("Pub", 12, 8));
        findAll.add(returnPoiDTO("SuperMercado", 23, 6));
        findAll.add(returnPoiDTO("Churrascaria", 28, 2));
        Collections.sort(findAll, Comparator.comparing(PoiDTO::getDescPoi));
        return findAll;
    }

    private PoiDTO returnPoiDTO(String desc, Integer x, Integer y) {
        PoiDTO result = new PoiDTO();
        result.setDescPoi(desc);
        result.setCoordenadaX(x);
        result.setCoordenadaY(y);
        return result;
    }

}
