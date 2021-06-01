package exercise.android.reemh.todo_items;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditToDoListActivity extends AppCompatActivity {
    private TodoItem item;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do_list);
        Intent intent = getIntent();

        this.item = (TodoItem)intent.getSerializableExtra("ToDoItem");

        TextView view_current_task_status = findViewById(R.id.currentTaskStatus);
        TextView view_init_task_date = findViewById(R.id.initTaskDate);
        TextView view_last_changed_date = findViewById(R.id.dateLastChanged);
        EditText view_description = findViewById(R.id.description);
        String new_description = item.get_description();
        view_description.setText(new_description);
        view_init_task_date.setText("initial task date is " + item.get_item_total_time());
        Date new_date_to_edit = new Date();
        long time_dif = (new_date_to_edit.getTime() - item.getLastChangedDate().getTime());
        long minute_num =  time_dif / (60000);
        long hours_num = time_dif / 3600000;
        if (minute_num < 60)
        {
            view_last_changed_date.setText("changed " + minute_num + " minutes ago");
        }
        else if(hours_num < 24)
        {
            view_last_changed_date.setText("changed today at" + hours_num);
        }
        else
        {
            String new_date_str = new SimpleDateFormat("yyy-dd-MM", Locale.getDefault()).format(item.getLastChangedDate());
            String hour_of_day = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
            view_last_changed_date.setText("changed " + new_date_str + hour_of_day);
        }
        item.setLastChangedDate(new Date());


        switch(this.item.get_cur_status())
        {
            case DONE:
                view_current_task_status.setText("task Done");
                break;
            case IN_PROGRESS:
                view_current_task_status.setText("task In Progress");

        }


        view_description.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        view_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String changed_description  = editable.toString();
                item.change_description(changed_description);
                Date new_date_to_edit = new Date();
                long time_dif = (new_date_to_edit.getTime() - item.getLastChangedDate().getTime());
                long minute_num =  time_dif / (60000);
                long hours_num = time_dif / 3600000;
                if (minute_num < 60)
                {
                    view_last_changed_date.setText("changed " + minute_num + " minutes ago");
                }
                else if(hours_num < 24)
                {
                    view_last_changed_date.setText("changed today at" + hours_num);
                }
                else
                {
                    String new_date_str = new SimpleDateFormat("yyy-dd-MM", Locale.getDefault()).format(item.getLastChangedDate());
                    String hour_of_day = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
                    view_last_changed_date.setText("changed " + new_date_str + hour_of_day);
                }item.setLastChangedDate(new Date());
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent_back = new Intent();
        intent_back.setAction("ToDoItemEdited");
        intent_back.putExtra("ToDoItem", this.item);
        sendBroadcast(intent_back);
    }
}
