package main;

import components.CatalogCoursesComponent;
import components.popups.AuthPopup;
import extensions.UiExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import pages.CategoriesPage;
import pages.MainPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(UiExtension.class)
public class HomeworkPageTest {

    @Inject
    protected CatalogCoursesComponent catalogCoursesComponent;

    @Inject
    protected AuthPopup authPopup;

    @Inject
    protected CategoriesPage categoriesPage;

    @Inject
    protected MainPage mainPage;

    @Test
    @DisplayName("Выбор случайной категории курсов")
    public void testRandomLearningCategorySelection() {

        String title =

                mainPage.open()
                        .getLearningCategory(1);

        mainPage.goToLearningSection(title);

        categoriesPage.pageHeaderShowIdBySameUs(title);

        catalogCoursesComponent.getComponentEntry()
                .click();

        authPopup.popupShouldNotBeVisible()
                .popupShouldBeVisible()
                .closePopup();

        WebElement firstCategoryElement =

                mainPage.firstCategoryElement(1);
        mainPage.waitUntilElementIsVisible(firstCategoryElement);
        assertThat(
                mainPage.checkElementContainsText(firstCategoryElement, "Программирование"));

    }

    @Test
    @DisplayName("Проверка выбора курса по имени")
    public void testSelectCourseByName() {

        categoriesPage.open("courses", "categories=programming");

        String courseName =
                CatalogCoursesComponent.COURSE_NAME;

        categoriesPage.pageHeaderShowIdBySameUs(courseName);

        categoriesPage.clickSelectedCourseByName(courseName)
                .findCourseByName(courseName);

        catalogCoursesComponent
                .getComponentEntry()
                .click();

        authPopup.popupShouldNotBeVisible()
                .popupShouldBeVisible()
                .closePopup();

        WebElement courseDescriptionElement =
                mainPage.findElementByCSS(".sc-s2pydo-1");
        mainPage.waitUntilElementIsVisible(courseDescriptionElement);
        assertThat(courseDescriptionElement.getText()).contains(courseName);

    }

    @Test
    @DisplayName("Проверка курсов с самой ранней и поздней датой старта")
    public void testFindEarliestAndLatestCourses() throws Exception {

        categoriesPage.open("courses", "categories=programming");

        catalogCoursesComponent.verifyEarliestAndLatestCourses();

        catalogCoursesComponent
                .getComponentEntry()
                .click();

        authPopup.popupShouldNotBeVisible()
                .popupShouldBeVisible()
                .closePopup();
    }
}
