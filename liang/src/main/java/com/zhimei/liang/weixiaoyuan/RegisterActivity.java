package com.zhimei.liang.weixiaoyuan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhimei.liang.customview.RoundImageView;

import java.io.File;
import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


public class RegisterActivity extends Activity {
    private ImageView addPhoto;
    private Bitmap picture;
    private Uri imageUri;
   // private ImageView imagetest;
    private EditText et_address;
    private EditText et_password;
    private EditText et_name;
    private Button next;
    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE=3;
    private static final int CHOOSE_PHOTO_CROP=4;
    private  File output;
    private String address;//地址
    private String password;//密码
    private String name;//昵称
    private String phone_number;//电话号码


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SMSSDK.initSDK(this, "bb55d5170bea", "89c378517a900a7b3d4fea2199d2c561");
        initView();
        chooseWays();
        sendSMS();

    }
    void initView(){
        addPhoto=(RoundImageView)findViewById(R.id.register_ib_photo);
       // imagetest=(ImageView)findViewById(R.id.imageView_test);
        et_address=(EditText)findViewById(R.id.reg_et_address);
        et_password=(EditText)findViewById(R.id.reg_et_password);
        et_name=(EditText)findViewById(R.id.reg_et_name);
        next=(Button)findViewById(R.id.reg_submit);
       // et_phone_number=(EditText)findViewById(R.id.reg_et_password);

       /* id=et_id.getText().toString();*/
        password=et_password.getText().toString();
        name=et_name.getText().toString();
      /*  phone_number=et_phone_number.getText().toString();*/




    }

    void sendSMS(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getData();

                address=et_address.getText().toString();
                password=et_password.getText().toString();
                name=et_name.getText().toString();
                RegisterPage registerPage = new RegisterPage();
               // Toast.makeText(RegisterActivity.this,address,Toast.LENGTH_SHORT).show();
                if(address.equals("")){
                    Toast.makeText(RegisterActivity.this,"亲，请填写地址",Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("")){
                    Toast.makeText(RegisterActivity.this,"亲，请填写密码",Toast.LENGTH_SHORT).show();
                }
                else if(name.equals("")){
                    Toast.makeText(RegisterActivity.this,"亲，请填写昵称",Toast.LENGTH_SHORT).show();
                }
                else{
                  registerPage.show(RegisterActivity.this);
                }

                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {

                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                            Log.i("liang", phone);


                            SMSSDK.submitUserInfo("123", "jialiang", null, "", "18842647883");
                        }
                    }

                    @Override
                    public void beforeEvent(int i, Object o) {
                        super.beforeEvent(i, o);
                        Log.i("liang","gvtrhjtesrgtrhgt");
                    }
                });
              //  registerPage.show(RegisterActivity.this);

            }
        });
    }


    void getData(){
        address=et_address.getText().toString();
        password=et_password.getText().toString();
        name=et_name.getText().toString();
        if(address==null||address==""){
            Toast.makeText(RegisterActivity.this,"亲，请填写地址",Toast.LENGTH_SHORT).show();
        }
        else if(password==null||password==""){
            Toast.makeText(RegisterActivity.this,"亲，请填写密码",Toast.LENGTH_SHORT).show();
        }
        else if(name==null||name==""){
            Toast.makeText(RegisterActivity.this,"亲，请填写昵称",Toast.LENGTH_SHORT).show();
        }

    }

     void chooseWays(){
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string[] = {"拍照", "从相册中选取"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                dialog.setTitle("选择照片");
                dialog.setItems(string, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takephoto();
                                break;
                            case 1:
                                choosephoto();
                            default:
                                break;

                        }
                    }
                });
                dialog.show();

            }

        });

    }

    /**
     * 打开相机拍照
     */

    private void takephoto() {


                output = new File(Environment
                        .getExternalStorageDirectory(), "output_image.jpg");
                try {
                    if (output.exists()) {
                        output.delete();
                    }
                    output.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(output);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);

            }


    public void onActivityResult(int req, int res, Intent data) {
        switch (req) {
            case TAKE_PHOTO:
                if (res == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra("MediaStore.EXTRA_OUTPUT", imageUri);

                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if (res == RESULT_OK) {
                    try {
                        Uri uri = data.getData();
                        Bitmap bit = BitmapFactory
                                .decodeStream(getContentResolver().openInputStream(
                                        uri));
                        picture=bit;
                       /* imagetest.setImageBitmap(picture);
                        imagetest.setVisibility(View.VISIBLE);*/
                        addPhoto.setImageBitmap(picture);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO_CROP:
                /**
                 * 对相册中的图片进行裁剪，首先要得到被选中图片的URI
                 */
                if (res == RESULT_OK) {
                    Uri uri_photo = data.getData();
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uri_photo, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra("MediaStore.EXTRA_OUTPUT", uri_photo);

                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;


            default:
                break;
        }
    }

    /**
     * 选择相册图片
     */
    private void choosephoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, CHOOSE_PHOTO_CROP);

    }


}
