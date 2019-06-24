package com.pk.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pk.R;

public class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.MyHolder> {
    String[] name, account;
    int[] image;
    Context context;

    public SocialMediaAdapter(Context context) {
        this.context = context;
        name = context.getResources().getStringArray(R.array.media_name);
        account = context.getResources().getStringArray(R.array.media_account);
        image = new int[]{R.drawable.dial, R.drawable.gmail, R.drawable.fb, R.drawable.whatsapp, R.drawable.twitter,
                R.drawable.instagram, R.drawable.youtube};

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.social_media_strip, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int position) {
        myHolder.socialImage.setImageResource(image[position]);
        myHolder.socialAccount.setText(account[position]);
        myHolder.socialName.setText(name[position]);


        switch (myHolder.getAdapterPosition()) {
            //This is dial
            case 0:
                myHolder.socialView.setBackgroundResource(R.color.dial_color);
                break;
            //This is Facebook
            case 1:
                myHolder.socialView.setBackgroundResource(R.color.youtube_color);
                break;
            case 2:
                myHolder.socialView.setBackgroundResource(R.color.fb_color);
                break;
            //This is WhatsApp
            case 3:
                myHolder.socialView.setBackgroundResource(R.color.whatsApp_color);
                break;
            //This is Twitter
            case 4:
                myHolder.socialView.setBackgroundResource(R.color.twitter_color);
                break;
            //This is Instagram
            case 5:
                myHolder.socialView.setBackgroundResource(R.color.instagram_color);
                break;
            //This is Youtube
            case 6:
                myHolder.socialView.setBackgroundResource(R.color.youtube_color);
                break;
        }

        //setting onclick to individual item
        myHolder.socialCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        //dial
                        final Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.save_call_dialog);
                        dialog.setCancelable(true);

                        Button dial = dialog.findViewById(R.id.dialog_dial);
                        Button save = dialog.findViewById(R.id.dialog_save);
                        TextView message = dialog.findViewById(R.id.dialog_message);

                        message.setText(context.getResources().getString(R.string.save_call));

                        dial.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent dialContact = new Intent(Intent.ACTION_DIAL);
                                dialContact.setData(Uri.parse("tel:0743304418"));
                                v.getContext().startActivity(dialContact);
                            }
                        });

                        save.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent saveContact = new Intent(ContactsContract.Intents.Insert.ACTION);
                                saveContact.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                                saveContact
                                        .putExtra(ContactsContract.Intents.Insert.EMAIL, context.getResources().getString(R.string.cu_mail))
                                        .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME)
                                        .putExtra(ContactsContract.Intents.Insert.PHONE, "0743304418")
                                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MAIN)
                                        .putExtra(ContactsContract.Intents.Insert.NAME, "Pwani University CU");
                                v.getContext().startActivity(saveContact);

                            }
                        });

                        dialog.show();
                        break;
                    case 1:
                        Intent gmail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                                "pucusecretary@gmail.com", null));
                        gmail.putExtra(Intent.EXTRA_SUBJECT, "");
                        gmail.putExtra(Intent.EXTRA_TEXT, "");
                        if (gmail.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(Intent.createChooser(gmail, "Send Email"));
                        } else {
                            Toast.makeText(context, "No Email Application found. \n " +
                                    "Please send mail via:" + account[position], Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 2:
                        //fb
                        Toast.makeText(context, "OPENING " + name[position] + " WEBSITE." +
                                "\n Please search " + account[position], Toast.LENGTH_LONG).show();
                        openSocialMediaSites("https://www.facebook.com");

                        break;

                    case 3:
                        //WhatsApp

                        final Dialog whatsAPPDialog = new Dialog(context);
                        whatsAPPDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        whatsAPPDialog.setCancelable(true);
                        whatsAPPDialog.setContentView(R.layout.save_call_dialog);

                        Button saveTelNo = whatsAPPDialog.findViewById(R.id.dialog_save);
                        Button dialContact = whatsAPPDialog.findViewById(R.id.dialog_dial);
                        TextView dialogMessage = whatsAPPDialog.findViewById(R.id.dialog_message);
                        RelativeLayout dialogRelative = whatsAPPDialog.findViewById(R.id.dialog_relative);


                        dialContact.setVisibility(View.GONE);
                        saveTelNo.setGravity(Gravity.CENTER_HORIZONTAL);
                        dialogMessage.setText(context.getResources().getString(R.string.save_or));
                        dialogRelative.setBackgroundResource(R.drawable.call_bg);
                        dialogMessage.setBackgroundResource(R.drawable.call_bg);

                        saveTelNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent saveContact = new Intent(ContactsContract.Intents.Insert.ACTION);
                                saveContact.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                                saveContact
                                        .putExtra(ContactsContract.Intents.Insert.EMAIL, context.getResources().getString(R.string.cu_mail))
                                        .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME)
                                        .putExtra(ContactsContract.Intents.Insert.PHONE, "0743304418")
                                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                                        .putExtra(ContactsContract.Intents.Insert.NAME, "Pwani University CU");
                                v.getContext().startActivity(saveContact);
                            }
                        });
                        whatsAPPDialog.show();
                        /*Intent whatsApp = new Intent(Intent.ACTION_SEND);
                        whatsApp.setPackage("com.whatsapp");
                        v.getContext().startActivity(whatsApp);*/
                        break;

                    case 4:
                        //Twitter
                        Toast.makeText(context, "OPENING" + name[position] + "WEBSITE." +
                                "\n Please search" + account[position], Toast.LENGTH_SHORT).show();
                        openSocialMediaSites("https://www.twitter.com");
                        break;
                    case 5:
                        //Instagram
                        Toast.makeText(context, "OPENING" + name[position] + "WEBSITE." +
                                "\n Please search" + account[position], Toast.LENGTH_SHORT).show();
                        openSocialMediaSites("https://www.instagram.com");
                        break;
                    case 6:
                        Toast.makeText(context, "OPENING" + name[position] + "WEBSITE." +
                                "\n Please search" + account[position], Toast.LENGTH_SHORT).show();
                        openSocialMediaSites("https://www.youtube.com/watch?v=cVHrkYbEgFM");
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    private void openSocialMediaSites(String link) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView socialName, socialAccount;
        ImageView socialImage;
        CardView socialCard;
        View socialView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            socialName = itemView.findViewById(R.id.social_name);
            socialAccount = itemView.findViewById(R.id.social_account);
            socialImage = itemView.findViewById(R.id.social_image);
            socialCard = itemView.findViewById(R.id.social_card);
            socialView = itemView.findViewById(R.id.social_view);
        }
    }
}