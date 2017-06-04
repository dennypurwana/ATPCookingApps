package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.ListMenuMakananActivityModule;
import com.app.atps.cookingapps.data.module.ListNotificationActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListMenuMakananActivity;
import com.app.atps.cookingapps.view.ListNotificationActivity;

import dagger.Component;

/**
 * Created by emerio on 5/25/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = ListNotificationActivityModule.class)
public interface ListNotificationActivityComponent {
    void inject(ListNotificationActivity listNotificationActivity);
}
