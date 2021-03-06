package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SingleItemViewActivity extends QuickMenu {

    int itemIndex = -1;
    PatientSingleton ps = PatientSingleton.getInstance();
    JSONArray currentArray = ps.getCurrentArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PatientSingleton.getInstance().getCurrentItemType() == PatientSingleton.ItemType.journalEntry) {
            setContentView(R.layout.generic_single_object_4buttons_view);
        } else {
            setContentView(R.layout.generic_single_object_view);
        }
        itemIndex = getIntent().getExtras().getInt("itemIndex");

        TextView heading = (TextView) findViewById(R.id.heading_text);
        TextView content = (TextView) findViewById(R.id.content_text);
        try {
            JSONObject object = PatientSingleton.getInstance().getCurrentObject();
            heading.setText(object.getString("created"));
            content.setText(object.getString("content"));
        } catch (JSONException je) {
            System.out.println("Couldn't get current JSON object to display in single item view.");
        }

        if (itemIndex == 0) {   // first item
            Button previous = (Button) findViewById(R.id.button_previous);
            previous.setClickable(false);
            previous.setVisibility(View.INVISIBLE);
        }

        if (itemIndex == currentArray.length() - 1) {       // last item
            Button next = (Button) findViewById(R.id.button_next);
            next.setClickable(false);
            next.setVisibility(View.INVISIBLE);
        }
   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_item_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToNextItem(View view) throws JSONException {
        if (itemIndex < currentArray.length() - 1) {
            Intent intent = new Intent(this, SingleItemViewActivity.class);
            itemIndex++;
            intent.putExtra("itemIndex", itemIndex);
            ps.setCurrentObject(currentArray.getJSONObject(itemIndex));
            startActivity(intent);
        }
    }

    public void goToPreviousItem(View view) throws JSONException {
        if (itemIndex > 0) {
            Intent intent = new Intent(this, SingleItemViewActivity.class);
            itemIndex--;
            intent.putExtra("itemIndex", itemIndex);
            ps.setCurrentObject(currentArray.getJSONObject(itemIndex));
            startActivity(intent);
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, PatientSingleton.getInstance().getBackToPreviewsClass());
        startActivity(intent);
    }

    public void goToEditItem(View view) {
        Intent intent = new Intent(this, SingleItemEditActivity.class);
        intent.putExtra("edit", true);
        startActivity(intent);
    }

    public void goToDeleteItem(View view) {
        Intent intent = new Intent(this, SingleItemDeleteActivity.class);
        startActivity(intent);
    }

}
