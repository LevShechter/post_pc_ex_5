package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.Date;

public class TodoItem implements Serializable {
  // TODO: edit this class as you want
    public enum enum_status {DONE, IN_PROGRESS};
    private enum_status curStatus;
    private String description;
    public static int itemCreationTime = 1;
    public Date initTaskDate;
    private Date lastChangedDate;
//
//    public TodoItem()
//    {
//      this.initTaskDate = new Date();
//      itemCreationTime += 1;
//      this.lastChangedDate = this.initTaskDate;
//    }

    public TodoItem(String description, enum_status curStatus)
    { this.description = description;
    this.curStatus = curStatus;
    this.initTaskDate = new Date();
    itemCreationTime += 1;
    this.lastChangedDate = this.initTaskDate;}

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
    return itemCreationTime;
  }

  public void change_item_date(){}
  public Date getLastChangedDate() {
    return lastChangedDate;
  }

  public void setLastChangedDate(Date lastChangedDate) {
    this.lastChangedDate = lastChangedDate;
  }{ }

}
