package pl.cytawek.vatRates.mappers;

import pl.cytawek.vatRates.dtos.RatesForAllCountriesDto;
import pl.cytawek.vatRates.entieties.VatEntity;

public class VatDtoToVatEntity {
    public static VatEntity convert(RatesForAllCountriesDto ratesForAllCountriesDto) {




        for (int i = 0; i < ratesForAllCountriesDto.getRates().size(); i++) {
            VatEntity vatEntity = new VatEntity();
            vatEntity.setCountryName( ratesForAllCountriesDto.getRates().get( i ).getName() );
            vatEntity.setCountryCode( ratesForAllCountriesDto.getRates().get( i ).getCode() );
            if (  ratesForAllCountriesDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getParking() != null) {
                vatEntity.setParking( ratesForAllCountriesDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getParking() );
            }
//            vatEntity.setReduced1( ratesForAllCountriesDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getReduced1() );
//            vatEntity.setReduced2( ratesForAllCountriesDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getReduced2() );
//            vatEntity.setSuperReduced( ratesForAllCountriesDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getSuperReduced() );
//            vatEntity.setStandard( ratesForAllCountriesDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getStandard() );
//            vatEntity.setReduced( ratesForAllCountriesDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getReduced() );

            return vatEntity;

        }


        return null;
    }

}
