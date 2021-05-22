package exercise.android.reemh.todo_items;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    public LayoutInflater layoutInflater;
    public Context context;
    public TodoItemsDataBase todoItemsDataBase;

    public ItemAdapter(Context context, TodoItemsDataBase todoItemsDataBase)
    {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.todoItemsDataBase = todoItemsDataBase;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.row_todo_item, parent, false);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        TodoItem todoItem = this.todoItemsDataBase.getCurrentItems().get(position);
        String item_description = todoItem.get_description();
        holder.description.setText(item_description);
        boolean is_status_done = todoItem.get_cur_status() == (TodoItem.enum_status.DONE);
        holder.check_box.setChecked(is_status_done);

        if(is_status_done)
        {
            holder.description.setPaintFlags(holder.description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else
        {
            holder.description.setPaintFlags(0);
        }

        holder.check_box.setOnClickListener(view ->
        {
            if(is_status_done)
            {
                this.todoItemsDataBase.markItemInProgress(todoItem);
            }
            else
            {
                this.todoItemsDataBase.markItemDone(todoItem);
                notifyDataSetChanged();

            }
        });

        holder.delete_item_button.setOnClickListener(view ->
        {
            this.todoItemsDataBase.deleteItem(todoItem);
            notifyDataSetChanged();

        });


    }

    @Override
    public int getItemCount() {
        int size = this.todoItemsDataBase.getCurrentItems().size();
        return size;
    }
}
