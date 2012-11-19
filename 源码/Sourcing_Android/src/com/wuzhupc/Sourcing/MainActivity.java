package com.wuzhupc.Sourcing;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends BaseActivity 
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCatureBackKey(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
