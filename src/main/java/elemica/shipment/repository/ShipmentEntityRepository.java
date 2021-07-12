package elemica.shipment.repository;

import elemica.shipment.models.ShipmentEntity;
import elemica.shipment.service.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShipmentEntityRepository extends BaseRepository {

@Query("SELECT SE FROM ShipmentEntity SE WHERE SE.shipmentName = : shipmentName")
    ShipmentEntity getShipmentDetailsByName(@Param("shipmentName") String shipmentName);
}
