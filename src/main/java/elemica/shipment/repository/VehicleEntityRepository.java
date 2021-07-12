package elemica.shipment.repository;

import elemica.shipment.models.ShipmentEntity;
import elemica.shipment.models.VehicleEntity;
import elemica.shipment.service.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleEntityRepository extends BaseRepository {

    @Query("SELECT ve FROM VehicleEntity ve WHERE ve.weightCapacity >= : wc")
    List<VehicleEntity> getVehicleDetailsByWeightCapacity(@Param("weightCapacity") Integer wc);
}
