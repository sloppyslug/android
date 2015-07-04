package org.androidtown.mytap;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by sangsoo on 2015-06-27.
 */
public class MyStatus extends Fragment {

    SelectMyStatus pickerDialog;
    ImageView imageView;
    EditText editText;
    String TAG = "emoment";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.my_status, container, false);
        imageView = (ImageView) vi.findViewById(R.id.imageView);
        editText = (EditText)vi.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            String previousString="";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previousString= s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getLineCount() >= 4)
                {
                    editText.setText(previousString);
                    editText.setSelection(editText.length());
                }
            }
        });


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
