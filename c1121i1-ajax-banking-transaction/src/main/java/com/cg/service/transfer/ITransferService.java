package com.cg.service.transfer;

import com.cg.model.Customer;
import com.cg.model.Transfer;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ITransferService extends IGeneralService<Transfer> {

    void save(TransferDTO transferDTO);

    void doTransfer(Optional<CustomerDTO> sender, Optional<CustomerDTO> recipient, TransferDTO transfer);

//    void doTransfer(CustomerDTO sender, CustomerDTO recipient, TransferDTO transfer);
}
