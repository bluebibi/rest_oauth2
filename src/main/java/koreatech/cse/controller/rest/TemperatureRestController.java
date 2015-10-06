package koreatech.cse.controller.rest;

import koreatech.cse.domain.rest.Temperature;
import koreatech.cse.domain.rest.TemperatureXml;
import koreatech.cse.repository.rest.TemperatureMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.LinkedList;
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
    @RequestMapping(value="/xml/temperature/{sensorId}", method=RequestMethod.GET, produces="application/xml")
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

    @Transactional
    @RequestMapping(value="/temperature/location/{location}", method=RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<Temperature>> temperatureByLocation(@PathVariable("location") String location) {
        List<Temperature> temperatureList = temperatureMapper.findByLocation(location);
        if (temperatureList.size() == 0) {
            System.out.println("Temperature sensors with location of " + location + " are not found");
            return new ResponseEntity<List<Temperature>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Temperature>>(temperatureList, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value="/xml/temperature/location/{location}", method=RequestMethod.GET, produces="application/xml")
    public ResponseEntity<List<TemperatureXml>> temperatureByLocationXml(@PathVariable("location") String location) {
        List<Temperature> temperatureList = temperatureMapper.findByLocation(location);
        if (temperatureList.size() == 0) {
            System.out.println("Temperature sensors with location of " + location + " are not found");
            return new ResponseEntity<List<TemperatureXml>>(HttpStatus.NOT_FOUND);
        }
        List<TemperatureXml> temperatureXmlList = new LinkedList<TemperatureXml>();
        for (Temperature temperature : temperatureList) {
            TemperatureXml temperatureXml = new TemperatureXml();
            temperatureXml.setId(temperature.getId());
            temperatureXml.setSensorId(temperature.getSensorId());
            temperatureXml.setTemperature(temperature.getTemperature());
            temperatureXml.setDatetime(temperature.getDatetime());
            temperatureXml.setLocation(temperature.getLocation());
            temperatureXmlList.add(temperatureXml);
        }
        return new ResponseEntity<List<TemperatureXml>>(temperatureXmlList, HttpStatus.OK);
    }
}
