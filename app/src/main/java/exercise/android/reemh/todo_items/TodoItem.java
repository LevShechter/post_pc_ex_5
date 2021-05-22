package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
  // TODO: edit this class as you want
    public enum enum_status {DONE, IN_PROGRESS};
    private enum_status curStatus;
    private String description;
    public static int itemCreationTime = 1;
    private int totalItemTime;

    public TodoItem()
    {
      this.totalItemTime = itemCreationTime;
      itemCreationTime += 1;
    }

    public TodoItem(String description, enum_status curStatus)
    { this.description = description;
    this.curStatus = curStatus;
    this.totalItemTime = itemCreationTime;
    itemCreationTime += 1; }

    public void change_status(enum_status newStatus)
    {
      this.curStatus = newStatus;
    }
    public void change_description(String newDescription)
    {
      this.description = newDescription;
    }

    public enum_status get_cur_status()
    {
      return this.curStatus;
    }

    public String get_description()
    {
      return this.description;
    }

  public int get_item_total_time()
  {
    return this.totalItemTime;
  }

}
