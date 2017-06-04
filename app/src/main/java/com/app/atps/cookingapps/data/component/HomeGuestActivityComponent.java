package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.HomeActivityModule;
import com.app.atps.cookingapps.data.module.HomeGuestActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.HomeActivity;
import com.app.atps.cookingapps.view.HomeGuestActivity;

import dagger.Component;

/**
 * Created by emerio on 5/25/17.
 */
@CustomScope
@Component(dependencies =NetComponent.class,modules = HomeGuestActivityModule.class)
public interface HomeGuestActivityComponent {
    void inject(HomeGuestActivity homeActivity);
}
