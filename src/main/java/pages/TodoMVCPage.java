package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import static core.Concise.*;
import static core.CustomConditions.*;
import static core.CustomConditions.exactTextsOfVisible;
import static core.CustomConditions.sizeOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

/**
 * Created by alex-macbook on 7/20/17.
 */
public class TodoMVCPage {

    private static By tasks = byCss("#todo-list>li");
    private static By todoCount = byCss("#todo-count strong");

    @Step("Open App")
    public static void openApp() {
        open("http://todomvc4tasj.herokuapp.com/");
    }

    @Step("add task {0}")
    public static void add(String taskText) {
        $(byCss("#new-todo")).sendKeys(taskText + Keys.ENTER);
    }

    @Step("Edit old task - {0}, set new task - {1}")
    public static void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText+Keys.ENTER);
    }

    private static WebElement startEdit(String oldTaskText, String newTaskText) {
        doubleClick($(listElementWithExactText(tasks, oldTaskText), "label"));
        return setValue($(listElementWithCssClass(tasks, "editing"), ".edit"), newTaskText);
    }

    @Step("toogle task  - {0}")
    public static void toggle(String taskText) {
        $(listElementWithExactText(tasks, taskText), ".toggle").click();
    }

    @Step("Verified existing task - {0}")
    public static void assertTasks(String... taskTexts) {
        assertThat(exactTextOf(tasks, taskTexts));
    }

    @Step("Go to Active Filter")
    public static void filterActive() {
        $(byLinkText("Active")).click();
    }

    @Step("Verified that the no visible task")
    public static void assertNoVisibleTasks() {
        assertSizeOfVisibleTasks(0);
    }

    public static void assertSizeOfVisibleTasks(int size) {
        assertThat(sizeOfVisible(tasks, size));
    }

    @Step("Verified visability task - {0}")
    public static void assertVisibleTasks(String... taskTexts) {
        assertThat(exactTextsOfVisible(tasks, taskTexts));
    }

    @Step("Toogle all task")
    public static void toggleAll() {
        $(byCss("#toggle-all")).click();
    }

    @Step("Go to completed filter ")
    public static void filterCompleted() {
        $(byLinkText("Completed")).click();
    }

    @Step("Edit task {0}, to {1} and cancel")
    public static void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText,newTaskText+Keys.ESCAPE);
    }

    @Step("Clear completed")
    public static void clearCompleted() {
        $(byCss("#clear-completed")).click();
    }

    @Step("Go to filter completed tab")
    public static void filterAll() {
        $(byLinkText("All")).click();
    }

    @Step("Verified assert item left - {0}")
    public static void assertItemsLeft(int count) {
        assertThat(textToBe(todoCount, Integer.toString(count)));
    }

    @Step("Delete task - {0}")
    public static void delete(String taskText) {
        hover($(listElementWithExactText(tasks, taskText)));
        $(listElementWithExactText(tasks, taskText), ".destroy").click();
    }

    @Step("Assert no task")
    public static void assertNoTasks() {
        assertSizeOfTasks(0);
    }

    public static void assertSizeOfTasks(int size) {
        assertThat(sizeOf(tasks, size));
    }

}
