package com.paw.pawganizr.config;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.paw.pawganizr.contact.Contact;
import com.paw.pawganizr.contact.ContactController;
import com.paw.pawganizr.medicine.Medicine;
import com.paw.pawganizr.medicine.MedicineController;
import com.paw.pawganizr.nutrition.Nutrition;
import com.paw.pawganizr.nutrition.NutritionController;
import com.paw.pawganizr.pedigree.Pedigree;
import com.paw.pawganizr.pedigree.PedigreeController;
import com.paw.pawganizr.pet.Pet;
import com.paw.pawganizr.pet.PetController;
import com.paw.pawganizr.treatment.Treatment;
import com.paw.pawganizr.treatment.TreatmentController;
import com.paw.pawganizr.user.AppUser;
import com.paw.pawganizr.user.AppUserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = {AppUserController.class, TreatmentController.class, MedicineController.class, PetController.class,
        NutritionController.class, ContactController.class, PedigreeController.class})
public class SwaggerConfig {

    public static final String DEFAULT_INCLUDE_PATTERN = "/users/.*";


    @Bean
    public Docket SwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(getSwaggerPaths())
                .build()
                .ignoredParameterTypes(Contact.class, Nutrition.class, Pet.class, AppUser.class, Medicine.class, Treatment.class, Pedigree.class)
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Lists.newArrayList(securityContext()));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        var authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        var authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private Predicate<String> getSwaggerPaths() {
        return or(
                regex("/auth.*"),
                regex(DEFAULT_INCLUDE_PATTERN));
    }
}
