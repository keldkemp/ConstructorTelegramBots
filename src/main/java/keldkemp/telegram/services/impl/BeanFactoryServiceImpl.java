package keldkemp.telegram.services.impl;

import keldkemp.telegram.services.BeanFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public abstract class BeanFactoryServiceImpl implements BeanFactoryService {

    @Autowired
    protected ConfigurableApplicationContext applicationContext;

    @Override
    public <T> T getBean(String name, Class<T> tClass) {
        return applicationContext.getBean(name, tClass);
    }

    @Override
    public Object getBean(String name) {
        return getBean(name, Object.class);
    }

    protected abstract void createBean(String name);
}
