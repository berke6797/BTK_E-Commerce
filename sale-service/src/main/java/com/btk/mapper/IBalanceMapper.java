package com.btk.mapper;

import com.btk.dto.response.AddBalanceResponseDto;
import com.btk.entity.Balance;
import com.btk.service.BalanceService;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBalanceMapper {
    IBalanceMapper INSTANCE = Mappers.getMapper(IBalanceMapper.class);
    AddBalanceResponseDto balanceToAddBalanceResponseDto(Balance balance);
}
