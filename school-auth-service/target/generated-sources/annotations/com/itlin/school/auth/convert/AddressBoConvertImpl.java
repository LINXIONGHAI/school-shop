package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.AddressBo;
import com.itlin.school.auth.entity.AddressDo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T20:53:30+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class AddressBoConvertImpl implements AddressBoConvert {

    @Override
    public List<AddressBo> AddressToBoConvertList(List<AddressDo> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressBo> list1 = new ArrayList<AddressBo>( list.size() );
        for ( AddressDo addressDo : list ) {
            list1.add( addressDoToAddressBo( addressDo ) );
        }

        return list1;
    }

    protected AddressBo addressDoToAddressBo(AddressDo addressDo) {
        if ( addressDo == null ) {
            return null;
        }

        AddressBo addressBo = new AddressBo();

        addressBo.setId( addressDo.getId() );
        addressBo.setUserId( addressDo.getUserId() );
        addressBo.setDefaultStatus( addressDo.getDefaultStatus() );
        addressBo.setReceiveName( addressDo.getReceiveName() );
        addressBo.setPhone( addressDo.getPhone() );
        addressBo.setProvince( addressDo.getProvince() );
        addressBo.setCity( addressDo.getCity() );
        addressBo.setRegion( addressDo.getRegion() );
        addressBo.setDetailAddress( addressDo.getDetailAddress() );
        addressBo.setCreateTime( addressDo.getCreateTime() );

        return addressBo;
    }
}
