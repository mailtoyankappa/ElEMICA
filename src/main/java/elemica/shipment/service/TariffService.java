package elemica.shipment.service;

import elemica.shipment.models.ShipmentEntity;
import elemica.shipment.models.Tariff;
import elemica.shipment.models.VehicleEntity;
import elemica.shipment.repository.ShipmentEntityRepository;
import elemica.shipment.repository.TariffEntityRepository;
import elemica.shipment.repository.VehicleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Service
public class TariffService {

    @Autowired
    TariffEntityRepository tariffEntityRepository;

    @Autowired
    ShipmentEntityRepository shipmentEntityRepository;

    @Autowired
    VehicleEntityRepository vehicleEntityRepository;

    @Transactional
    public Tariff createShipment(Tariff tariff){
        return createShipmentAndPersist(tariff);
    }
    public Tariff createShipmentAndPersist(Tariff tariff){
        ShipmentEntity shipmentEntity = shipmentEntityRepository.getShipmentDetailsByName(tariff.getShipmentName());
        List<VehicleEntity> vehicleEntity = vehicleEntityRepository.getVehicleDetailsByWeightCapacity(Integer.parseInt(shipmentEntity.getWeight().toString()));
        Tariff finalTariff = calculateRateAndAssignDiscount(vehicleEntity, shipmentEntity, tariff);
        Tariff entity = (Tariff) tariffEntityRepository.save(tariff);
        return entity;
    }

    public <T> List<T> listShipment(){
        return (List<T>) tariffEntityRepository.findAll();
    }

    public Tariff calculateRateAndAssignDiscount(List<VehicleEntity> vehicleEntities, ShipmentEntity shipmentEntity, Tariff tariff){
        List<Tariff> finalTariff = (List<Tariff>) new Tariff();
        Tariff result = new Tariff();
        for(VehicleEntity vehicleEntity: vehicleEntities) {
            Tariff tariff1 = new Tariff();
            tariff1.setShipmentName(tariff.getShipmentName());
            tariff1.setRatePerKG(tariff.getRatePerKG());
            boolean eligibility = false;
            double avg;
            int diff, sum;
            int weightCapacity = Integer.parseInt(vehicleEntity.getWeightCapacity().toString());
            int shipmentWeight = Integer.parseInt(shipmentEntity.getWeight().toString());
            int ratePerKG = Integer.parseInt(tariff.getRatePerKG().toString());
            if(weightCapacity>=shipmentWeight) {
                diff = Math.subtractExact(weightCapacity, shipmentWeight);
                sum = Math.addExact(weightCapacity, shipmentWeight) / 2;
                avg = Math.floorDiv(diff, sum);
                if (avg<=30) {
                    eligibility = true;
                    tariff1.setDiscount(30);
                }else {
                    tariff1.setDiscount(0);
                }
            }
            tariff1.setCost(calcCost(shipmentWeight, ratePerKG, eligibility));
            finalTariff.add(tariff1);
        }
        result = finalTariff.stream()
                .min(Comparator.comparingInt(Tariff::getCost))
                .get();
        return result;
    }
    public int calcCost(int  shipmentWeight, int  ratePerKG, boolean eligibility){
        int sum, discount;
        sum = shipmentWeight * ratePerKG;
        if(eligibility==true) {
            discount = (sum * 30)/100;
            sum = sum - discount;
        }
        return sum;
    }
}

