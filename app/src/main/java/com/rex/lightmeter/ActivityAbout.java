/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rex.lightmeter;

import android.app.ActionBar;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.MenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityAbout extends PreferenceActivity {

    private final Logger mLogger = LoggerFactory.getLogger("RexLog");

    private static final String KEY_ABOUT_VERSION = "preference_about_version";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogger.trace("");

        addPreferencesFromResource(R.xml.about);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            Preference prefsVersion = getPreferenceScreen().findPreference(KEY_ABOUT_VERSION);
            String strTemplate = getResources().getString(R.string.about_version_summary);
            String strVersion = String.format(strTemplate, packageInfo.versionName);
            prefsVersion.setSummary(strVersion);
        } catch (NameNotFoundException ex) {
            mLogger.error("Failed to parse package info {}", ex.getMessage());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLogger.trace("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLogger.trace("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            // TODO: Should use android:parentActivityName in AndroidManifest.xml instead
            finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
