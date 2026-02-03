package rensi.sakhiya.n01657318;

// Name: Rensi Sakhiya
// Student ID: N01657318

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private int imageIndex = 0;

    private final int[] images = {
            R.drawable.img1_background,
            R.drawable.img2_background,
            R.drawable.img3_background,
            R.drawable.img4_background
    };

    private final String[] imageNames = {
            "Image 1",
            "Image 2",
            "Image 3",
            "Image 4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = findViewById(R.id.imageButton);
        EditText editText = findViewById(R.id.editText);
        SwitchCompat switchBtn = findViewById(R.id.switch1);

        // ImageButton click – rotate images
        imageButton.setOnClickListener(v -> {
            imageButton.setImageResource(images[imageIndex]);
            Toast.makeText(
                    this,
                    getString(R.string.toast_image, imageNames[imageIndex]),
                    Toast.LENGTH_SHORT
            ).show();

            imageIndex = (imageIndex + 1) % images.length;
        });

        // Open Settings button
        findViewById(R.id.btnOpen).setOnClickListener(v ->
                startActivity(new Intent(Settings.ACTION_SETTINGS))
        );

        // Save button – open website
        findViewById(R.id.btnSave).setOnClickListener(v ->
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com")
                ))
        );

        // Switch – Snackbar
        switchBtn.setOnCheckedChangeListener((buttonView, isChecked) ->
                Snackbar.make(
                        buttonView,
                        isChecked
                                ? getString(R.string.switch_on)
                                : getString(R.string.switch_off),
                        Snackbar.LENGTH_LONG
                ).show()
        );

        // Handle back button
        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        showExitDialog();
                    }
                });
    }

    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_exit_background)
                .setTitle(R.string.app_name)
                .setMessage(R.string.exit_msg)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, (dialog, which) -> finish())
                .setNegativeButton(R.string.no, null)
                .show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LAB3", "Rensi Sakhiya N01657318");
    }
}
