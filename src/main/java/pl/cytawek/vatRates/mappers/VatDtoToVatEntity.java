package pl.cytawek.vatRates.mappers;

import pl.cytawek.vatRates.dtos.RatesForAllCountriesDto;
import pl.cytawek.vatRates.entieties.VatEntity;

public class VatDtoToVatEntity {
    public static VatEntity convert(RatesForAllCountriesDto vatDto, int i) {

        VatEntity vatEntity = new VatEntity();

        vatEntity.setCountryName( vatDto.getRates().get( i ).getName() );
        vatEntity.setCountryCode( vatDto.getRates().get( i ).getCode() );
        vatEntity.setStandard( vatDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getStandard() );
        if (vatDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getReduced() != null) {
            vatEntity.setReduced( vatDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getReduced() );
        } else vatEntity.setReduced( 0.0 );
        if (vatDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getReduced1() != null) {
            vatEntity.setReduced1( vatDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getReduced1() );
        } else vatEntity.setReduced1( 0.0 );
        if ( vatDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getReduced2() != null ) {
            vatEntity.setReduced2( vatDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getReduced2() );
        } else vatEntity.setReduced2( 0.0 );
        if (vatDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getParking() != null) {
            vatEntity.setParking( vatDto.getRates().get( i ).getPeriods().get( 0 ).getRates().getParking() );
        } else vatEntity.setParking( 0.0 );
        if (vatDto.getRates().get( 0 ).getPeriods().get( 0 ).getRates().getSuperReduced() != null) {
            vatEntity.setSuperReduced( vatDto.getRates().get( 0 ).getPeriods().get( 0 ).getRates().getSuperReduced() );
        } else vatEntity.setSuperReduced( 0.0 );


        return vatEntity;


    }
}
