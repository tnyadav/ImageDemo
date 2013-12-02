/**
 * The MIT License (MIT)

Copyright (c) 2013 Chute

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.pic.moment.multiimagepicker;

import com.pic.moment.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;



public class MultiImagePickerActivity extends FragmentActivity {

  public static final String TAG = MultiImagePickerActivity.class.getSimpleName();
  private GridView grid;
  private PhotoSelectCursorAdapter gridAdapter;

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_select_photos);

    grid = (GridView) findViewById(R.id.gridView);
    getSupportLoaderManager().initLoader(1, null,
        new AssetsLoaderCallback());

    final Button ok = (Button) findViewById(R.id.buttonOk);
    ok.setOnClickListener(new OnOkClickListener());
    final Button cancel = (Button) findViewById(R.id.buttonCancel);
    cancel.setOnClickListener(new OnCancelClickListener());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  private final class AssetsLoaderCallback implements LoaderCallbacks<Cursor> {

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
      return new CursorAsyncTaskLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
      if (cursor == null) {
        return;
      }
      gridAdapter = new PhotoSelectCursorAdapter(MultiImagePickerActivity.this, cursor);
      grid.setAdapter(gridAdapter);
      grid.setOnItemClickListener(new OnGridItemClickListener());

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
      // TODO Auto-generated method stub

    }

  }

  private final class OnGridItemClickListener implements OnItemClickListener {

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view,
        final int position,
        final long id) {
      if (gridAdapter.tick.containsKey(position)) {
        gridAdapter.tick.remove(position);
      } else {
        gridAdapter.tick.put(position, gridAdapter.getItem(position));
      }
      gridAdapter.notifyDataSetChanged();
    }
  }

  private final class OnCancelClickListener implements OnClickListener {

    @Override
    public void onClick(final View v) {
      finish();
    }
  }

  private final class OnOkClickListener implements OnClickListener {

    @Override
    public void onClick(final View v) {
      if (!gridAdapter.hasSelectedItems()) {
        Toast.makeText(getApplicationContext(), R.string.toast_choose_photos,
            Toast.LENGTH_SHORT).show();
        return;
      }
      final ChoosePhotosActivityIntentWrapper wrapper = new ChoosePhotosActivityIntentWrapper(
          new Intent());
      wrapper.setAssetPathList(gridAdapter.getSelectedFilePaths());
      setResult(Activity.RESULT_OK, wrapper.getIntent());
      finish();
    }
  }

}