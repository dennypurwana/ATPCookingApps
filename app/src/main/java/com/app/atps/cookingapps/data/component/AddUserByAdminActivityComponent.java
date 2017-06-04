package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.AddMenuMasakanActivityModule;
import com.app.atps.cookingapps.data.module.AddUserByAdminActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.AddMenuMasakanActivity;
import com.app.atps.cookingapps.view.AddUserByAdminActivity;

import dagger.Component;

/**
 * Created by emerio on 5/27/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = AddUserByAdminActivityModule.class)
public interface AddUserByAdminActivityComponent {
    void inject(AddUserByAdminActivity addUserByAdminActivity);
}
