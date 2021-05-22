package exercise.android.reemh.todo_items;

import java.util.Comparator;

public class ItemsComparator implements Comparator<TodoItem> {


    @Override
    public int compare(TodoItem todoItem1, TodoItem todoItem2) {
        switch (todoItem1.get_cur_status())
        {
            case DONE:
                if (todoItem1.get_item_total_time() < todoItem2.get_item_total_time()){return 1;}
                else {return 0;}
            case IN_PROGRESS:
                if (todoItem2.get_cur_status() == TodoItem.enum_status.DONE)
                {
                    if (todoItem1.get_item_total_time() < todoItem2.get_item_total_time()) {return 1;}
                    else {return -1;}
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + todoItem1.get_cur_status());
        }
        return 0;
    }
}
