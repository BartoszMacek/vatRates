package pl.cytawek.vatRates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.cytawek.vatRates.dtos.RatesForAllCountriesDto;
import pl.cytawek.vatRates.entieties.VatEntity;
import pl.cytawek.vatRates.mappers.VatDtoToVatEntity;
import pl.cytawek.vatRates.repositories.VatRepositories;

import java.util.List;


@Service
public class VatService {

    private final VatRepositories vatRepositories;

    @Autowired
    public VatService(VatRepositories vatRepositories) {
        this.vatRepositories = vatRepositories;
    }

    public RatesForAllCountriesDto getCurrentVat() {
        RestTemplate restTemplate = getRestTemplate();
        RatesForAllCountriesDto vatRatesDto = restTemplate.getForObject(
                "http://jsonvat.com/", RatesForAllCountriesDto.class );
        return vatRatesDto;
    }


    public RatesForAllCountriesDto saveSingleRecord(String countryCode) {
        RestTemplate restTemplate = getRestTemplate();
        RatesForAllCountriesDto vatRatesDto = restTemplate.getForObject(
                "http://jsonvat.com/", RatesForAllCountriesDto.class );
        saveVat( vatRatesDto, countryCode );
        return vatRatesDto;
    }

    private boolean saveVat(RatesForAllCountriesDto vatRatesDto, String countryCode) {
        for (int i = 0; i < vatRatesDto.getRates().size(); i++) {
            VatEntity vatEntity = VatDtoToVatEntity.convert( vatRatesDto, i );
            if (vatRatesDto.getRates().get( i ).getCountryCode().equals( countryCode ))
                vatRepositories.save( vatEntity );
        }
        return false;
    }

    public List<VatEntity> getVatEntityLog() {
        return vatRepositories.findAll();
    }


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    public void toDelete(int id) {
        vatRepositories.deleteById( id );
    }
}