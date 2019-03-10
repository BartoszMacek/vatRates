package pl.cytawek.vatRates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cytawek.vatRates.entieties.VatEntity;

import java.util.Optional;

@Repository
public interface VatRepositories extends JpaRepository <VatEntity, Integer> {

    //Optional<VatEntity> findByCountryCode(String countryCode);

}
