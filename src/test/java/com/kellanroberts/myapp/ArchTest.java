package com.kellanroberts.myapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.kellanroberts.myapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.kellanroberts.myapp.service..")
            .or()
            .resideInAnyPackage("com.kellanroberts.myapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.kellanroberts.myapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
