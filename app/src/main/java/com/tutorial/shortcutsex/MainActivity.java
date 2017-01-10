package com.tutorial.shortcutsex;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView shortcutText = (TextView) findViewById(R.id.shortcut_text);
        Button createDynamicBtn = (Button) findViewById(R.id.shortcut_dynamic);

        final ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        final String fbShortId = "dynamic_fb_shortcut";
        final int fbShortRank = 1;
        final String fbShortLabel = "dynamic_short";
        final String fbLongLabel = "FaceBook";
        final Icon fbIcon = Icon.createWithResource(MainActivity.this, R.drawable.facebook);
        final Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));

        final String googleShortId = "dynamic_google_shortcut";
        final int googleShortRank = 2;
        final String googleShortLabel = "dynamic_short";
        final String googleLongLabel = "Google";
        final Icon googleIcon = Icon.createWithResource(MainActivity.this, R.drawable.google);
        final Intent googleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.tw/"));

        createDynamicBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N_MR1)
            @Override
            public void onClick(View view) {
                ShortcutInfo fbInfo = createDynamicShortcuts(fbShortId, fbShortRank, fbShortLabel, fbLongLabel, fbIcon,
                        fbIntent);
                ShortcutInfo googleInfo = createDynamicShortcuts(googleShortId, googleShortRank, googleShortLabel,
                        googleLongLabel, googleIcon, googleIntent);

                if (shortcutManager.setDynamicShortcuts(Arrays.asList(fbInfo, googleInfo))) {
                    shortcutText.setText("Setup Complete");
                } else {
                    shortcutText.setText("Setup False");
                }
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private ShortcutInfo createDynamicShortcuts(String shortId, int rank, CharSequence shortLabel,
            CharSequence longLabel, Icon icon, Intent intent) {
        return new ShortcutInfo.Builder(this, shortId).setRank(rank).setShortLabel(shortLabel).setLongLabel(longLabel)
                .setIcon(icon).setIntent(intent).build();
    }
}
