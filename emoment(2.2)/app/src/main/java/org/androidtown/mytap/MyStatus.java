package org.androidtown.mytap;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sangsoo on 2015-06-27.
 */
public class MyStatus extends Fragment {

    SelectMyStatus pickerDialog;
    ImageView imageView;
    String TAG = "emoment";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.my_status, container, false);
        imageView = (ImageView) vi.findViewById(R.id.imageView);

        pickerDialog = new SelectMyStatus(container.getContext());
        pickerDialog.setTitle("내 표정");

        pickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.d(TAG, "sendButton clicked");
                imageView.setImageBitmap(pickerDialog.getImage());

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Dialog start");
                pickerDialog.show();
            }
        });
        return vi;
    }
}
