package com.example.newsdesk;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ModelViewFactory implements ViewModelProvider.Factory {
        private Application mApplication;
        private Integer mParam;

        public ModelViewFactory(Application application, Integer param) {
            mApplication = application;
            mParam = param;
        }


        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ViewModelLiveData();
        }
    }


