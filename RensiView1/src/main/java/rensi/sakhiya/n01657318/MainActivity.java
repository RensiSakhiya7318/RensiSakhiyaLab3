package rensi.sakhiya.n01657318;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;



public class MainActivity extends AppCompatActivity {

    int index = 0;

    int[] images = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));

        Button btnSave = findViewById(R.id.btnSave);
        Button btnOpen = findViewById(R.id.btnOpen);
        ImageButton imageButton = findViewById(R.id.imageButton);
        SwitchMaterial appSwitch = findViewById(R.id.appSwitch);

        // OPEN → Device Settings
        btnOpen.setOnClickListener(v ->
                startActivity(new Intent(Settings.ACTION_SETTINGS)));

        // SAVE → Website
        btnSave.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.website_url)))));

        // Rotate images
        imageButton.setOnClickListener(v -> {
            imageButton.setImageResource(images[index]);
            index = (index + 1) % images.length;
        });

        // Switch Snackbar
        appSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String msg = isChecked
                    ? getString(R.string.switch_on)
                    : getString(R.string.switch_off);

            Snackbar.make(buttonView, msg, Snackbar.LENGTH_LONG).show();
        });
    }

    // Back button dialog
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.exit_msg))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), (d, w) -> finish())
                .setNegativeButton(getString(R.string.no), (d, w) -> d.dismiss())
                .setIcon(R.drawable.ic_exit)
                .show();
    }


    // Log when app goes background
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LAB3", "Rensi Sakhiya - N01657318");
    }
}
