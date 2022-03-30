package com.example.prdownloder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;
import com.downloader.Status;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    File folder;
    Button buttonStart, buttonCancel;
    TextView textView;
    ProgressBar progressBar;
    private int downloadId;
    private String url = "https://drive.google.com/uc?export=download&id=16wVvoiJ4nAPOMxBqgKw4W99gqT1XnIVP";

    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        buttonStart = findViewById (R.id.buttonOne);
        buttonCancel = findViewById (R.id.buttonCancelOne);
        textView = findViewById (R.id.textViewProgressOne);
        progressBar = findViewById (R.id.progressBarOne);

        buttonStart.setOnClickListener (view -> checkPermission ( ));

    }

    private void checkPermission() {
        Dexter.withContext (getApplicationContext ( ))
                .withPermissions (Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener (new MultiplePermissionsListener ( ) {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted ( )) {
                            downloadFile ( );
                        } else {
                            Toast.makeText (getApplicationContext ( ), "Please Give Permission First", Toast.LENGTH_SHORT).show ( );
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest ( );
                    }
                }).check ( );
    }

    private void downloadFile() {
        ProgressDialog progressDialog = new ProgressDialog (this);
        progressDialog.setMessage ("Downloading....");
        progressDialog.setProgressStyle (ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show ( );
        folder = Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_DOCUMENTS);
        if (Status.RUNNING == PRDownloader.getStatus (downloadId)) {
            PRDownloader.pause (downloadId);
            return;
        }
        buttonStart.setEnabled (false);
        progressBar.setIndeterminate (true);
        if (Status.PAUSED == PRDownloader.getStatus (downloadId)) {
            PRDownloader.resume (downloadId);
            return;
        }
        downloadId = PRDownloader.download (url, folder.getPath ( ), "new.mp4")
                .build ( )
                .setOnStartOrResumeListener (() -> {
                    progressBar.setIndeterminate (false);
                    buttonStart.setEnabled (true);
                    buttonStart.setText ("Pause");
                    Toast.makeText (this, "Download Start", Toast.LENGTH_SHORT).show ( );
                    buttonCancel.setEnabled (true);
                })
                .setOnPauseListener (() -> {
                    buttonStart.setText ("Resume");
                    Toast.makeText (this, "Pause", Toast.LENGTH_SHORT).show ( );
                })
                .setOnCancelListener (() -> {
                    buttonStart.setText ("Start");
                    buttonCancel.setEnabled (false);
                    progressBar.setProgress (0);
                    textView.setText ("");
                    downloadId = 0;
                    progressBar.setIndeterminate (false);
                    Toast.makeText (this, "Canceled", Toast.LENGTH_SHORT).show ( );
                })
                .setOnProgressListener (progress -> {
                    long per = progress.currentBytes * 100 / progress.totalBytes;
                    progressBar.setProgress ((int) per);
                    progressBar.setIndeterminate (false);
                    progressDialog.setProgress ((int) per);
                    textView.setText (getProgressDisplayLine (progress.currentBytes, progress.totalBytes));
                })
                .start (new OnDownloadListener ( ) {
                    @Override
                    public void onDownloadComplete() {
                        progressDialog.dismiss();
                        buttonStart.setEnabled (false);
                        buttonCancel.setEnabled (false);
                        buttonStart.setText ("Complete");
                        Toast.makeText (getApplicationContext ( ), "Download Completed", Toast.LENGTH_SHORT).show ( );
                    }

                    @Override
                    public void onError(Error error) {
                        buttonStart.setText ("Start");
                        textView.setText ("");
                        progressBar.setProgress (0);
                        downloadId = 0;
                        buttonCancel.setEnabled (false);
                        progressBar.setIndeterminate (false);
                        buttonStart.setEnabled (true);
                        progressDialog.dismiss ( );
                        Toast.makeText (getApplicationContext ( ), "Download Failed", Toast.LENGTH_SHORT).show ( );
                    }
                });
        buttonCancel.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel (downloadId);
            }
        });
    }

    public static String getProgressDisplayLine(long currentBytes, long totalBytes) {
        return getBytesToMBString (currentBytes) + "/" + getBytesToMBString (totalBytes);
    }

    private static String getBytesToMBString(long bytes) {
        return String.format (Locale.ENGLISH, "%.2fMb", bytes / (1024.00 * 1024.00));
    }
}