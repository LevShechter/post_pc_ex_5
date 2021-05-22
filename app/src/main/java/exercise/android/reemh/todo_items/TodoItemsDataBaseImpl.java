package exercise.android.reemh.todo_items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// TODO: implement!
public class TodoItemsDataBaseImpl implements TodoItemsDataBase {
  private List<TodoItem> TodoItemsList;
  public TodoItemsDataBaseImpl()
  {
    TodoItemsList = new ArrayList<>();
  }

  @Override
  public List<TodoItem> getCurrentItems() { return this.TodoItemsList; }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem new_item = new TodoItem(description, TodoItem.enum_status.IN_PROGRESS);
    this.TodoItemsList.add(new_item);
    ItemsComparator itemsComparator = new ItemsComparator();
    Collections.sort(this.TodoItemsList, itemsComparator);
  }

  @Override
  public void markItemDone(TodoItem item) {
    getItem(item).change_status(TodoItem.enum_status.DONE);
    ItemsComparator itemsComparator = new ItemsComparator();
    Collections.sort(this.TodoItemsList, itemsComparator);
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    getItem(item).change_status(TodoItem.enum_status.IN_PROGRESS);
    ItemsComparator itemsComparator = new ItemsComparator();
    Collections.sort(this.TodoItemsList, itemsComparator);
  }

  @Override
  public void deleteItem(TodoItem item) {
    this.TodoItemsList.remove(item);
  }

  private TodoItem getItem(TodoItem to_do_item)
  {
    for(int i = 0; i < this.TodoItemsList.size(); i++)
    {
      if (TodoItemsList.get(i).equals(to_do_item))
      {
        return TodoItemsList.get(i);
      }
    }
    return to_do_item;
  }
}
