package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.AddTableRestoActivityModule;
import com.app.atps.cookingapps.data.module.AddUserByAdminActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.AddTableRestoActivity;
import com.app.atps.cookingapps.view.AddUserByAdminActivity;

import dagger.Component;

/**
 * Created by emerio on 5/27/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = AddTableRestoActivityModule.class)
public interface AddTableRestoActivityComponent {
    void inject(AddTableRestoActivity addTableRestoActivity);
}
