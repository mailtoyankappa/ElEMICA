package elemica.shipment.controller;

import elemica.shipment.models.ShipmentEntity;
import elemica.shipment.service.ShipmentService;
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

import java.util.HashMap;

@RestController
@RequestMapping(value = "/shipment/details")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    //private static final LogManager logManager = LoggingConfig();
    Logger logger = LoggerFactory.getLogger(ShipmentController.class);

    @RequestMapping(value = "/createShipment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createShipment(@RequestBody final HashMap<String, String> hashMap) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        final ShipmentEntity shipmentEntity = ShipmentEntity.getDetails(hashMap);
        ShipmentEntity shipmentEntities = new ShipmentEntity();
        try {
            shipmentEntities = shipmentService.createShipment(shipmentEntity);

            jsonObject.put("ShipmentId", shipmentEntities.getId());
            jsonObject.put("ShipmentName", shipmentEntities.getShipmentName());
            jsonObject.put("ShipmentWeight", shipmentEntities.getWeight());
            jsonObject.put("Status", "Success");

        } catch (Exception e) {
            jsonObject.put("ShipmentName", shipmentEntity.getShipmentName());
            jsonObject.put("ShipmentWeight", shipmentEntity.getWeight());
            jsonObject.put("Status", "Failed");
            return new ResponseEntity<>(jsonObject, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
    }

}
