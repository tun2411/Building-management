package com.javaweb.converter;

import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.RoleDTO;
import com.javaweb.model.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO convertToDto(TransactionEntity transaction) {
        TransactionDTO result = modelMapper.map(transaction, TransactionDTO.class);
        return result;
    }

    public TransactionEntity convertToEntity(TransactionDTO dto) {
        TransactionEntity result = modelMapper.map(dto, TransactionEntity.class);
        return result;
    }

}
