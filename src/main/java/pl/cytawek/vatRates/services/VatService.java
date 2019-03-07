package pl.cytawek.vatRates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.cytawek.vatRates.dtos.RatesForAllCountriesDto;
import pl.cytawek.vatRates.entieties.VatEntity;
import pl.cytawek.vatRates.exception.AlreadyExist;
import pl.cytawek.vatRates.mappers.VatDtoToVatEntity;
import pl.cytawek.vatRates.repositories.VatRepositories;

import java.util.Optional;

@Service
public class VatService {

    final VatRepositories vatRepositories;

    @Autowired
    public VatService(VatRepositories vatRepositories) {
        this.vatRepositories = vatRepositories;
    }


    public boolean addRatesByCountryCodeToDB(RatesForAllCountriesDto ratesForAllCountriesDto, String countryCode) throws AlreadyExist {
        Optional<VatEntity> vatEntity = vatRepositories.findByCountryCode(countryCode);
        if(vatEntity.isPresent()){
            throw new AlreadyExist("Record for this country already exist in DB");
        }
        else {
            vatRepositories.save(VatEntity.builder()
                            .countryCode(ratesForAllCountriesDto.getRates().get(0).getCountryCode())
                            .countryName(ratesForAllCountriesDto.getRates().get(0).getName()).build()
/*
            .parking(vatDto.getRate().get(0).getPeriodDtos().get(0).getRatesDto().getParking())
            .reduced(vatDto.getRate().get(0).getPeriodDtos().get(0).getRatesDto().getReduced())
*/
            );
            return true;
        }

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
