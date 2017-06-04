package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.ListMenuMakananActivityModule;
import com.app.atps.cookingapps.data.module.ListTableRestoActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListMenuMakananActivity;
import com.app.atps.cookingapps.view.ListTableRestoActivity;

import dagger.Component;

/**
 * Created by emerio on 5/25/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = ListTableRestoActivityModule.class)
public interface ListTableRestoActivityComponent {
    void inject(ListTableRestoActivity listTableRestoActivity);
}
