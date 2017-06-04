package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.MenuOrderActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.MenuOrderActivity;

import dagger.Component;

/**
 * Created by emerio on 5/28/17.
 */


@CustomScope
@Component(dependencies = NetComponent.class,modules = MenuOrderActivityModule.class)
public interface MenuOrderActivityContent {
    void inject(MenuOrderActivity menuOrderActivity);
}
