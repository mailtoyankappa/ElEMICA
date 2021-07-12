package elemica.shipment.repository;

import elemica.shipment.models.ShipmentEntity;
import elemica.shipment.models.Tariff;
import elemica.shipment.service.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TariffEntityRepository extends BaseRepository {

    @Query("SELECT SE FROM Tariff SE WHERE SE.shipmentName = : shipmentName")
    Tariff getShipmentDetailsByName(@Param("shipmentName") String shipmentName);

    Object findAll();
}
