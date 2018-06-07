/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package pl.pisquared.tvgallery;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

/*
 * Details activity class that loads LeanbackDetailsFragment class
 */
public class DetailsActivity extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        Intent intent = getIntent();
        String url = intent.getStringExtra(Constants.EXTRA_URL_KEY);
        args.putString(Constants.EXTRA_URL_KEY, url);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.detail_fragment_container, fragment);
        fragmentTransaction.commit();
    }

}
