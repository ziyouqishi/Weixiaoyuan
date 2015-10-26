package com.zhimei.liang.weixiaoyuan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class Goods_release_Activity extends Activity {
    private ImageButton getPhoto,back,index;
    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;
    private static final int CHOOSE_PHOTO_CROP=3;
    private Bitmap picture;
    private Uri imageUri;
    private File output;
    private Spinner type;
    private  String[] planets;
    private String catagary;//商品的种类


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_release_);
        initViews();
    }
    void initViews(){
        getPhoto=(ImageButton)findViewById(R.id.rel_two_getphoto);
        back=(ImageButton)findViewById(R.id.rel_back);
        index=(ImageButton)findViewById(R.id.rel_index);
        type=(Spinner)findViewById(R.id.rel_spinner1);

        //获取数组资源
        Resources res = getResources();
         planets = res.getStringArray(R.array.goods_catagory);

        index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(Goods_release_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string[] = {"拍照", "从相册中选取"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(Goods_release_Activity.this);
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

        /*type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                *//*catagary=planets[position];
                Toast.makeText(Goods_release_Activity.this,planets[position],Toast.LENGTH_SHORT).show();*//*
            }
        });*/
       type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               catagary=planets[position];
               Toast.makeText(Goods_release_Activity.this,planets[position],Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

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
        /**
         * 打开拍照的界面
         */
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        startActivityForResult(intent, TAKE_PHOTO);

    }


    public void onActivityResult(int req, int res, Intent data) {
        switch (req) {
            case TAKE_PHOTO:
                if (res ==RESULT_OK) {

                    /**
                     * 打开裁剪的界面。
                     */
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra("MediaStore.EXTRA_OUTPUT", imageUri);

                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if (res == RESULT_OK) {
                    /**
                     * 将裁剪后的图片进行处理， Uri uri = data.getData();得到裁剪后图片的uri。
                     */
                    try {
                        Uri uri = data.getData();
                        Bitmap bit = BitmapFactory
                                .decodeStream(getContentResolver().openInputStream(
                                        uri));
                        picture=bit;
                       /* imagetest.setImageBitmap(picture);
                        imagetest.setVisibility(View.VISIBLE);*/
                        // finish();
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
        /**
         * 打开选择图片的界面，选择结束后，进入裁剪界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, CHOOSE_PHOTO_CROP);

    }


}
