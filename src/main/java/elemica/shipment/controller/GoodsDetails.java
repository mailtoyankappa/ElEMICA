package elemica.shipment.controller;

import elemica.shipment.models.ShipmentEntity;
import elemica.shipment.models.Tariff;
import elemica.shipment.service.ShipmentService;
import elemica.shipment.service.TariffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.LogManager;

@RestController
@RequestMapping(value = "/shipment/details")
public class GoodsDetails {

    @Autowired
    private TariffService tariffService;

    //private static final LogManager logManager = LoggingConfig();
    Logger logger = LoggerFactory.getLogger(GoodsDetails.class);

    @RequestMapping(value = "/createShipment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createShipment(@RequestBody final HashMap<String, String> hashMap) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        final Tariff tariff = Tariff.getDetails(hashMap);
        Tariff tariffEntity = new Tariff();
        try {
            tariffEntity = tariffService.createShipment(tariff);

            jsonObject.put("ShipmentId", tariffEntity.getId());
            jsonObject.put("ShipmentName", tariffEntity.getShipmentName());
            jsonObject.put("ShipmentWeight", tariffEntity.getRatePerKG());
            jsonObject.put("RatePerKG", tariffEntity.getDiscount());
            jsonObject.put("ApplicableVehicle", tariffEntity.getApplicableVehicle());
            jsonObject.put("Status", "Success");

        } catch (Exception e) {
            jsonObject.put("ShipmentName", tariff.getShipmentName());
            jsonObject.put("ShipmentWeight", tariff.getRatePerKG());
            jsonObject.put("Status", "Failed");
            return new ResponseEntity<>(jsonObject, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/ListShipment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listShipment() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        List<Tariff> tariffEntity = new ArrayList<>();
        try {
            tariffEntity = tariffService.listShipment();

            jsonObject.put("ShipmentObject", tariffEntity);
            jsonObject.put("Status", "Success");

        } catch (Exception e) {
            jsonObject.put("Status", "Failed");
            return new ResponseEntity<>(jsonObject, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }


}
