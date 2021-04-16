package com.magalutest.schedulingcore.business;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.model.Status;
import com.magalutest.schedulingcore.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusBusinessImpl implements StatusBusiness{

    private final StatusRepository statusRepository;

    @Autowired
    public StatusBusinessImpl(final StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status create(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status findById(long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(long id) {
        statusRepository.deleteById(id);
    }
}
