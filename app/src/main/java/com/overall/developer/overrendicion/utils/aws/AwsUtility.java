package com.overall.developer.overrendicion.utils.aws;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.services.s3.AmazonS3Client;
import com.overall.developer.overrendicion.RendicionApplication;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionActivity;

import java.io.File;

public class AwsUtility
{
    //region AWS Upload
    public static void UploadTransferUtilityS3(Context context, String path)
    {
        AWSMobileClient.getInstance().initialize(context).execute();

        TransferUtility transferUtility =   TransferUtility.builder()
                                            .context(RendicionApplication.getContext())
                                            .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                                            .s3Client(new AmazonS3Client(AWSMobileClient.getInstance().getCredentialsProvider()))
                                            .build();

        TransferObserver uploadObserver =  transferUtility.upload(
                                        "uploads/"+ path.substring(34),
                                            new File(path));

        Log.i("AWS",path);


        // Attach a listener to the observer to get state update and progress notifications
        uploadObserver.setTransferListener(new TransferListener() {

            @Override
            public void onStateChanged(int id, TransferState state) {
                if (TransferState.COMPLETED == state) {
                    // Handle a completed upload.
                    Log.i("AWS", state.toString());
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
                int percentDone = (int)percentDonef;

                Log.d("AWS", "ID:" + id + " bytesCurrent: " + bytesCurrent
                        + " bytesTotal: " + bytesTotal + " " + percentDone + "%");
            }

            @Override
            public void onError(int id, Exception ex) {
                Log.i("AWS","Error al subir imagen"+ ex.getMessage());
                // Handle errors
            }

        });

        // If you prefer to poll for the data, instead of attaching a
        // listener, check for the state and progress in the observer.
        if (TransferState.COMPLETED == uploadObserver.getState()) {
            // Handle a completed upload.
        }

        Log.d("YourActivity", "Bytes Transferrred: " + uploadObserver.getBytesTransferred());
        Log.d("YourActivity", "Bytes Total: " + uploadObserver.getBytesTotal());

    }
    //endregion

    //region AWS Download
    public static void downloadWithTransferUtility(Context context)
    {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/overRendicion/";

        TransferUtility transferUtility =   TransferUtility.builder()
                                            .context(context)
                                            .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                                            .s3Client(new AmazonS3Client(AWSMobileClient.getInstance().getCredentialsProvider()))
                                            .build();

        TransferObserver downloadObserver = transferUtility.download("uploads/20181012022614.jpg",
                                            new File(path+"20181012022614.jpg"));

        // Attach a listener to the observer to get state update and progress notifications
        downloadObserver.setTransferListener(new TransferListener()
        {
            @Override
            public void onStateChanged(int id, TransferState state) {
                if (TransferState.COMPLETED == state) {
                    Toast.makeText(context,"-"+ path, Toast.LENGTH_LONG).show();
                    // Handle a completed upload.
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
                int percentDone = (int) percentDonef;

                Log.d("AWS", "   ID:" + id + "   bytesCurrent: " + bytesCurrent + "   bytesTotal: " + bytesTotal + " " + percentDone + "%");
            }

            @Override
            public void onError(int id, Exception ex) {
                // Handle errors
                Log.d("AWS", "Error AWS " + ex.getMessage());
            }

        });

        // If you prefer to poll for the data, instead of attaching a
        // listener, check for the state and progress in the observer.
        if (TransferState.COMPLETED == downloadObserver.getState())
        {
            // Handle a completed upload.
            Toast.makeText(context,"-"+ path, Toast.LENGTH_LONG).show();
        }

        Log.d("AWS", "Bytes Transferrred: " + downloadObserver.getBytesTransferred());
        Log.d("AWS", "Bytes Total: " + downloadObserver.getBytesTotal());
    }
    //endregion

}
