package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.AddressBo;
import com.itlin.school.auth.dto.AddressReqDto;
import com.itlin.school.auth.dto.AddressResDto;
import com.itlin.school.auth.entity.AddressDo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-29T17:07:16+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class AddressDtoConvertImpl implements AddressDtoConvert {

    @Override
    public AddressBo AddressToBoConvert(AddressReqDto addressReqDto) {
        if ( addressReqDto == null ) {
            return null;
        }

        AddressBo addressBo = new AddressBo();

        addressBo.setId( addressReqDto.getId() );
        addressBo.setUserId( addressReqDto.getUserId() );
        addressBo.setDefaultStatus( addressReqDto.getDefaultStatus() );
        addressBo.setReceiveName( addressReqDto.getReceiveName() );
        addressBo.setPhone( addressReqDto.getPhone() );
        addressBo.setProvince( addressReqDto.getProvince() );
        addressBo.setCity( addressReqDto.getCity() );
        addressBo.setRegion( addressReqDto.getRegion() );
        addressBo.setDetailAddress( addressReqDto.getDetailAddress() );
        addressBo.setCreateTime( addressReqDto.getCreateTime() );

        return addressBo;
    }

    @Override
    public AddressDo AddressToBoConvert(AddressBo addressBo) {
        if ( addressBo == null ) {
            return null;
        }

        AddressDo addressDo = new AddressDo();

        addressDo.setId( addressBo.getId() );
        addressDo.setUserId( addressBo.getUserId() );
        addressDo.setDefaultStatus( addressBo.getDefaultStatus() );
        addressDo.setReceiveName( addressBo.getReceiveName() );
        addressDo.setPhone( addressBo.getPhone() );
        addressDo.setProvince( addressBo.getProvince() );
        addressDo.setCity( addressBo.getCity() );
        addressDo.setRegion( addressBo.getRegion() );
        addressDo.setDetailAddress( addressBo.getDetailAddress() );
        addressDo.setCreateTime( addressBo.getCreateTime() );

        return addressDo;
    }

    @Override
    public List<AddressResDto> AddressToResConvertList(List<AddressBo> addressBos) {
        if ( addressBos == null ) {
            return null;
        }

        List<AddressResDto> list = new ArrayList<AddressResDto>( addressBos.size() );
        for ( AddressBo addressBo : addressBos ) {
            list.add( addressBoToAddressResDto( addressBo ) );
        }

        return list;
    }

    protected AddressResDto addressBoToAddressResDto(AddressBo addressBo) {
        if ( addressBo == null ) {
            return null;
        }

        AddressResDto addressResDto = new AddressResDto();

        addressResDto.setId( addressBo.getId() );
        addressResDto.setUserId( addressBo.getUserId() );
        addressResDto.setDefaultStatus( addressBo.getDefaultStatus() );
        addressResDto.setReceiveName( addressBo.getReceiveName() );
        addressResDto.setPhone( addressBo.getPhone() );
        addressResDto.setProvince( addressBo.getProvince() );
        addressResDto.setCity( addressBo.getCity() );
        addressResDto.setRegion( addressBo.getRegion() );
        addressResDto.setDetailAddress( addressBo.getDetailAddress() );
        addressResDto.setCreateTime( addressBo.getCreateTime() );

        return addressResDto;
    }
}
