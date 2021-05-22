package exercise.android.reemh.todo_items;

import junit.framework.TestCase;

import org.junit.Test;

public class TodoItemsDataBaseImplTest extends TestCase {
  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    assertEquals(0, dbUnderTest.getCurrentItems().size());

    // test
    dbUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, dbUnderTest.getCurrentItems().size());
  }


  @Test
  public void test1(){
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    for (int i = 1;i<=10;i++){
      String description = "item number"+i;
      dbUnderTest.addNewInProgressItem(description);
    }
    assertEquals(10, dbUnderTest.getCurrentItems().size());
  }

  @Test
  public void test2(){
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    assertEquals(0, dbUnderTest.getCurrentItems().size());

    // test
    dbUnderTest.addNewInProgressItem("to cook");

    // verify
    assertEquals(1, dbUnderTest.getCurrentItems().size());
  }


  @Test
  public void test3(){
    // setup
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    for (int i = 1;i<=10;i++){
      String description = "item number"+i;
      dbUnderTest.addNewInProgressItem(description);
    }
    assertEquals(TodoItem.enum_status.IN_PROGRESS,     dbUnderTest.getCurrentItems().get(0).get_cur_status()
    );
  }

  @Test
  public void test4(){
    // setup
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    for (int i = 1;i<=10;i++){
      String description = "item number"+i;
      dbUnderTest.addNewInProgressItem(description);
    }

    dbUnderTest.deleteItem(dbUnderTest.getCurrentItems().get(0));
    assertEquals(9, dbUnderTest.getCurrentItems().size());

  }


  @Test
  public void test5(){
    // setup
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    for (int i = 1;i<=10;i++){
      String description = "item number"+i;
      dbUnderTest.addNewInProgressItem(description);
    }

    dbUnderTest.getCurrentItems().get(0).change_status(TodoItem.enum_status.DONE);
    assertEquals(TodoItem.enum_status.DONE,     dbUnderTest.getCurrentItems().get(0).get_cur_status());

  }

  @Test
  public void test6(){
    // setup
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    for (int i = 1;i<=3;i++){
      String description = "item number"+i;
      dbUnderTest.addNewInProgressItem(description);
    }
    assertEquals("item number1",     dbUnderTest.getCurrentItems().get(0).get_description());
  }

  @Test
  public void test7(){
    // setup
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    for (int i = 1;i<=3;i++){
      String description = "item number"+i;
      dbUnderTest.addNewInProgressItem(description);
    }
    dbUnderTest.getCurrentItems().get(0).change_status(TodoItem.enum_status.DONE);

    assertEquals("item number2",     dbUnderTest.getCurrentItems().get(1).get_description());
  }


  @Test
  public void test8(){
    // setup
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    for (int i = 1;i<=3;i++){
      String description = "item number"+i;
      dbUnderTest.addNewInProgressItem(description);
    }
    dbUnderTest.getCurrentItems().remove(0);
    dbUnderTest.getCurrentItems().remove(0);
    dbUnderTest.getCurrentItems().remove(0);


    assertEquals(0, dbUnderTest.getCurrentItems().size());
  }



  @Test
  public void test9(){
    // setup
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    for (int i = 1;i<=3;i++){
      String description = "item number"+i;
      dbUnderTest.addNewInProgressItem(description);
    }
    dbUnderTest.getCurrentItems().remove(0);
    dbUnderTest.getCurrentItems().remove(0);
    dbUnderTest.getCurrentItems().remove(0);
    dbUnderTest.addNewInProgressItem("study");
    dbUnderTest.addNewInProgressItem("eating");
    dbUnderTest.addNewInProgressItem("working out");


    assertEquals(3, dbUnderTest.getCurrentItems().size());
  }

  @Test
  public void test10(){
    // setup
    // setup
    TodoItemsDataBaseImpl dbUnderTest = new TodoItemsDataBaseImpl();
    for (int i = 1;i<=3;i++){
      String description = "item number"+i;
      dbUnderTest.addNewInProgressItem(description);
    }
    dbUnderTest.getCurrentItems().remove(0);
    dbUnderTest.getCurrentItems().remove(0);
    dbUnderTest.getCurrentItems().remove(0);
    dbUnderTest.addNewInProgressItem("study");
    dbUnderTest.addNewInProgressItem("eating");
    dbUnderTest.addNewInProgressItem("working out");

    dbUnderTest.getCurrentItems().remove(0);


    assertEquals("eating", dbUnderTest.getCurrentItems().get(0).get_description());
  }
  // TODO: add at least 10 more tests to verify correct behavior of your implementation of `TodoItemsDataBaseImpl` class
}