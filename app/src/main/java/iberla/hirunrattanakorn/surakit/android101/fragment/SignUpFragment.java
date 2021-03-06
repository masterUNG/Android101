package iberla.hirunrattanakorn.surakit.android101.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import iberla.hirunrattanakorn.surakit.android101.R;
import iberla.hirunrattanakorn.surakit.android101.manager.MyAlert;

/**
 * Created by Menn on 8/8/2560.
 */

public class SignUpFragment extends Fragment {

    //Explcit
    private ImageView backImageView, saveImageView,
            uploadImageView, pictureImageView;
    private EditText nameEditText, userEditText, passwordEditText;
    private String nameString, userString, passwordString, pathPictureString;
    private String tag = "10AugV1";
    private Uri uri;
    private boolean aBoolean = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;
    }   // onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Initial View
        initialView();

        //Back Controller
        backController();

        //Save Controller
        saveController();

        //Picture Controller
        pictureController();

    }   // onActivityCreate

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            Log.d(tag, "RESULT_OK");
            aBoolean = false;
            uri = data.getData();

            //Show Image
            showImage();

            //Find Path and Name of Picture

            String[] strings = new String[]{MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver()
                    .query(uri, strings, null, null, null);

            if (cursor != null) {

                cursor.moveToFirst();
                int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                pathPictureString = cursor.getString(index);

            } else {
                pathPictureString = uri.getPath();
            }

            Log.d(tag, "pathPicture ==> " + pathPictureString);

        }   // if

    }   // onActivityResult

    private void showImage() {
        try {

            Bitmap bitmap = BitmapFactory.decodeStream(getActivity()
                    .getContentResolver().openInputStream(uri));
            pictureImageView.setImageBitmap(bitmap);

        } catch (Exception e) {
            Log.d(tag, "e ShowImage ==> " + e.toString());
        }
    }

    private void pictureController() {
        pictureImageView = getView().findViewById(R.id.imvPicture);
        pictureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Choose Picture
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Please Choose App"), 1);

            }   // onClick
        });
    }

    private void saveController() {
        saveImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Value From EditText
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
                    //Have Space
                    Log.d(tag, "Have Space");

                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));

                } else if (aBoolean) {
                    //Non Choose Picture
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.title_non_choose),
                            getString(R.string.message_non_choose));
                } else {
                    //No Space and Choosed Picture
                    Log.d(tag, "No Space and Choosed Picture");

                }   // if

            }   // onClick
        });
    }

    private void backController() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Back to Main Fragment
                getActivity().getSupportFragmentManager()
                        .popBackStack();

            }   // onClick
        });
    }

    private void initialView() {
        backImageView = getView().findViewById(R.id.imvBack);
        saveImageView = getView().findViewById(R.id.imvSave);
        nameEditText = getView().findViewById(R.id.edtName);
        userEditText = getView().findViewById(R.id.edtUser);
        passwordEditText = getView().findViewById(R.id.edtPassword);
        uploadImageView = getView().findViewById(R.id.imvUpload);
        pictureImageView = getView().findViewById(R.id.imvPicture);

    }

}//Main Class
