package pl.cytawek.vatRates.entieties;


import lombok.Builder;
import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Builder
@Table (name="vat")
public class VatEntity {

    private @Id @GeneratedValue int id;
    private @Column (name = "country_name") String countryName;
    private @Column (name = "country_code") String countryCode;
    private @Column (name = "super_reduced") double superReduced;
    private double reduced;
    private double reduced1;
    private double reduced2;
    private double standard;
    private double parking;


}
