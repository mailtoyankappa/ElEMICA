package elemica.shipment.service;

import elemica.shipment.models.ShipmentEntity;
import elemica.shipment.models.VehicleEntity;
import elemica.shipment.repository.ShipmentEntityRepository;
import elemica.shipment.repository.VehicleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VehicleService {

    @Autowired
    VehicleEntityRepository vehicleEntityRepository;

    @Transactional
    public VehicleEntity createShipment(VehicleEntity vehicleEntity){
        return createShipmentAndPersist(vehicleEntity);
    }
    public VehicleEntity createShipmentAndPersist(VehicleEntity vehicleEntity){
        VehicleEntity entity = (VehicleEntity) vehicleEntityRepository.save(vehicleEntity);
        return entity;
    }
}