package pl.cytawek.vatRates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.cytawek.vatRates.dtos.RatesForAllCountriesDto;
import pl.cytawek.vatRates.entieties.VatEntity;
import pl.cytawek.vatRates.mappers.VatDtoToVatEntity;
import pl.cytawek.vatRates.repositories.VatRepositories;

@Service
public class VatService {

    final VatRepositories vatRepositories;

    @Autowired
    public VatService(VatRepositories vatRepositories) {
        this.vatRepositories = vatRepositories;
    }


    public RatesForAllCountriesDto getCurrentVat() {
        RestTemplate restTemplate = getRestTemplate();
        RatesForAllCountriesDto ratesForAllCountriesDto = restTemplate.getForObject(
                "https://jsonvat.com/", RatesForAllCountriesDto.class );
        saveVat( ratesForAllCountriesDto );

        return ratesForAllCountriesDto;
    }



    private boolean saveVat(RatesForAllCountriesDto ratesForAllCountriesDto) {
        VatEntity vatEntity = VatDtoToVatEntity.convert( ratesForAllCountriesDto );
        return vatRepositories.save( vatEntity ) != null;
    }

    
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
