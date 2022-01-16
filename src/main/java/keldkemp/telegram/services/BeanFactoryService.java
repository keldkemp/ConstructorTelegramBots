package keldkemp.telegram.services;

public interface BeanFactoryService {

    String LOCK_NAME = "BEAN_FACTORY_DEFAULT_LOCK";

    <T> T getBean(String name, Class<T> tClass);

    <T> T getBean(String name);

    boolean checkBean(String name);
}
