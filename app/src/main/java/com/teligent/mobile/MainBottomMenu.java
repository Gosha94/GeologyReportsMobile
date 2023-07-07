package com.teligent.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainBottomMenu  extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_bottom_menu, menu);
        return true;
    }

//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        //respond to menu item selection
//        switch (item.getItemId()) {
//
//            case R.id.about:
//
//                startActivity(new Intent(this, About.class));
//
//                return true;
//
//            case R.id.help:
//
//                startActivity(new Intent(this, Help.class));
//
//                return true;
//
//            default:
//
//                return super.onOptionsItemSelected(item);
//        }
//    }
}