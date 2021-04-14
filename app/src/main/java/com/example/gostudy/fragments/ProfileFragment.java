package com.example.gostudy.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gostudy.LoginActivity;
import com.example.gostudy.MainActivity;
import com.example.gostudy.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView tvUsername, tvUserId, tvUserDescription;
    private ImageView ivAvatar, ivBackground;
    private Button btnLogout;
    public static final String TAG = "ProfileFragment";
    public static final int FROM_GALLERY = 20;
    public static final int FROM_CAMERA = 21;
    public static final int CAMERA_PERMISSION = 1;

    private File photoFile;
    public String photoFileName = "profile_photo.jpg";
    private Uri imageUri;

    Context context;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUsername=view.findViewById(R.id.tvUsername);
        tvUserId=view.findViewById(R.id.tvUserId);
        tvUserDescription =view.findViewById(R.id.tvUserDescription);
        ivAvatar=view.findViewById(R.id.ivAvatar);
        ivBackground=view.findViewById(R.id.ivBackground);
        btnLogout=view.findViewById(R.id.btnLogout);

        context = getContext();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        // request and set personal info
        ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser != null) {
            tvUsername.setText(parseUser.getUsername());
            tvUserId.setText(parseUser.getObjectId());
            tvUserDescription.setText("");

            ParseFile profileImage = parseUser.getParseFile("profileImage");
            if (profileImage != null) {
                Glide.with(context).load(profileImage.getUrl()).into(ivAvatar);
            } else {
                Log.i(TAG,"no profile image");
            }
        }

        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Set your profile photo");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo"))
                        {
                            Log.i(TAG, "User chooses to take photo.");
                            // check camera permission, this needs more test if possible
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                if (ContextCompat.checkSelfPermission(context,
                                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(getActivity(), new
                                            String[]{Manifest.permission.CAMERA }, CAMERA_PERMISSION);
                                } else {
                                    getFromCamera();
                                }
                            } else {
                                getFromCamera();
                            }

                        }
                        else if (options[item].equals("Choose from Gallery"))
                        {
                            Log.i(TAG, "User chooses to upload from gallery.");
                            getFromGallery();
                        }
                        else if (options[item].equals("Cancel")) {
                            Log.i(TAG, "Action cancelled.");
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FROM_CAMERA:
                if(resultCode == RESULT_OK) {
                    Log.i(TAG,"camera: result okay!"+data);
                    Log.i(TAG, "Back from camera.");
                    try {
                        setProfilePhoto();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case FROM_GALLERY:
                if(resultCode == RESULT_OK) {
                    Log.i(TAG,"Back from gallery.");
                    try {
                        imageUri = data.getData();
                        setProfilePhoto();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void setProfilePhoto() throws IOException {
        // locally set the profile photo
        Bitmap bitmap =BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imageUri));
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Bitmap resizedBitmap = toSquareBitmap(bitmap);
        ivAvatar.setImageBitmap(resizedBitmap);
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File resizedFile = getPhotoFileUri(photoFileName + "_resized");
        FileOutputStream fos = new FileOutputStream(resizedFile);
        resizedFile.createNewFile();
        fos.write(bytes.toByteArray());
        fos.close();
        Log.i(TAG,"file saved complete! "+resizedFile.toString());

        ParseUser user = ParseUser.getCurrentUser();
        user.put("profileImage",new ParseFile(resizedFile));
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null) {
                    Log.e(TAG, "Error while saving",e);
                    Toast.makeText(getContext(), "Error while saving!",Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG,"Profile photo save was successful!");
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    getFromCamera();
                } else {
                    Toast.makeText(context, "You denied the peimission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getFromCamera() {
        photoFile = getPhotoFileUri(photoFileName);
        imageUri = FileProvider.getUriForFile(context, "com.codepath.fileprovider", photoFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            startActivityForResult(intent, FROM_CAMERA);
        }

    }
    private void getFromGallery() {
        Intent getImage = new Intent(Intent.ACTION_PICK, null);
        getImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(getImage, FROM_GALLERY);
    }

    public File getPhotoFileUri(String fileName) {
        File mediaStorageDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }
    public Bitmap toSquareBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            return Bitmap.createBitmap(bitmap,(width-height)/2, 0,height,height);
        } else {
            return Bitmap.createBitmap(bitmap,0,(height-width)/2,width,width);
        }
    }
}