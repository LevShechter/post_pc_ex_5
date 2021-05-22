package exercise.android.reemh.todo_items;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemHolder extends RecyclerView.ViewHolder {
    public TextView description;
    public CheckBox check_box;
    public Button delete_item_button;

    public ItemHolder(@NonNull View itemView) {
        super(itemView);
        this.description = itemView.findViewById(R.id.description);
        this.check_box = itemView.findViewById(R.id.check_box);
        this.delete_item_button = itemView.findViewById(R.id.delete_item_button);
    }

}
