package keldkemp.telegram.services;

public interface BeanFactoryService {

    <T> T getBean(String name, Class<T> tClass);

    <T> T getBean(String name);
}
