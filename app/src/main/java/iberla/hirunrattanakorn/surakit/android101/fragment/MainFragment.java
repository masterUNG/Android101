package iberla.hirunrattanakorn.surakit.android101.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;

import iberla.hirunrattanakorn.surakit.android101.R;

/**
 * Created by Menn on 8/8/2560.
 */

public class MainFragment extends Fragment {

    //Explicit
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }   // onCreateView ทำหน้าที่ สร้างหน้ากาก View ไปแปะ ที่ Activity

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Initial View
        initialView();

        //TextView Controller
        textViewController();

    }   // onActivityCreate จะทำงาน พร้อม Activity เราจะเริ่มต้นการทำงานโค้ดที่นี่

    private void textViewController() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Move to SignUpFragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.myContent, new SignUpFragment())
                        .addToBackStack(null)
                        .commit();

            }   // onClick
        });
    }

    private void initialView() {
        userEditText = getView().findViewById(R.id.edtUser);
        passwordEditText = getView().findViewById(R.id.edtPassword);
        textView = getView().findViewById(R.id.txtNewRegister);
        button = getView().findViewById(R.id.btnSignIn);
    }
} //Main Class
