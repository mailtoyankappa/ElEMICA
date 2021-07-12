package elemica.shipment.models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Objects;

@Entity
@Table(name = "VEHICLE_DETAILS")
public class VehicleEntity {

    @Id
    @SequenceGenerator(name = "vehicle_details_seq_gen", sequenceName = "vehicle_details_seq_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_details_seq_gen")
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "weightCapacity")
    private Number weightCapacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public static VehicleEntity getDetails(HashMap<String, String> map){

        VehicleEntity tariff = new VehicleEntity();
        tariff.setVehicleName(map.get("vehicleName"));
        tariff.setWeightCapacity(Integer.parseInt(map.get("weightCapacity")));

        return tariff;
    }

    public Number getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(Number weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleEntity tariff = (VehicleEntity) o;
        return Objects.equals(id, tariff.id) && Objects.equals(vehicleName, tariff.vehicleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehicleName);
    }
}
