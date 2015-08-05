package de.jandrotek.android.aspectra.plottest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import de.jandrotek.android.aspectra.core.AspectraGlobals;
import de.jandrotek.android.aspectra.libplotspectrav3.PlotViewFragment;

public class MainActivity extends AppCompatActivity
    implements ButtonHolderFragment.OnButtonClickListener
{

    private PlotTestController mController = null;

    private static PlotViewFragment mPlotViewFragment;
    private static ButtonHolderFragment mButtonHolderFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mController = new PlotTestController(this);

        mButtonHolderFragment = ButtonHolderFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentButtonHolder, mButtonHolderFragment)
                .commit();

        mPlotViewFragment = PlotViewFragment.newInstance(AspectraGlobals.ACT_ITEM_LIVE_VIEW, dummyItems);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fvPlotView, mPlotViewFragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onButtonClickListener(int _buttonId){
        switch (_buttonId) {
            case ButtonHolderFragment.eButtonMoveLeft:{
                mController.onButtonMoveLeft();
                break;
            }
            case ButtonHolderFragment.eButtonMoveRight:{
                mController.onButtonMoveRight();
                break;
            }
        } // switch
    }

    public void updatePlot(int[] data){

    }
}
