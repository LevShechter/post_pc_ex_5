package exercise.android.reemh.todo_items;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class ToDoListApplication extends Application {
    SharedPreferences sp;
    Context context;
    public List<TodoItem> todoList;

    public ToDoListApplication(Context context)
    {
        this.context = context;
        this.sp = getDefaultSharedPreferences(context);
        get_changes_todo_list();
    }

    public void get_changes_todo_list()
    {
        todoList = new ArrayList<>();
        String list_items_sp = sp.getString("items_list", "");
        if (!list_items_sp.equals(""))
        {
            Type typeOfList = new TypeToken<ArrayList<TodoItem>>(){}.getType();
            todoList = new Gson().fromJson(list_items_sp, typeOfList);
        }

    }
    public void save_list_changes()
    {
        sp.edit().putString("items_list", new Gson().toJson(todoList)).apply();
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
