package com.example.newsdesk;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class FirestoreQueryLiveData extends LiveData<QuerySnapshot> {
    private static final String Log_tag = "FirebaseQueryLiveData";
    //private final CollectionReference documentReference;
    private final Query documentReference;
    private final MyValueEventListener listner = new MyValueEventListener();
    private ListenerRegistration registration ;

    public FirestoreQueryLiveData(Query ref){
        this.documentReference = ref;
    }


    @Override
    protected void onActive(){
        Log.d(Log_tag, "onActive");
        registration = documentReference.addSnapshotListener(listner);
    }

    @Override
    protected void onInactive(){
        Log.d(Log_tag, "onInactive");
        //documentReference.addSnapshotListener(listner);
        registration.remove();

    }


    private class MyValueEventListener implements EventListener<QuerySnapshot>
    {
        @Override
        public void onEvent(@Nullable QuerySnapshot snapshot,
                @Nullable FirebaseFirestoreException e) {
            if (e != null) {
                Log.w("DataBase", "Listen failed.", e);
                return;
            }
            if (snapshot != null && !snapshot.isEmpty()) {

                    setValue(snapshot);

                } else {
                Log.d("DataBase", "Current data: null");
            }
        }
    }


}

