/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.android.contacts.logging;

import android.text.TextUtils;

import com.android.contacts.activities.PeopleActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores constants identifying individual screens/dialogs/fragments in the application, and also
 * provides a mapping of integer id -> screen name mappings for analytics purposes.
 */
public class ScreenEvent {
    private static final Map<Integer, String> sScreenNameMap = new HashMap<>();

    private static final String FRAGMENT_TAG_SEPARATOR = "#";

    public static final int UNKNOWN = 0;

    public static final int SEARCH = 1;

    static {
        sScreenNameMap.put(SEARCH, getScreenNameWithTag(
                PeopleActivity.class.getSimpleName(), "Search"));
    }

    /**
     * For a given screen type, returns the actual screen name that is used for logging/analytics
     * purposes.
     *
     * @param screenType unique ID of a type of screen
     *
     * @return the tagged version of the screen name corresponding to the provided screenType,
     *         or {@null} if the provided screenType is unknown.
     */
    public static String getScreenName(int screenType) {
        return sScreenNameMap.get(screenType);
    }

    /**
     * Build a tagged version of the provided screenName if the tag is non-empty.
     *
     * @param screenName Name of the screen.
     * @param tag Optional tag describing the screen.
     * @return the unchanged screenName if the tag is {@code null} or empty, the tagged version of
     *         the screenName otherwise.
     */
    public static String getScreenNameWithTag(String screenName, String tag) {
        if (TextUtils.isEmpty(tag)) {
            return screenName;
        }
        return screenName + FRAGMENT_TAG_SEPARATOR + tag;
    }
}