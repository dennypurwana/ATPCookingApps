package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.HomeActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.HomeActivity;

import dagger.Component;

/**
 * Created by emerio on 5/25/17.
 */
@CustomScope
@Component(dependencies =NetComponent.class,modules = HomeActivityModule.class)
public interface HomeActivityComponent {
    void inject(HomeActivity homeActivity);
}
