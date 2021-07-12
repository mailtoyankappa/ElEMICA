package elemica.shipment.controller;

import elemica.shipment.models.ShipmentEntity;
import elemica.shipment.models.VehicleEntity;
import elemica.shipment.service.ShipmentService;
import elemica.shipment.service.VehicleService;
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
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    //private static final LogManager logManager = LoggingConfig();
    Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @RequestMapping(value = "/createVehicleDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createShipment(@RequestBody final HashMap<String, String> hashMap) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        final VehicleEntity entity = VehicleEntity.getDetails(hashMap);
        VehicleEntity vehicleEntity = new VehicleEntity();
        try {
            vehicleEntity = vehicleService.createShipment(entity);

            jsonObject.put("ShipmentId", vehicleEntity.getId());
            jsonObject.put("ShipmentName", vehicleEntity.getVehicleName());
            jsonObject.put("ShipmentWeight", vehicleEntity.getWeightCapacity());
            jsonObject.put("Status", "Success");

        } catch (Exception e) {
            jsonObject.put("VehicleName", vehicleEntity.getVehicleName());
            jsonObject.put("WeightCapacity", vehicleEntity.getWeightCapacity());
            jsonObject.put("Status", "Failed");
            return new ResponseEntity<>(jsonObject, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
    }

}
