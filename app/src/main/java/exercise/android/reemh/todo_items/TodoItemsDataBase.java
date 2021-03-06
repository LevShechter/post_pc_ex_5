package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.List;


// TODO: feel free to add/change/remove methods as you want
public interface TodoItemsDataBase extends Serializable {

  /** Get a copy of the current items list */
  List<TodoItem> getCurrentItems();

  /**
   * Creates a new TodoItem and adds it to the list, with the @param description and status=IN-PROGRESS
   * Subsequent calls to [getCurrentItems()] should have this new TodoItem in the list
   */
  void addNewInProgressItem(String description);

  /** mark the @param item as DONE */
  void markItemDone(TodoItem item);

  /** mark the @param item as IN-PROGRESS */
  void markItemInProgress(TodoItem item);

  /** delete the @param item */
  void deleteItem(TodoItem item);

  /**change the state of an item */
  void change_item_state(TodoItem item);

  /**change few items state */
  void change_many_items_states(List<TodoItem> item_list);
}
