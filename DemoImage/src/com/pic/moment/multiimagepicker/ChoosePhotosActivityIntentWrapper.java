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

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


public class ChoosePhotosActivityIntentWrapper {
    public static final int ACTIVITY_FOR_RESULT_KEY = 112;

    private static final String EXTRA_KEY_PATH_LIST = "key_path_list";

    public static final String TAG = ChoosePhotosActivityIntentWrapper.class.getSimpleName();

    private final Intent intent;

    public ChoosePhotosActivityIntentWrapper(Intent intent) {
	super();
	this.intent = intent;
    }

    public ChoosePhotosActivityIntentWrapper(Context packageContext, Class<?> cls) {
	super();
	intent = new Intent(packageContext, cls);
    }

    public ChoosePhotosActivityIntentWrapper(Context packageContext) {
	super();
	intent = new Intent(packageContext, MultiImagePickerActivity.class);
    }

    public Intent getIntent() {
	return intent;
    }

    public void setAssetPathList(ArrayList<String> pathList) {
	intent.putStringArrayListExtra(EXTRA_KEY_PATH_LIST, pathList);
    }

    public ArrayList<String> getAssetPathList() {
	return intent.getExtras().getStringArrayList(EXTRA_KEY_PATH_LIST);
    }

    public void startActivityForResult(Activity context, int code) {
	context.startActivityForResult(intent, code);
    }
}
