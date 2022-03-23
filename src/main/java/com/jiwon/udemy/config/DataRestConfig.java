package com.jiwon.udemy.config;

import com.jiwon.udemy.model.CourseCategory;
import com.jiwon.udemy.model.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {
    @Value("${url.front}")
    private String allowedOrigin;
    private final EntityManager entityManager;

    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // disable HTTP methods: PUT, POST and DELETE for course-category and course-level
        HttpMethod[] unsupportedMethods = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE };
        disableHttpMethods(config, CourseCategory.class, unsupportedMethods);
        disableHttpMethods(config, Level.class, unsupportedMethods);

        // expose IDs for all entities
        exposeId(config);

        // configure CORS
        cors.addMapping("/**")
            .allowedOrigins(allowedOrigin)
            .allowedMethods("GET", "PUT", "POST", "DELETE")
            .maxAge(3600);
    }

    private void exposeId(RepositoryRestConfiguration config) {
        // get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class<?>> entityClasses = entities
            .stream()
            .map(Type::getJavaType)
            .collect(Collectors.toList());

        // expose the entity ids for the array of entity/domain types
        config.exposeIdsFor(entityClasses.toArray(Class<?>[]::new));
    }

    private <T> void disableHttpMethods(
        RepositoryRestConfiguration config, Class<T> clazz,
        HttpMethod[] unsupportedMethods
    ) {
        config.getExposureConfiguration()
            .forDomainType(clazz)
            .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedMethods))
            .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedMethods));
    }
}
