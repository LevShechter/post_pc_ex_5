package exercise.android.reemh.todo_items;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  public TodoItemsDataBase dataBase;
  RecyclerView recyclerView;
  ItemAdapter itemAdapter;
  public ToDoListApplication toDoListApplication;
  public BroadcastReceiver broadcastReceiver;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toDoListApplication = new ToDoListApplication(this);

    if (dataBase == null) {
      dataBase = new TodoItemsDataBaseImpl();
    }
    itemAdapter = new ItemAdapter(this, this.dataBase);
    toDoListApplication.save_list_changes();
    List<TodoItem> appList = toDoListApplication.todoList;
    dataBase.change_many_items_states(appList);

    TextView editTextView = findViewById(R.id.editTextInsertTask);
    recyclerView = findViewById(R.id.recyclerTodoItemsList);
    FloatingActionButton createTaskButton = findViewById(R.id.buttonCreateTodoItem);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    recyclerView.setAdapter(itemAdapter);
    editTextView.setText("");
    if (savedInstanceState != null)
    {
      String edited_string = savedInstanceState.getString("ToDoItemEdited");
      editTextView.setText(edited_string);
    }

    createTaskButton.setOnClickListener(view ->
    {
      boolean is_pin = editTextView.getText().toString().length() > 0;
      if(is_pin)
      {
        String description = editTextView.getText().toString();
        this.dataBase.addNewInProgressItem(description);
        editTextView.setText("");
        itemAdapter.notifyDataSetChanged();
      }
    });

    broadcastReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        boolean is_changed = false;
        is_changed = intent.getAction().equals("ToDoItemEdited");
        if ( is_changed)
        {
          TodoItem editedItem = (TodoItem)intent.getSerializableExtra("ToDoItem");
          dataBase.change_item_state(editedItem);
          toDoListApplication.todoList = dataBase.getCurrentItems();
          toDoListApplication.save_list_changes();
          itemAdapter.notifyDataSetChanged();
        }
      }
    };
    IntentFilter intentFilter = new IntentFilter("ToDoItemEdited");
    registerReceiver(broadcastReceiver, intentFilter);

    // TODO: implement the specs as defined below
    //    (find all UI components, hook them up, connect everything you need)
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("ToDoItemEdited", this.dataBase );
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Serializable changed_state = savedInstanceState.getSerializable("ToDoItemEdited");
    loadState(changed_state);
    itemAdapter.notifyDataSetChanged();
  }

  @Override
  protected void onResume() {
    super.onResume();
    toDoListApplication.get_changes_todo_list();
    List<TodoItem> appList = toDoListApplication.todoList;
    dataBase.change_many_items_states(appList);
  }
  @Override
  protected void onDestroy() {
    super.onDestroy();
    this.unregisterReceiver(broadcastReceiver);
  }

  @Override
  protected void onStop() {
    super.onStop();
    toDoListApplication.todoList = this.dataBase.getCurrentItems();
    toDoListApplication.save_list_changes();
  }
  public Serializable saveState() {
    app_state state_database = new app_state();
    state_database.set_state(this.dataBase, this.recyclerView, itemAdapter, this.dataBase.getCurrentItems());
    return state_database;
  }

  public void loadState(Serializable prevState) {
    if (!(prevState instanceof app_state)) {
      return; // ignore
    }
    app_state casted = (app_state) prevState;
    dataBase = casted.get_dataBase();
    recyclerView = casted.get_recyclerView();
    itemAdapter = casted.get_itemAdapter();
    dataBase.change_many_items_states(casted.get_todoItemList());
  }

  private static class app_state implements Serializable {
    TodoItemsDataBaseImpl dataBase;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<TodoItem> todoItemList = new ArrayList<>();


    public void set_state(TodoItemsDataBase dataBase, RecyclerView recyclerView_other, ItemAdapter itemAdapter_other, List<TodoItem> todoItemList_other)
    {
      this.dataBase = new TodoItemsDataBaseImpl();
      this.recyclerView = recyclerView_other;
      this.recyclerView.setAdapter(itemAdapter);
      this.dataBase.change_many_items_states(todoItemList_other);
    }
    public TodoItemsDataBaseImpl get_dataBase()
    {
      return this.dataBase;
    }
    public RecyclerView get_recyclerView()
    {
      return this.recyclerView;
    }

    public ItemAdapter get_itemAdapter()
    {
      return this.itemAdapter;
    }
    public List<TodoItem> get_todoItemList()
    {
      return todoItemList;
    }
  }

}



/*

SPECS:

- the screen starts out empty (no items shown, edit-text input should be empty)
- every time the user taps the "add TODO item" button:
    * if the edit-text is empty (no input), nothing happens
    * if there is input:
        - a new TodoItem (checkbox not checked) will be created and added to the items list
        - the new TodoItem will be shown as the first item in the Recycler view
        - the edit-text input will be erased
- the "TodoItems" list is shown in the screen
  * every operation that creates/edits/deletes a TodoItem should immediately be shown in the UI
  * the order of the TodoItems in the UI is:
    - all IN-PROGRESS items are shown first. items are sorted by creation time,
      where the last-created item is the first item in the list
    - all DONE items are shown afterwards, no particular sort is needed (but try to think about what's the best UX for the user)
  * every item shows a checkbox and a description. you can decide to show other data as well (creation time, etc)
  * DONE items should show the checkbox as checked, and the description with a strike-through text
  * IN-PROGRESS items should show the checkbox as not checked, and the description text normal
  * upon click on the checkbox, flip the TodoItem's state (if was DONE will be IN-PROGRESS, and vice versa)
  * add a functionality to remove a TodoItem. either by a button, long-click or any other UX as you want
- when a screen rotation happens (user flips the screen):
  * the UI should still show the same list of TodoItems
  * the edit-text should store the same user-input (don't erase input upon screen change)

Remarks:
- you should use the `dataBase` field of the activity
- you will need to create a class extending from RecyclerView.Adapter and use it in this activity
- notice that you have the "row_todo_item.xml" file and you can use it in the adapter
- you should add tests to make sure your activity works as expected. take a look at file `MainActivityTest.java`



(optional, for advanced students:
- save the TodoItems list to file, so the list will still be in the same state even when app is killed and re-launched
)

*/
