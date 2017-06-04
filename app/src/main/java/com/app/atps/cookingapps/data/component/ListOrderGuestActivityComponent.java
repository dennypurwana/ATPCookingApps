package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.ListOrderGuestActivityModule;
import com.app.atps.cookingapps.data.module.ListUserActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListOrderGuestActivity;
import com.app.atps.cookingapps.view.ListUserActivity;

import dagger.Component;

/**
 * Created by emerio on 5/25/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = ListOrderGuestActivityModule.class)
public interface ListOrderGuestActivityComponent {
    void inject(ListOrderGuestActivity listOrderGuestActivity);
}
