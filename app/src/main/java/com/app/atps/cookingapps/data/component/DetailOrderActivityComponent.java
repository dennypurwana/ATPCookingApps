package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.DetailOrderActivityModule;
import com.app.atps.cookingapps.data.module.MyOrderActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.DetailOrderActivity;
import com.app.atps.cookingapps.view.MyOrderActivity;

import dagger.Component;

/**
 * Created by emerio on 5/27/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = DetailOrderActivityModule.class)
public interface DetailOrderActivityComponent {
    void inject(DetailOrderActivity detailOrderActivity);
}
