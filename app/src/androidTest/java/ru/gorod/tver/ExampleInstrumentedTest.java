/*
 * *
 *  * Created by DemonApps on 14.07.20 20:03
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 14.07.20 18:59
 *
 */

package ru.gorod.tver;

import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ru.gorod_dubna.gorod_dubna", appContext.getPackageName());
    }
}
