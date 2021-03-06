package com.example.project;

import android.content.Context;

import com.example.project.database.provider.ExampleSQLiteOpenHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public abstract class RoboTestCase {

    protected Context context;

    @Before
    public void roboSetup() {
        context = RuntimeEnvironment.application;
    }

    @After
    public void finishRobolectricTest() {
        resetSingleton(ExampleSQLiteOpenHelper.class, "sInstance");
    }

    private void resetSingleton(Class clazz, String fieldName) {
        Field instance;
        try {
            instance = clazz.getDeclaredField(fieldName);
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
