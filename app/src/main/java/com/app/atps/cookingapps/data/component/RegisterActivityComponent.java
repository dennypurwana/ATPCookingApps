package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.RegisterActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.RegisterActivity;

import dagger.Component;

/**
 * Created by emerio on 5/25/17.
 */
@CustomScope
@Component(dependencies = NetComponent.class,modules = RegisterActivityModule.class)
public interface RegisterActivityComponent {
 void inject(RegisterActivity registerActivity);
}
