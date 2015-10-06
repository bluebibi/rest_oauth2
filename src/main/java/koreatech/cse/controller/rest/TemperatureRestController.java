package koreatech.cse.controller.rest;

import koreatech.cse.domain.rest.Temperature;
import koreatech.cse.domain.rest.TemperatureXml;
import koreatech.cse.repository.rest.TemperatureMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/thermometer")
public class TemperatureRestController {
    @Inject
    private TemperatureMapper temperatureMapper;

    @Transactional
    @RequestMapping(value="/temperature/{sensorId}", method=RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Temperature> temperature(@PathVariable("sensorId") String sensorId) {
        Temperature temperature = temperatureMapper.findOneBySensorId(sensorId);
        if (temperature == null) {
            System.out.println("Temperature sensor with id " + sensorId + " is not found");
            return new ResponseEntity<Temperature>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Temperature>(temperature, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value="/xml/temperature/{sensorId}", method=RequestMethod.GET, produces = "application/xml")
    public ResponseEntity<TemperatureXml> temperatureXml(@PathVariable("sensorId") String sensorId) {
        Temperature temperature = temperatureMapper.findOneBySensorId(sensorId);
        if (temperature == null) {
            System.out.println("Temperature sensor with id " + sensorId + " is not found");
            return new ResponseEntity<TemperatureXml>(HttpStatus.NOT_FOUND);
        }
        TemperatureXml temperatureXml = new TemperatureXml();
        temperatureXml.setId(temperature.getId());
        temperatureXml.setSensorId(temperature.getSensorId());
        temperatureXml.setTemperature(temperature.getTemperature());
        temperatureXml.setDatetime(temperature.getDatetime());
        temperatureXml.setLocation(temperature.getLocation());
        return new ResponseEntity<TemperatureXml>(temperatureXml, HttpStatus.OK);
    }
}
