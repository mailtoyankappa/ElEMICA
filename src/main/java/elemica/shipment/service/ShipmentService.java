package elemica.shipment.service;

import elemica.shipment.models.ShipmentEntity;
import elemica.shipment.repository.ShipmentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ShipmentService {

    @Autowired
    ShipmentEntityRepository shipmentEntityRepository;

    @Transactional
    public ShipmentEntity createShipment(ShipmentEntity shipmentEntity){
        return createShipmentAndPersist(shipmentEntity);
    }
    public ShipmentEntity createShipmentAndPersist(ShipmentEntity shipmentEntity){
      ShipmentEntity entity = (ShipmentEntity) shipmentEntityRepository.save(shipmentEntity);
        return entity;
    }
}
