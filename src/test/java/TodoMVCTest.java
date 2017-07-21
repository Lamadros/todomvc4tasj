import org.junit.Before;
import org.junit.Test;
import testconfig.BaseTest;

import static pages.TodoMVCPage.*;

/**
 * Created by alex-macbook on 7/20/17.
 */

public class TodoMVCTest extends BaseTest {

    @Before
    public void openToDoMVC() {
        openApp();
    }

    @Test
    public void testTaskLifeCycle() {

        add("1");
        edit("1", "1 edited");
        toggle("1 edited");
        assertTasks("1 edited");

        filterActive();
        assertNoVisibleTasks();
        add("2");
        assertVisibleTasks("2");
        toggleAll();
        assertNoVisibleTasks();

        filterCompleted();
        assertVisibleTasks("1 edited", "2");
        cancelEdit("2", "777");
        assertVisibleTasks("1 edited", "2");
        //reopen
        toggle("2");
        assertVisibleTasks("1 edited");
        clearCompleted();
        assertNoVisibleTasks();

        filterAll();
        assertTasks("2");
        assertItemsLeft(1);
        delete("2");
        assertNoTasks();
    }

    @Test
    public void testEditAtAll() {
        //precondition-added tasks
        add("1");
        add("2");

        edit("1", "1 edited");

        assertTasks("1 edited", "2");
        assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtActive() {
        //precondition-added tasks
        add("1");
        add("2");
        filterActive();

        delete("1");

        assertTasks("2");
        assertItemsLeft(1);
    }

    @Test
    public void testCancelEditAtCompleted() {
        //precondition-completed tasks
        add("1");
        add("2");

        toggleAll();
        filterCompleted();

        cancelEdit("2", "to be canceled");

        assertTasks("1", "2");
        assertItemsLeft(0);
    }
}
