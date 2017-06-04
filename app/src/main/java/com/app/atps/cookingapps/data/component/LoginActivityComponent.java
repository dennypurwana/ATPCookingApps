package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.LoginActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.LoginActivity;

import dagger.Component;

/**
 * Created by emerio on 5/25/17.
 */
@CustomScope
@Component(dependencies = NetComponent.class,modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);
}
