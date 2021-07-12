package elemica.shipment.models;

import javax.naming.ldap.HasControls;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Objects;

@Entity
@Table(name = "tariff_data")
public class Tariff {

    @Id
    @SequenceGenerator(name = "tariff_details_seq_gen", sequenceName = "tariff_details_seq_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tariff_details_seq_gen")
    @Column(name = "tariff_id")
    private Long id;

    @Column(name = "shipment_name")
    private String shipmentName;

    @Column(name = "rate_per_kg")
    private Number ratePerKG;

    @Column(name = "discount")
    private Number discount;

    @Column(name = "applicable_vehicle")
    private String applicableVehicle;

    @Column(name = "COST")
    private int cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public Number getRatePerKG() {
        return ratePerKG;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setRatePerKG(Number ratePerKG) {
        this.ratePerKG = ratePerKG;
    }

    public Number getDiscount() {
        return discount;
    }

    public void setDiscount(Number discount) {
        this.discount = discount;
    }

    public String getApplicableVehicle() {
        return applicableVehicle;
    }

    public void setApplicableVehicle(String applicableVehicle) {
        this.applicableVehicle = applicableVehicle;
    }

    public static Tariff getDetails(HashMap<String, String> map){

        Tariff tariff = new Tariff();
        tariff.setShipmentName(map.get("shipmentName"));
        tariff.setRatePerKG(Integer.parseInt(map.get("rate")));

        return tariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(id, tariff.id) && Objects.equals(shipmentName, tariff.shipmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipmentName);
    }
}
