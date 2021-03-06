package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ProfilePanelsActivity extends QuickMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        HelperSingleton.getInstance().updateJournalEntries(this);
        HelperSingleton.getInstance().updateMedicalNotes(this);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_panels);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_panels, menu);
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

    /** Called when the user clicks the Profile View button */
    public void goToProfileView(View view) {
        Intent intent = new Intent(this, ProfileViewActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Profile View button */
    public void goToJournalView(View view) {
        Intent intent = new Intent(this, JournalViewActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Profile View button */
    public void goToMedicalNotesView(View view) {
        Intent intent = new Intent(this, MedicalNotesViewActivity.class);
        startActivity(intent);
    }
}
