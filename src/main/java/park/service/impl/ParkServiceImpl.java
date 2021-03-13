package park.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import park.domain.Park;
import park.exception.ParkNotFoundException;
import park.repository.ParkRepository;
import park.service.ParkService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkServiceImpl implements ParkService {
    @Autowired
    private ParkRepository parkRepository;

    @Override
    public List<Park> all() {
       return parkRepository.findAll();
    }

    @Override
    public Park one(final Long code) {
        return parkRepository.findById(code)
                .orElseThrow(() -> new ParkNotFoundException(code));
    }

    @Override
    public Park addPark(final Park newPark) {
        return parkRepository.save(newPark);
    }

    @Override
    public Park replacePark(final Park newPark,final Long parkCode) {
         return parkRepository.findById(parkCode)
                .map(park -> {
                    park.setName(park.getName());
                    return parkRepository.save(park);
                })
                .orElseGet(() -> {
                    newPark.setCode(parkCode);
                    return parkRepository.save(newPark);
                });
    }
}
