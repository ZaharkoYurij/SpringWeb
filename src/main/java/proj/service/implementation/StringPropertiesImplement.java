package proj.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import proj.entity.StringProperties;
import proj.repository.StringPropertiesRepository;
import proj.service.StringPropertiesService;

import java.util.List;

/**
 * Created by SCIP on 12.08.2016.
 */
@Service
public class StringPropertiesImplement implements StringPropertiesService {
    @Autowired
    StringPropertiesRepository stringPropertiesRepository;

    @Override
    public void save(String name) {
        stringPropertiesRepository.save(new StringProperties(name));
    }

    @Override
    public StringProperties findByPropertyName(String name) {
        return stringPropertiesRepository.findByPropertyName(name);
    }

    @Override
    public void delete(String name) {
        stringPropertiesRepository.deleteByName(name);
    }

    @Override
    public List<StringProperties> findAll() {
        return stringPropertiesRepository.findAll();
    }

    @Override
    public StringProperties findById(int id) {
        return stringPropertiesRepository.findById(id);
    }

    @Override
    public void save(StringProperties stringProperties) {
        stringPropertiesRepository.save(stringProperties);
    }

    @Override
    public void deleteById(int id) {
        stringPropertiesRepository.delete(id);
    }

    @Override
    public List<StringProperties> findByCategoryId(int id) {
        return stringPropertiesRepository.findByCategoryId(id);
    }


}
