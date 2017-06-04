package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.AddMenuMasakanActivityModule;
import com.app.atps.cookingapps.data.module.AddPromoActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.AddMenuMasakanActivity;
import com.app.atps.cookingapps.view.AddPromoActivity;

import dagger.Component;

/**
 * Created by emerio on 5/27/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = AddPromoActivityModule.class)
public interface AddPromoActivityComponent {
    void inject(AddPromoActivity addPromoActivity);
}
