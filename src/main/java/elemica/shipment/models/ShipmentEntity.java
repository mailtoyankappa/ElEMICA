package elemica.shipment.models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Objects;

@Entity
@Table(name = "SHIPMENT_DETAILS")
public class ShipmentEntity {

    @Id
    @SequenceGenerator(name = "shipment_details_seq_gen", sequenceName = "shipment_details_seq_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_details_seq_gen")
    @Column(name = "shipment_id")
    private Long id;

    @Column(name = "shipment_name")
    private String shipmentName;

    @Column(name = "weight")
    private Number weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public Number getWeight() {
        return weight;
    }

    public void setWeight(Number weight) {
        this.weight = weight;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public static ShipmentEntity getDetails(HashMap<String, String> map){

        ShipmentEntity shipmentEntity = new ShipmentEntity();
        shipmentEntity.setShipmentName(map.get("shipmentName"));
        shipmentEntity.setWeight(Integer.parseInt(map.get("weight")));

        return shipmentEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipmentEntity tariff = (ShipmentEntity) o;
        return Objects.equals(id, tariff.id) && Objects.equals(shipmentName, tariff.shipmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipmentName);
    }
}
