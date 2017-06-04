package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.DetailMenuMakananActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.DetailMenuMakananActivity;

import dagger.Component;

/**
 * Created by emerio on 5/27/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = DetailMenuMakananActivityModule.class)
public interface DetailMenuMakananActivityComponent {
    void inject(DetailMenuMakananActivity detailMenuMakananActivity);
}
