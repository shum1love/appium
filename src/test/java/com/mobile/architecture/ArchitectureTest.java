package com.mobile.architecture;

import com.mobile.base.BaseTest;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

/**
 * Базовые архитектурные правила фреймворка.
 */
@SuppressWarnings("unused")
@AnalyzeClasses(
        packages = "com.mobile",
        importOptions = {ImportOption.DoNotIncludeJars.class}
)
class ArchitectureTest {

    /**
     * Проверяет отсутствие циклических зависимостей между топ-уровневыми пакетами.
     * Циклы — это признак плохой архитектуры, их нужно избегать.
     */
    @ArchTest
    static ArchRule topLevelPackagesShouldBeFreeOfCycles() {
        return slices()
                .matching("com.mobile.(*)..")
                .should()
                .beFreeOfCycles();
    }

    /**
     * Классы-локаторы должны следовать утилитному стилю:
     * - название заканчивается на "Locators"
     * - класс final
     * - только приватный конструктор (нельзя создавать экземпляры)
     */
    @ArchTest
    static ArchRule locatorsShouldFollowUtilityStyle() {
        return classes()
                .that()
                .resideInAPackage("..locators..")
                .should()
                .haveSimpleNameEndingWith("Locators")
                .andShould()
                .haveModifier(JavaModifier.FINAL)
                .andShould()
                .haveOnlyPrivateConstructors();
    }

    /**
     * DTO-классы должны следовать утилитному стилю:
     * - название заканчивается на "Data"
     * - класс final
     * - только приватный конструктор
     */
    @ArchTest
    static ArchRule dtoShouldFollowUtilityStyle() {
        return classes()
                .that()
                .resideInAPackage("..dto..")
                .should()
                .haveSimpleNameEndingWith("Data")
                .andShould()
                .haveModifier(JavaModifier.FINAL)
                .andShould()
                .haveOnlyPrivateConstructors();
    }

    /**
     * Локаторы НЕ должны зависеть от других слоёв фреймворка
     * (экраны, хелперы, утилиты, тесты, base и т.д.).
     * Это правило защищает чистоту Page Object / Locator слоя.
     */
    @ArchTest
    static ArchRule locatorsShouldNotDependOnFrameworkLayers() {
        return noClasses()
                .that()
                .resideInAPackage("..locators..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(
                        "..screens..",
                        "..helper..",
                        "..utils..",
                        "..tests..",
                        "..base..",
                        "..architecture.."
                );
    }

    /**
     * Тесты и BaseTest НЕ должны напрямую обращаться к классам локаторов.
     * Доступ должен идти только через Page Object.
     */
    @ArchTest
    static ArchRule testsShouldNotAccessLocatorsDirectly() {
        return noClasses()
                .that()
                .resideInAnyPackage("..tests..", "..base..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..locators..");
    }

    /**
     * Все smoke-тесты должны:
     * - находиться в пакете tests
     * - иметь название, заканчивающееся на "Test"
     * - наследоваться от BaseTest
     * (не inner-классы)
     */
    @ArchTest
    static ArchRule smokeTestsShouldBeNamedAndInheritFromBaseTest() {
        return classes()
                .that()
                .resideInAPackage("..tests..")
                .and()
                .areNotInnerClasses()
                .should()
                .haveSimpleNameEndingWith("Test")
                .andShould()
                .beAssignableTo(BaseTest.class);
    }
}
